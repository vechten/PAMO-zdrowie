package com.example.zdrowie.java.ui.kcal

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.RadioButton
import android.text.TextWatcher
import android.text.Editable
import android.view.View
import androidx.fragment.app.Fragment
import com.example.zdrowie.databinding.FragmentKcalBinding
import java.lang.NumberFormatException
import java.text.DecimalFormat

class KcalFragment : Fragment() {
    private var binding: FragmentKcalBinding? = null
    private var height = 0.0
    private var weight = 0.0
    private var age = 0
    private var sex = ""
    private var kcal = 0.0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentKcalBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        binding!!.heightEditNumber.addTextChangedListener(heightEditTextWatcher)
        binding!!.weightEditNumber.addTextChangedListener(weightEditTextWatcher)
        binding!!.ageEditNumber.addTextChangedListener(ageEditTextWatcher)
        val sexRadioGroup = binding!!.sexRadioGroup
        sexRadioGroup.setOnCheckedChangeListener { _: RadioGroup?, _: Int ->
            val checkedButton =
                requireView().findViewById<View>(sexRadioGroup.checkedRadioButtonId) as RadioButton
            when (checkedButton.text.toString()) {
                "Female" -> sex = "female"
                "Male" -> sex = "male"
            }
            calculate()
        }
        return root
    }

    private fun calculate() {
        if (weight > 0 && height > 0 && age > 0 && (sex == "female" || sex == "male")) {
            kcal = if (sex == "male") {
                66.47 + 13.7 * weight + 5.0 * height - 6.76 * age
            } else {
                655.1 + 9.567 * weight + 1.85 * height - 4.68 * age
            }
        }
        setText(decimalFormat.format(kcal))
    }

    private fun setText(text: String?) {
        binding!!.bmrValueTextView.text = text
    }

    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            height = try {
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
    private val ageEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            age = try {
                s.toString().toInt()
            } catch (e: NumberFormatException) {
                0
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

    companion object {
        private val decimalFormat = DecimalFormat("0.0")
    }
}