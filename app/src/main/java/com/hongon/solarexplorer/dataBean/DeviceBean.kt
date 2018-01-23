package com.hongon.solarexplorer.dataBean

import com.google.gson.annotations.SerializedName

/**
 * json对应的设备类型
 * Created by CoCO on 2018/1/23.
 */
class DeviceBean(
        @SerializedName("inventersn") val inventerSN:String,
        @SerializedName("desc") val destination:String,
        val power:String,
        val status :String,
        @SerializedName("eday") val eDay:String,
        @SerializedName("etotal") val eTotal:String,
        @SerializedName("errormsg") val errorMsg:String
)
