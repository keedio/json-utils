package org.keedio.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class JsonTransformer {

    public static List<String> expand(String fieldName, String jsonString) {
        Gson gson = new Gson();

        JsonObject original = gson.fromJson(jsonString, JsonObject.class);
        JsonArray data = original.getAsJsonArray(fieldName);

        original.remove(fieldName);
        String reduced = original.toString();

        JsonArray out = new JsonArray();
        for (int i = 0; i < data.size(); i++) {
            JsonObject newJson = gson.fromJson(reduced, JsonObject.class);
            Object object = data.get(i);
            if ( object instanceof JsonObject ) {
                JsonObject jsonObject = (JsonObject) object;
                for (Entry<String, JsonElement> k : jsonObject.entrySet()) {
                    newJson.add(k.getKey(), k.getValue());
                }
            } else {
                newJson.add(fieldName, (JsonElement) object);
            }
            int j = i + 1;
            out.add(newJson);
        }

        List<String> jsonObjectsList = new LinkedList();
        for (int i = 0; i < out.size(); i++) {
            jsonObjectsList.add(out.get(i).toString());
        }

        return jsonObjectsList;
    }

}
