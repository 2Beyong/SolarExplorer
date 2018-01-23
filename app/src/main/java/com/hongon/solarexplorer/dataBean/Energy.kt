package com.hongon.solarexplorer.dataBean

/**
 * Created by CoCO on 2018/1/22.
 */
class Energy(
        //var month:List<EnergyMonth>
){

}
// 记录某一年的12个月，每月的发电量
// 这里的bean记录的是单个月份
class EnergyMonthly(
    MonthNum:Int,
    MonthEnergy:Float

){}
// 记录某一年的某个月的31日，每日的发电量
// 这里的bean记录的是单个日子
class EnergyDaily(
        Day:Int,
        DayEnergy:Float
){}

//
class EnergyPerHour(

)