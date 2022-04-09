package com.example.zdrowie.ui.bmi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.zdrowie.databinding.FragmentBmiBinding;

import java.text.DecimalFormat;


public class BmiFragment extends Fragment {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.0");

    private double height = 0;
    private double weight = 0.0;

    private FragmentBmiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText heightEditText =  (EditText) binding.heightEditNumber;
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        EditText weightEditText =
                (EditText) binding.weightEditNumber;
        weightEditText.addTextChangedListener(weightEditTextWatcher);


        return root;
    }

    private void calculate() {
        double bmi;
        if ( weight > 0.0 && height > 0) {
            bmi = weight / ((height/100) * (height/100));
        }
        else {
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
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            try {
                weight = Double.parseDouble(s.toString());
            }
            catch (NumberFormatException e) {
                weight = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}