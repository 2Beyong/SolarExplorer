package com.hongon.solarexplorer.dataBean

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * Created by CoCO on 2018/1/22.
 */
class MyPowerStation (
      var stationID:String,
      var stationName:String,
      @SerializedName("station_pic")
      var stationPictureUrl:String,
      var currentPower:String,
      var capacity:String,
      @SerializedName("value_eDayTotal")
      var  eDayTotal:String,
      @SerializedName("value_eTotal")
      var  eTotal:String,
      @SerializedName("value_dayIncome")
      var  dayIncome:String,
      @SerializedName("value_totalIncome")
      var  totalIncome:String

){
      public fun dayIncomeTranslation() :String{
            if(dayIncome!=null){
                  val regex =Regex("(\\d+)\\.?(\\d+)")
                  val result = regex.find(dayIncome)!!.value

                  return result+Regex("[^\\.\\d]+").find(dayIncome)!!.value
            }
            return "";
      }
}


