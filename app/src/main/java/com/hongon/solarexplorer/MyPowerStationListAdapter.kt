package com.hongon.solarexplorer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hongon.solarexplorer.dataBean.MyPowerStation

import android.animation.Animator;
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Toast

/**
 * Created by CoCO on 2018/1/22.
 */
class MyPowerStationListAdapter(
        var mContext:Context,
        var items:List<MyPowerStation>
        ):
RecyclerView.Adapter<MyPowerStationListAdapter.ViewHolder>(){

    class ViewHolder(
        view: View
    ):RecyclerView.ViewHolder(view){
        var name:TextView =view.findViewById<TextView>(R.id.cardTitle)
        var image =view.findViewById<ImageView>(R.id.StationPicture)
        var power =view.findViewById<TextView>(R.id.cardPowerValue)
        var eDay = view.findViewById<TextView>(R.id.card_edayValue)
        var incomeDay = view.findViewById<TextView>(R.id.card_income_value)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.station_card_layout,parent,false)
        var holder = ViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(items[position].stationName)
        Log.e("picUrl",items[position].stationPictureUrl)
        Glide.with(mContext).load("http://www.goodwe-power.com"+items[position].stationPictureUrl).centerCrop().crossFade().into(holder.image)
        //holder.image
        holder.eDay.setText(items[position].eDayTotal)
        holder.incomeDay.text = items[position].dayIncomeTranslation()
        holder.power.text=items[position].currentPower

        //其实可以从外面调进来 类似Service中的举措 不过这里是写进了Adapter，并不是很方便
        holder.itemView.setOnClickListener {
            //Toast.makeText(mContext,"U click "+items[position].stationID,Toast.LENGTH_SHORT).show()
            var myIntent = Intent(mContext,ChartActivity::class.java)
            myIntent.putExtra("stationId",items[position].stationID)

            mContext.startActivity(myIntent)

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    /*
    *Animation
    * */




}