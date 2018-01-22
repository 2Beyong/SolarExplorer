package com.hongon.solarexplorer;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hongon.solarexplorer.dataBean.Login;
import com.hongon.solarexplorer.dataBean.MyPowerStation;

import org.json.JSONArray;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by CoCO on 2018/1/22.
 */

public class Query {

    // 单例化
    private static Query instance=null;
    public static Query getInstance(){
        if(instance==null)
        createInstance();
        return instance;
    }
    private synchronized static void createInstance(){
        if(instance==null)
            instance = new Query();
    }
    // constructor
    OkHttpClient client ;
    private  Query(){
        client = new OkHttpClient();
    }
    //
    public String Login(){

        String url ="http://goodwe-power.com/Mobile/Login?username=demo&amp;password=";
        Request request = new Request.Builder().url(url).build();
        try {
            Response x = client.newCall(request).execute();
            //Log.d("query",x.body().string());
            Login loginResult =new Gson().fromJson(x.body().string(),Login.class);
            return loginResult.getLoginFlagMsg();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<MyPowerStation> getMyPowerStationByUser(String userName){
        String url ="http://www.goodwe-power.com/Mobile/GetMyPowerStationByUser?userName="+userName;
        Request request = new Request.Builder().url(url).build();
        try {
            Response x = client.newCall(request).execute();
            //Log.d("query",x.body().string());
            List<MyPowerStation> result =new Gson().fromJson(x.body().string(),new TypeToken<List<MyPowerStation>>(){}.getType());
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
