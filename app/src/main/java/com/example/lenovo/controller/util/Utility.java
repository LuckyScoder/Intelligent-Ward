package com.example.lenovo.controller.util;

import android.text.TextUtils;

import com.example.lenovo.controller.db.MultiRaspData;
import com.example.lenovo.controller.db.RaspData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/17.
 */

public class Utility {

    public static RaspData handleRaspDataResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray Data_Array = new JSONArray(response);
                JSONObject DataObject = Data_Array.getJSONObject(0);
                RaspData raspData = new RaspData();
                raspData.setStatus(DataObject.getString("status"));
                raspData.setTemperature(DataObject.getDouble("temperature"));
                raspData.setHumidity(DataObject.getDouble("humidity"));
                raspData.setTime(DataObject.getString("time"));
                return raspData;
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static MultiRaspData handleMultiRaspDataResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("MultiRaspData");
            String raspContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(raspContent,MultiRaspData.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
