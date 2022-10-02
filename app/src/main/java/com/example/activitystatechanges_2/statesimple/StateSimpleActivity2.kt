package com.example.activitystatechanges_2.statesimple

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.activitystatechanges_2.R
import com.example.activitystatechanges_2.databinding.ActivityStateSimple2Binding
import kotlin.properties.Delegates.notNull
import kotlin.random.Random

class StateSimpleActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityStateSimple2Binding

    private var counterValue by notNull<Int>()
    private var counterTextColor by notNull<Int>()
    private var counterIsVisible by notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStateSimple2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonIncrement.setOnClickListener { increment() }
        binding.buttonRandomColor.setOnClickListener { setRandomColor() }
        binding.buttonSwitchVisibility.setOnClickListener { switchVisibility() }

        if (savedInstanceState == null) {
            counterValue = 0
            counterTextColor = ContextCompat.getColor(this, R.color.purple_700)
            counterIsVisible = true
        } else {
            counterValue = savedInstanceState.getInt(KEY_COUNTER)
            counterTextColor = savedInstanceState.getInt(KEY_COLOR)
            counterIsVisible = savedInstanceState.getBoolean(KEY_IS_VISIBLE)
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNTER, counterValue)
        outState.putInt(KEY_COLOR, counterTextColor)
        outState.putBoolean(KEY_IS_VISIBLE, counterIsVisible)
    }

    private fun increment() {
        counterValue++
        renderState()
    }

    private fun setRandomColor() {
        counterTextColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun switchVisibility() {
        counterIsVisible = !counterIsVisible
        renderState()
    }


    private fun renderState() {
        binding.textViewCounter.text = counterValue.toString()
        binding.textViewCounter.setTextColor(counterTextColor)
        binding.textViewCounter.visibility = if (counterIsVisible)
            View.VISIBLE
        else
            View.INVISIBLE
    }

    companion object {

        const val KEY_COUNTER = "COUNTER"
        const val KEY_COLOR = "COLOR"
        const val KEY_IS_VISIBLE = "IS_VISIBLE"
    }
}