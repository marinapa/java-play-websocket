package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.ResponseForm;
import models.entities.Field;
import models.entities.FieldValue;
import models.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ApplicationService;
import services.FieldService;
import services.FieldValueService;
import services.ResponseService;
import utils.ws.WebSocketUtil;
import views.html.congratulationPage;
import views.html.responseCollecting;
import views.html.responseList;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static play.data.Form.form;

/**
 * Created by Maryna Pashkouskaya on 2015-12-18.
 */

@org.springframework.stereotype.Controller
public class ResponseController extends Controller  {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private FieldValueService fieldValueService;

    final static Form<ResponseForm> responseForm = form(ResponseForm.class);

    public Result newResponse() {
        List<Field> fields = fieldService.getAllFields();
        return ok(responseCollecting.render(responseForm, fields, ResponseService.numberResponses));
    }

    @SuppressWarnings("unchecked")
    public Result createResponse() {
        Map<String, String> result = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = request().body().asJson();
            String jsonString = Json.stringify(json);
            Map<String, Object> values = mapper.readValue(jsonString, Map.class);
            Response response = new Response();
            response.setUuid(UUID.randomUUID().toString());
            result = applicationService.createResponseWithValues(response, values);
            ResponseService.numberResponses = responseService.getResponseCount();
            result.put("count", ResponseService.numberResponses.toString());
            result.put("url", "/");
        } catch (IOException e) {
            result.put("error", e.getMessage());
            e.printStackTrace();
        }
        JsonNode jsonNode = Json.toJson(result);
        WebSocketUtil.tell(jsonNode);
        return ok(congratulationPage.render());
//        return ok(jsonNode);
    }

    @SuppressWarnings("unchecked")
    public Result responses() {
        List<Field> fields = fieldService.getAllFields();
        List<Response> responses = responseService.getAllResponses();

        Map<Integer, Map<Integer, String>> map = new HashMap<>();
        for (Response r : responses) {
            map.put(r.getId(), new HashMap<>());
        }
        List<FieldValue> fvs = fieldValueService.getAllValues();
        for (FieldValue fv : fvs) {
            map.get(fv.getId().getResponseId()).put(fv.getId().getFieldId(), fv.getValue());
        }

        return ok(responseList.render(fields, responses, map, responses.size()));
    }
}
