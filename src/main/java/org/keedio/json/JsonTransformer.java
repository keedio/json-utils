package org.keedio.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTransformer {

    public static JSONArray expand(String fieldName, String jsonString) {

        JSONObject original = new JSONObject(jsonString);
        JSONArray data = original.getJSONArray(fieldName);

        original.remove(fieldName);
        String reduced = original.toString();

        JSONArray out = new JSONArray();
        for (int i = 0; i < data.length(); i++) {
            JSONObject newJson = new JSONObject(reduced);
            JSONObject d = data.getJSONObject(i);
            for (Object k : d.keySet()) {
                newJson.put(k.toString(), d.get(k.toString()));
            }
            int j = i + 1;
            out.put(newJson);
        }
        return out;
    }

}
