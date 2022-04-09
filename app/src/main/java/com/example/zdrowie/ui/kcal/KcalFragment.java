package com.example.zdrowie.ui.kcal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.zdrowie.databinding.FragmentKcalBinding;

import java.text.DecimalFormat;

public class KcalFragment extends Fragment {

    private FragmentKcalBinding binding;

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.0");

    private double height = 0.0;
    private double weight = 0.0;
    private int age = 0;
    private String sex = "";
    double kcal = 0.0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentKcalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText heightEditText =  (EditText) binding.heightEditNumber;
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        EditText weightEditText =
                (EditText) binding.weightEditNumber;
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        EditText ageEditText =
                (EditText) binding.ageEditNumber;
        ageEditText.addTextChangedListener(ageEditTextWatcher);

        RadioGroup sexRadioGroup = binding.sexRadioGroup;
        sexRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton checkedButton = (RadioButton) requireView().
                    findViewById(sexRadioGroup.getCheckedRadioButtonId());

            String checkedButtonText = checkedButton.getText().toString();

            switch (checkedButtonText) {
                case "Female":
                    sex = "female";
                    break;

                case "Male":
                    sex = "male";
                    break;
            }

            calculate();
        });

        return root;
    }

    private void calculate() {
        if ( weight > 0 && height > 0 && age > 0 && (sex.equals("female") || sex.equals("male"))) {
            if(sex.equals("male")) {
                kcal = 66.47 + (13.7 * weight) + (5.0 * height) - (6.76 * age);
            }
            else {
                kcal = 655.1 + (9.567 * weight) + (1.85 * height) - (4.68 * age);
            }
        }

        setText(decimalFormat.format(kcal));
    }

    public void setText(String text) {
        TextView view = (TextView) binding.bmrValueTextView;
        view.setText(text);
    }

    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            try {
                height = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                height = 0.0;
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

    private final TextWatcher ageEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            try {
                age = Integer.parseInt(s.toString());
            }
            catch (NumberFormatException e) {
                age = 0;
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