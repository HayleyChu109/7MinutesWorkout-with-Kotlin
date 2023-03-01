package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

class BMIActivity : AppCompatActivity() {
    companion object {
        private const val METRIC_UNIT_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNIT_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibleView: String = METRIC_UNIT_VIEW

    private var binding: ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBMI)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }
        binding?.toolbarBMI?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId: Int ->
            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitView()
            } else {
                makeVisibleUsUnitView()
            }
        }

        binding?.btnCalculateBMI?.setOnClickListener {
            calculateBMI()
        }
    }

    private fun makeVisibleMetricUnitView() {
        currentVisibleView = METRIC_UNIT_VIEW

        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE

        binding?.tilUsUnitWeightInPound?.visibility = View.INVISIBLE
        binding?.llUsUnitHeight?.visibility = View.INVISIBLE

        binding?.etMetricUnitWeight?.text?.clear()
        binding?.etMetricUnitHeight?.text?.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUsUnitView() {
        currentVisibleView = US_UNIT_VIEW

        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE

        binding?.tilUsUnitWeightInPound?.visibility = View.VISIBLE
        binding?.llUsUnitHeight?.visibility = View.VISIBLE

        binding?.etUsUnitWeightInPound?.text?.clear()
        binding?.etUsUnitHeightInFeet?.text?.clear()
        binding?.etUsUnitHeightInInch?.text?.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (binding?.etMetricUnitWeight?.text.toString()
                .isEmpty() || binding?.etMetricUnitHeight?.text.toString().isEmpty()
        ) {
            isValid = false
        }

        return isValid
    }

    private fun validateUsUnits(): Boolean {
        var isValid = true

        if (binding?.etUsUnitWeightInPound?.text.toString()
                .isEmpty() || binding?.etUsUnitHeightInFeet?.text.toString()
                .isEmpty() || binding?.etUsUnitHeightInInch?.text.toString().isEmpty()
        ) {
            isValid = false
        }

        return isValid
    }

    private fun calculateBMI() {
        if (currentVisibleView == METRIC_UNIT_VIEW) {
            if (validateMetricUnits()) {
                val weight: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val height: Float = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100

                val bmi = weight / height.pow(2)

                displayBMIResult(bmi)
            } else {
                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_LONG).show()
            }
        } else {
            if (validateUsUnits()) {
                val weight = binding?.etUsUnitWeightInPound?.text.toString().toFloat()
                val heightInFeet = binding?.etUsUnitHeightInFeet?.text.toString().toFloat()
                val heightInInch = binding?.etUsUnitHeightInInch?.text.toString().toFloat()

                val height = heightInFeet * 12 + heightInInch

                val bmi = 703 * (weight / height.pow(2))

                displayBMIResult(bmi)
            } else {
                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun displayBMIResult(bmi: Float) {
        binding?.llDisplayBMIResult?.visibility = View.VISIBLE

        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You need to take better care of yourself! Workout more!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You need to take better care of yourself! Workout more!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Obese Class | (Severely obese)"
            bmiDescription = "Oops! You are in a very dangerous condition! Act more!"
        } else {
            bmiLabel = "Obese Class | (Very severely obese)"
            bmiDescription = "Oops! You are in a very dangerous condition! Act more!"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}