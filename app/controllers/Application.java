package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import utils.ws.WebSocketUtil;
import play.Routes;
import play.mvc.*;

import views.html.*;

import java.util.Map;

@org.springframework.stereotype.Controller
public class Application extends Controller {

    public static Result jsRoutes()
    {
        response().setContentType("text/javascript");
        return ok(Routes.javascriptRouter("appRoutes",
                // appRoutes will be the JS object available in our view
                routes.javascript.FieldController.delete(),
                routes.javascript.FieldController.createField(),
                routes.javascript.FieldController.editField(),
                routes.javascript.ResponseController.createResponse(),
                routes.javascript.Application.socket()
                ));
    }

    public static WebSocket<JsonNode> socket() {
        Long clientId = ctx().id();
        return new WebSocket<JsonNode>() {
            // Called when the WebSocket Handshake is done.
            public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) {
                try {
                    WebSocketUtil.addChannel(clientId, in, out);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }
}
