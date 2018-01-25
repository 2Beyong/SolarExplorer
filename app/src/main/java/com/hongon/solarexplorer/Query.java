package com.hongon.solarexplorer;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hongon.solarexplorer.dataBean.DayChartDataBean;
import com.hongon.solarexplorer.dataBean.DeviceBean;
import com.hongon.solarexplorer.dataBean.EnergyDaily;
import com.hongon.solarexplorer.dataBean.EnergyEntry;
import com.hongon.solarexplorer.dataBean.EnergyMonthly;
import com.hongon.solarexplorer.dataBean.Login;
import com.hongon.solarexplorer.dataBean.MyPowerStation;

import org.json.JSONArray;

import java.time.format.DateTimeFormatterBuilder;
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

    // 获取某个站点麾下的逆变器运营状态数据
    public List<DeviceBean> getMyDeviceListById(String stationId){
        String url ="http://www.goodwe-power.com/Mobile/GetMyDeviceListById?stationId="+stationId;
        Request request = new Request.Builder().url(url).build();
        try {
            Response x = client.newCall(request).execute();
            //Log.d("query",x.body().string());
            List<DeviceBean> result =new Gson().fromJson(x.body().string(),new TypeToken<List<DeviceBean>>(){}.getType());
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    /*
    * @param stationID  选中的站点
    * @param dayTime    选择的日期（不能超过当日，可以是以前的日子）
    * @param selectSN 选中的逆变器序列号*/
    public DayChartDataBean getEnergyInverterDayChartDataByStationID(String stationId ,String dayTime,String selectSN){
        String url ="http://www.goodwe-power.com/Mobile/GetEnergyInverterDayChartDataByStationID?StationID="+stationId+"&DayTime="+dayTime+"&selectSN="+selectSN;
        Request request = new Request.Builder().url(url).build();
        try {
            Response x = client.newCall(request).execute();
            //Log.d("query",x.body().string());
            DayChartDataBean result =new Gson().fromJson(x.body().string(),DayChartDataBean.class);
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<EnergyEntry> getPowerBarChart(String stationId,int queryType,String date){
        String url ="http://www.goodwe-power.com/Mobile/GetPowerBarChart?stationID="+stationId+"&queryType="+queryType+"&date="+date;
        //
        Request request = new Request.Builder().url(url).build();
        try {
            Response x = client.newCall(request).execute();
            //
            if(queryType ==1){
                List<EnergyEntry> result = new Gson().fromJson(x.body().string(), new TypeToken<List<EnergyMonthly>>(){}.getType());
                mInterface.OnReceiveMonth(result);
                return result;
            }
            else if(queryType ==2){
                List<EnergyEntry> result = new Gson().fromJson(x.body().string(), new TypeToken<List<EnergyDaily>>(){}.getType());
                mInterface.OnReceiveDay(result);
                return result;
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null ;
    }
    //
    public interface OnReceivePowerBarChart{
        public void OnReceiveMonth(List<EnergyEntry> result);

        public void OnReceiveDay(List<EnergyEntry> result);
    }
    OnReceivePowerBarChart mInterface;
    public void setOnReceivePowerBarChart(OnReceivePowerBarChart t){
        mInterface =t;
    }


}
