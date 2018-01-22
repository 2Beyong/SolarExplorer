package com.hongon.solarexplorer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView

/**
 * Created by CoCO on 2018/1/22.
 */
class MyPowerStationListDecoration(

        var paint: Paint =Paint(),
        var size: Int = 2):RecyclerView.ItemDecoration() {



    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        super.onDrawOver(c, parent, state)

        paint.color = Color.GRAY
        val left =parent.paddingLeft
        val right =parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + size

            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)

        }
    }



}







