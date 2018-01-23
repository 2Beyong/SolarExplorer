package com.hongon.solarexplorer.dataBean

/**
 * Created by CoCO on 2018/1/23.
 */
class DayChartDataBean (
       var PV:List<XYBean>,
       var Pac:List<XYBean>,
       var Meter:List<XYBean>,
       var BatteryPower:List<XYBean>,
       var Battery:List<XYBean>,
       var EDay:Float,
       var Self:List<XYBean>,
       var Use:List<XYBean>

){

    data class XYBean(
            // day minute
            var x:Int,
            // value
            var y:Float
    )


}