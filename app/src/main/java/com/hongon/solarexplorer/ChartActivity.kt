package com.hongon.solarexplorer

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.UiThread
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.Toast
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.hongon.solarexplorer.dataBean.EnergyEntry
import com.hongon.solarexplorer.dataBean.EnergyMonthly
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

/**
 * Created by CoCO on 2018/1/25.
 */
class ChartActivity:AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // layout
        setContentView(R.layout.station_line_chart_layout)

        // 获取stationId
        var stationID:String  = intent.getStringExtra("stationId")
        Log.e("charActivity","OnCreate")
        //
        Toast.makeText(this,"Selected ID is: "+stationID,Toast.LENGTH_SHORT).show()

        // DateTime 设置 获取今天的时间
        var dateFormat = SimpleDateFormat("yyyy-mm-dd")
        var dateToday:String = dateFormat.format(Date())

        // GetMonth
        Query.getInstance().setOnReceivePowerBarChart(mInterface)
        //var monthData:List<EnergyEntry>? = Query.getInstance().getPowerBarChart(stationID,1,dateToday)
        // GetDaily
        thread(start=true){ Query.getInstance().getPowerBarChart(stationID,2,dateToday)}


        //chart
        /*
        var valSet = ArrayList<Entry>()

        for(i in dayData){
            valSet.add(Entry(i.getIndex().toFloat(),i.getValue()))
        }

        //
        var dataSet = LineDataSet(valSet,"A")
        var linedata:LineData = LineData(dataSet)

        var chartActive:LineChart = findViewById<LineChart>(R.id.Chart)


        chartActive.data =linedata
        chartActive.notifyDataSetChanged()
        */
    }
    //
    //var monthData:List<EnergyEntry>?
    //var dayData:List<EnergyEntry>? =  MutableList<EnergyEntry>()
    var mInterface = ReceiveInterface()
    inner class ReceiveInterface( ) :Query.OnReceivePowerBarChart {
        override fun OnReceiveDay(result: MutableList<EnergyEntry>) {
            runOnUiThread{
                Toast.makeText(this@ChartActivity,"day"+result[0].getIndex().toString(),Toast.LENGTH_SHORT).show()
                Log.e("xx","OnReceive"+result[0].getIndex().toString())

                var valSet = ArrayList<Entry>()

                for(i in result){
                    valSet.add(Entry(i.getIndex().toFloat(),i.getValue()))
                }

                //
                var dataSet = LineDataSet(valSet,"A")
                var linedata:LineData = LineData(dataSet)

                var chartActive:LineChart = findViewById<LineChart>(R.id.Chart)


                chartActive.data =linedata
                chartActive.notifyDataSetChanged()
            }

        }

        override fun OnReceiveMonth(result: MutableList<EnergyEntry>?) {

        }
    }
}