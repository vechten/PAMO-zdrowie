package com.example.zdrowie.java.ui.bmi

import android.graphics.Color
import com.github.mikephil.charting.data.BarEntry
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.zdrowie.java.ui.bmi.BmiFragment
import android.widget.TextView
import android.text.TextWatcher
import android.text.Editable
import android.view.View
import android.widget.Button
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarDataSet
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.zdrowie.databinding.FragmentBmiBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.utils.ColorTemplate
import java.lang.NumberFormatException
import java.text.DecimalFormat
import java.util.ArrayList

class BmiFragment : Fragment() {
    private val bmiValues = ArrayList<BarEntry>()
    private var height = 0.0
    private var weight = 0.0
    private var bmi = 0.0
    private var binding: FragmentBmiBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmiBinding.inflate(inflater, container, false)
        createDataForView(binding!!)
        return binding!!.root
    }

    private fun calculate() {
        bmi = if (weight > 0.0 && height > 0) {
            weight / (height / 100 * (height / 100))
        } else {
            0.0
        }
        setText(decimalFormat.format(bmi))
    }

    fun setText(text: String?) {
        binding!!.bmiTextView.text = text
    }

    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            height = try {
                s.toString().toInt().toDouble()
            } catch (e: NumberFormatException) {
                0.0
            }
            calculate()
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }
    private val weightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            weight = try {
                s.toString().toDouble()
            } catch (e: NumberFormatException) {
                0.0
            }
            calculate()
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setOnClick(btn: Button, chart: BarChart, dataset: BarDataSet) {
        btn.setOnClickListener { v: View? ->
            dataset.addEntry(BarEntry(dataset.entryCount.toFloat(), bmi.toFloat()))
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
            chart.invalidate()
        }
    }

    private fun createDataForView(binding: FragmentBmiBinding) {
        binding.heightEditNumber.addTextChangedListener(heightEditTextWatcher)
        binding.weightEditNumber.addTextChangedListener(weightEditTextWatcher)
        val chart = binding.barchart
        val dataset = BarDataSet(bmiValues, "BMI")
        val data = BarData(dataset)
        menageDataset(dataset)
        menageChart(chart, data)
        setOnClick(binding.saveButton, chart, dataset)
    }

    private fun menageChart(chart: BarChart, data: BarData) {
        chart.setFitBars(true)
        chart.data = data
        chart.description.text = "chart"
        chart.animateY(1000)
    }

    private fun menageDataset(dataset: BarDataSet) {
        dataset.setColors(*ColorTemplate.MATERIAL_COLORS)
        dataset.valueTextColor = Color.BLACK
        dataset.valueTextSize = 12f
    }

    companion object {
        private val decimalFormat = DecimalFormat("0.0")
    }
}