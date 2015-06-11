package org.keedio.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class JsonTransformer {

    public static List<String> expand(String fieldName, String jsonString) {

        JSONObject original = new JSONObject(jsonString);
        JSONArray data = original.getJSONArray(fieldName);

        original.remove(fieldName);
        String reduced = original.toString();

        JSONArray out = new JSONArray();
        for (int i = 0; i < data.length(); i++) {
            JSONObject newJson = new JSONObject(reduced);
            Object object = data.get(i);
            if ( object instanceof JSONObject ) {
                JSONObject jsonObject = (JSONObject) object;
                for (Object k : jsonObject.keySet()) {
                    newJson.put(k.toString(), jsonObject.get(k.toString()));
                }
            } else {
                newJson.put(fieldName, object);
            }
            int j = i + 1;
            out.put(newJson);
        }

        List<String> jsonObjectsList = new LinkedList();
        for (int i = 0; i < out.length(); i++) {
            jsonObjectsList.add(out.get(i).toString());
        }

        return jsonObjectsList;
    }

}
