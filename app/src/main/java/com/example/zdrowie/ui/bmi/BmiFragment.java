package com.example.zdrowie.ui.bmi;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.zdrowie.databinding.FragmentBmiBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BmiFragment extends Fragment {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.0");
    private final ArrayList<BarEntry> bmiValues = new ArrayList<>();
    private double height = 0;
    private double weight = 0.0;
    private double bmi;

    private FragmentBmiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBmiBinding.inflate(inflater, container, false);
        createDataForView(binding);

        return binding.getRoot();
    }

    private void calculate() {
        if (weight > 0.0 && height > 0) {
            bmi = weight / ((height / 100) * (height / 100));
        } else {
            bmi = 0.0;
        }

        setText(decimalFormat.format(bmi));
    }

    public void setText(String text) {
        TextView view = (TextView) binding.bmiTextView;
        view.setText(text);
    }

    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            try {
                height = Integer.parseInt(s.toString());
            } catch (NumberFormatException e) {
                height = 0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            try {
                weight = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                weight = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setOnClick(final Button btn, final BarChart chart, BarDataSet dataset) {
        btn.setOnClickListener(v -> {
            dataset.addEntry(new BarEntry(dataset.getEntryCount(), (float) bmi));
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
            chart.invalidate();
        });
    }

    private void createDataForView(FragmentBmiBinding binding) {
        EditText heightEditText = (EditText) binding.heightEditNumber;
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        EditText weightEditText = (EditText) binding.weightEditNumber;
        weightEditText.addTextChangedListener(weightEditTextWatcher);
        BarChart chart = (BarChart) binding.barchart;
        Button saveButton = (Button) binding.saveButton;

        BarDataSet dataset = new BarDataSet(bmiValues, "BMI");
        BarData data = new BarData(dataset);

        menageDataset(dataset);
        menageChart(chart, data);
        setOnClick(saveButton, chart, dataset);
    }

    private void menageChart(BarChart chart, BarData data) {
        chart.setFitBars(true);
        chart.setData(data);
        chart.getDescription().setText("chart");
        chart.animateY(1000);
    }

    private void menageDataset(BarDataSet dataset) {
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);
        dataset.setValueTextColor(Color.BLACK);
        dataset.setValueTextSize(12f);
    }
}