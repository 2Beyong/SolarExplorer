package com.hongon.solarexplorer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.hongon.solarexplorer.dataBean.MyPowerStation
import kotlin.concurrent.thread
import android.support.v4.view.ViewCompat.setScaleY
import android.icu.util.UniversalTimeScale.MAX_SCALE
import android.util.Log
import android.widget.RelativeLayout
import com.bumptech.glide.util.Util
import com.leochuan.ScaleLayoutManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        thread(start=true){ Query.getInstance().Login()}
        //setContentView(R.layout.station_card_layout)
        initRecyclerView()


        LoadStationList("demo")
    }

    //
    fun initRecyclerView(){
        var stationList:List<MyPowerStation> =emptyList<MyPowerStation>()
        var adapter =MyPowerStationListAdapter(this,stationList)
        var rec:RecyclerView =findViewById(R.id.rec_myPowerStation)


        //rec.layoutManager =LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rec.layoutManager = createScaleLayoutManager()
        //rec.addItemDecoration(MyPowerStationListDecoration())
        rec.adapter =adapter

        //



    }
    //
    fun createScaleLayoutManager(): ScaleLayoutManager{
        return ScaleLayoutManager(this, 20)
    }

    //
    fun LoadStationList(userName:String){
        thread(start=true){
            var query =Query.getInstance()
            var stationList = query.getMyPowerStationByUser(userName)
            runOnUiThread{
                var rec:RecyclerView =findViewById(R.id.rec_myPowerStation)
                var adapter :MyPowerStationListAdapter = rec.adapter as MyPowerStationListAdapter
                adapter.items = stationList
                adapter.notifyDataSetChanged()
            }
        }
    }

}
