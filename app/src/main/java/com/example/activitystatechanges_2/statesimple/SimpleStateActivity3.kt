package com.example.activitystatechanges_2.statesimple

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.core.content.ContextCompat
import com.example.activitystatechanges_2.R
import com.example.activitystatechanges_2.databinding.ActivitySimpleState3Binding
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import kotlin.random.Random

class SimpleStateActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivitySimpleState3Binding

    private lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleState3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonIncrement.setOnClickListener { increment() }
        binding.buttonRandomColor.setOnClickListener { setRandomColor() }
        binding.buttonSwitchVisibility.setOnClickListener { switchVisibility() }

        state = if (savedInstanceState == null) {
            State(
                counterValue = 0,
                counterTextColor = ContextCompat.getColor(this, R.color.purple_700),
                counterIsVisible = true
            )
        } else {
            savedInstanceState.getParcelable(KEY_STATE)!!
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
    }

    private fun increment() {
        state.counterValue++
        renderState()
    }

    private fun setRandomColor() {
        state.counterTextColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun switchVisibility() {
        state.counterIsVisible = !state.counterIsVisible
        renderState()
    }

    private fun renderState() {
        binding.textViewCounter.text = state.counterValue.toString()
        binding.textViewCounter.setTextColor(state.counterTextColor)
        binding.textViewCounter.visibility = if (state.counterIsVisible)
            View.VISIBLE
        else
            View.INVISIBLE
    }

    @Parcelize
    class State(
        var counterValue: Int,
        var counterTextColor: Int,
        var counterIsVisible: Boolean,
    ) : Parcelable


        companion object {

            const val KEY_STATE = "STATE"
        }
    }