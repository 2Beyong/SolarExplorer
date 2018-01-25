package com.hongon.solarexplorer.dataBean

import java.time.Month

/**
 * Created by CoCO on 2018/1/22.
 */
interface EnergyEntry{
    fun getIndex():Int
    fun getValue():Float
}
// 记录某一年的12个月，每月的发电量
// 这里的bean记录的是单个月份
class EnergyMonthly(
    var MonthNum:Int= 0,
    var MonthEnergy:Float=1f

):EnergyEntry{
    override fun getIndex(): Int {
        return MonthNum
    }

    override fun getValue(): Float {
        return  MonthEnergy
    }
}
// 记录某一年的某个月的31日，每日的发电量
// 这里的bean记录的是单个日子
class EnergyDaily(
        var DayNum:Int,
        var DayEnergy:Float
):EnergyEntry{
    override fun getIndex(): Int {
        return DayNum
    }

    override fun getValue(): Float {
        return  DayEnergy
    }
}

//
class EnergyPerHour(

)