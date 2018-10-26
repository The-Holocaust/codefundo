package com.example.anmolbhat.codefundo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser {

    private HashMap<String, String> getPlace(JSONObject googleplace){

        HashMap<String, String> googlplacemap = new HashMap<>();
        String placeName = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitude = "";
        String reference = "";

        try {
            if (!googleplace.isNull("name"))
                placeName = googleplace.getString("name");

            if (!googleplace.isNull("vicinity"))
                vicinity = googleplace.getString("vicinity");

            latitude = googleplace.getJSONObject("geometry").getJSONObject("loaction").getString("lat");
            longitude = googleplace.getJSONObject("geometry").getJSONObject("loaction").getString("lng");

            reference = googleplace.getString("reference");

            googlplacemap.put("place_name",placeName);
            googlplacemap.put("vicinity",vicinity);
            googlplacemap.put("latitude",latitude);
            googlplacemap.put("longitude",longitude);
            googlplacemap.put("reference",reference);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return googlplacemap;
    }

    private List<HashMap<String, String>> getPlaces(JSONArray jsonArray){

        int count = jsonArray.length();
        List<HashMap<String, String>> placeList = new ArrayList<>();
        HashMap<String, String> placeMap = null;

        for(int i=0;i<count;i++){
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(i));
                placeList.add(placeMap);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return placeList;
    }

    public List<HashMap<String, String>> parse(String jsonData){
        JSONArray  jsonArray = null;
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getPlaces(jsonArray);
    }
}
