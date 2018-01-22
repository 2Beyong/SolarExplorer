package com.hongon.solarexplorer

import android.content.Context
import android.support.v7.widget.AppCompatAutoCompleteTextView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hongon.solarexplorer.dataBean.MyPowerStation

/**
 * Created by CoCO on 2018/1/22.
 */
class MyPowerStationListAdapter(var mContext:Context, var items:List<MyPowerStation>):
RecyclerView.Adapter<MyPowerStationListAdapter.ViewHolder>(){

    class ViewHolder(
        view: View
    ):RecyclerView.ViewHolder(view){
        var name:TextView =view.findViewById<TextView>(R.id.tv_stationName)
        var image =view.findViewById<ImageView>(R.id.stationPic)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.my_power_station_list_item,parent,false)
        var holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(items[position].stationName)
        Log.e("picUrl",items[position].stationPictureUrl)
        Glide.with(mContext).load("http://www.goodwe-power.com"+items[position].stationPictureUrl).crossFade().into(holder.image)
        //holder.image

    }

    override fun getItemCount(): Int {
        return items.size
    }
}