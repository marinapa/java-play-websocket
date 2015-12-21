package utils.ws;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.Logger;
import play.libs.F;
import play.libs.Json;
import play.mvc.WebSocket;
import services.ResponseService;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static akka.pattern.Patterns.ask;

/**
 * Created by Maryna Pashkouskaya on 2015-12-17.
 */


public class WebSocketUtil {

    public static final String STATUS_SUCCEED = "succeed";
    public static final String STATUS_FAILED = "failed";
    public static final String STATUS = "status";

    // hold open webSockets for 'active' clients
    public static Map<Long, WebSocket.Out<JsonNode>> channels = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static void addChannel(Long id, WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) {
        // register a callback for processing instream events
        in.onMessage(new F.Callback<JsonNode>() {
            public void invoke(JsonNode status) {
                String jsonString = Json.stringify(status);
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> values = new HashMap<String, Object>();
                try {
                    values = mapper.readValue(jsonString, Map.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Logger.info("Submit user data status : " + values.get(STATUS).toString());
            }
        });

        in.onClose(new F.Callback0() {
            public void invoke() {
                channels.remove(id);
                Logger.info("Channel " + id + " status : Disconnected");
            }
        });

        // add new client connection to channel map
        channels.put(id, out);
        Logger.info("Channel " + id + " status : Connected");
    }

    public static void tell(JsonNode jsonNode) {
        for (WebSocket.Out<JsonNode> channel : channels.values()) {
                channel.write(jsonNode);
        }
    }
}
