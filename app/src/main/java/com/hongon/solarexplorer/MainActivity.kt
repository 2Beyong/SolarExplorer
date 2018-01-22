package com.hongon.solarexplorer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.hongon.solarexplorer.dataBean.MyPowerStation
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        val tx = findViewById<TextView>(R.id.textView)

           tx.setOnClickListener {
               thread(start =true){
               kotlin.run{
                   var query = Query.getInstance()
                   var loginResult:String = query.Login()
                   runOnUiThread { tx.setText(loginResult) }
               }
           }
           }
        LoadStationList("demo")
    }

    //
    fun initRecyclerView(){
        var stationList:List<MyPowerStation> =emptyList<MyPowerStation>()
        var adapter =MyPowerStationListAdapter(this,stationList)
        var rec:RecyclerView =findViewById(R.id.rec_myPowerStation)
        rec.layoutManager =LinearLayoutManager(this)
        rec.addItemDecoration(MyPowerStationListDecoration())
        rec.adapter =adapter

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
