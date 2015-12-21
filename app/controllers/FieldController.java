package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.entities.Field;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.FieldService;
import services.ResponseService;
import views.html.createField;
import views.html.editField;
import views.html.fieldList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static play.data.Form.form;

/**
 * Created by Maryna Pashkouskaya on 2015-12-18.
 */

@org.springframework.stereotype.Controller
public class FieldController extends Controller {
    @Autowired
    private FieldService fieldService;

    final static Form<Field> fieldForm = form(Field.class);

    public Result newField() {
        return ok(createField.render(fieldForm, ResponseService.numberResponses));
    }

    public Result createField() {
        if (fieldForm.hasErrors()) {
            return badRequest(createField.render(fieldForm, ResponseService.numberResponses));
        }
        JsonNode json = request().body().asJson();
        Field field = Json.fromJson(json, Field.class);
        field = fieldService.createField(field);
        Integer fieldId = field.getId();
        Map<String, String> result = new HashMap<String, String>();
        result.put("id", fieldId.toString());
        result.put("url", "/fields");
        return ok(Json.toJson(result));
    }

    public Result edit(Integer id) {
        Field field = fieldService.getFieldById(id);
        field.setFieldItemString(field.getItemsString());
        Form<Field> fieldForm = form(Field.class).fill(field);
        return ok(editField.render(id, fieldForm, field, ResponseService.numberResponses));
    }

    public Result editField(Integer id) {
        JsonNode json = request().body().asJson();
        Field field = Json.fromJson(json, Field.class);
        field.setId(id);
        Field f = fieldService.editField(field);
        Map<String, String> result = new HashMap<>();
        result.put("id", f.getId().toString());
        result.put("url", "/fields");
        return ok(Json.toJson(result));
    }

    @SuppressWarnings("unchecked")
    public Result fieldList() {
        List<Field> dataFields = new ArrayList<>();
        List<Field> fields = fieldService.getAllFields();
        dataFields.addAll(fields);

        return ok(fieldList.render(dataFields, ResponseService.numberResponses));
    }

    public Result delete(Integer id) {
        fieldService.deleteField(id);
        return redirect("/fields");
    }
}
