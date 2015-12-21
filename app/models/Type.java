package models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Maryna Pashkouskaya on 2015-12-11.
 */
public enum Type {

    SINGLE_LINE_TEXT("Single line text"),
    MULTI_LINE_TEXT("Multi line text"),
    RADIO_BUTTON("Radio button"),
    CHECK_BOX("Check box"),
    COMBO_BOX("Combo box"),
    DATE("Date");

    private String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Map<String, String> options(){
        Map<String, String> values = new HashMap<>();
        for (Type type: Type.values()) {
            values.put(type.name(), type.getValue());
        }
        return values;
    }
}
