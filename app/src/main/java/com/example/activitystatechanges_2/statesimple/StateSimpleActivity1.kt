package com.example.activitystatechanges_2.statesimple

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.core.view.isVisible
import com.example.activitystatechanges_2.R
import com.example.activitystatechanges_2.databinding.ActivityStateSimple1Binding
import kotlin.random.Random

class StateSimpleActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivityStateSimple1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStateSimple1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonIncrement.setOnClickListener { increment() }
        binding.buttonRandomColor.setOnClickListener { setRandomColor() }
        binding.buttonSwitchVisibility.setOnClickListener { switchVisibility() }
    }

    private fun increment() {
        var counter = binding.textViewCounter.text.toString().toInt()
        counter++
        binding.textViewCounter.text = counter.toString()
    }

    private fun setRandomColor() {
        val randomColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        binding.textViewCounter.setTextColor(randomColor)
    }

    private fun switchVisibility() = with(binding.textViewCounter) {
        visibility = if (visibility == VISIBLE)
            INVISIBLE
        else
            VISIBLE
    }
}