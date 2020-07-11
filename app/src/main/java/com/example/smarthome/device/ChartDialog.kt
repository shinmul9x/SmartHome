package com.example.smarthome.device

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.smarthome.R
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.popup_chart_room.view.*

class ChartDialog(private var points: ArrayList<Entry>) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val chart = inflater.inflate(R.layout.popup_chart_room, container, false)
        // set up chart
        (dialog!!).apply {
            setTitle(tag)
        }

        val points = ArrayList<Entry>()
        points.add(Entry(1f, 50f))
        points.add(Entry(2f, 150f))
        points.add(Entry(3f, 100f))
        val lineChart = chart.line_chart_power
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)
        lineChart.minimumHeight = 400
        if (lineChart.data != null && lineChart.data.dataSetCount > 0) {
            val set = lineChart.data.getDataSetByIndex(0) as LineDataSet
            set.values = points
            lineChart.data.notifyDataChanged()
        } else {
            val set = LineDataSet(points, "Power")
            set.setDrawIcons(false)
            set.enableDashedLine(10f, 5f, 0f)
            set.enableDashedHighlightLine(10f, 5f, 0f)
            set.color = Color.DKGRAY
            set.setCircleColor(Color.DKGRAY)
            set.lineWidth = 1f
            set.circleRadius = 3f
            set.setDrawCircleHole(false)
            set.valueTextSize = 9f
            set.setDrawFilled(true)
            set.formLineWidth = 1f
            set.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set.formSize = 15f
            if (Utils.getSDKInt() >= 18) {
                val drawable = ContextCompat.getDrawable(context!!, R.drawable.fade_blue)
                set.fillDrawable = drawable
            } else {
                set.fillColor = Color.DKGRAY
            }
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set)
            val data = LineData(dataSets)
            lineChart.data = data
        }

        chart.btn_cancel.setOnClickListener {
            dismiss()
        }

        return chart
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }
}