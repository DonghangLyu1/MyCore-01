package com.mobdev.noiseoff

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    var level = 10
    lateinit var textTop:TextView
    lateinit var buttonSneeze:Button
    lateinit var buttonMedication:Button
    lateinit var buttonBlow:Button

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textTop = view.findViewById<TextView>(R.id.textview_first)
        buttonSneeze = view.findViewById<Button>(R.id.button_sneeze)
        buttonMedication = view.findViewById<Button>(R.id.button_take_medicine)
        buttonBlow = view.findViewById<Button>(R.id.button_blow)

        if(null != savedInstanceState) level = savedInstanceState.getInt("level")
        textTop.text = "$level"
        updateColor()

        buttonSneeze.setOnClickListener {
            Log.i("Button Clicked", "current $level")
            if (level > 1) level -= 1
            else Log.i("Useless click", "level not change")

            textTop.text = "$level"
            updateColor()
        }

        buttonMedication.setOnClickListener {
            Log.i("Button Clicked", "current $level")
            if(level < 9) level += 2
            else Log.i("Useless click", "level not change")

            textTop.text = "$level"
            updateColor()
        }

        buttonBlow.setOnClickListener {
            Log.i("Button Clicked", "current $level")
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.human_nose_sniffing)
            mediaPlayer?.start()
        }
    }

    fun updateColor() {
        if (level <= 5) {
            Log.i("Color", "Bad color")
            textTop.setBackgroundColor(resources.getColor(R.color.colorReallyBad))
        } else if (level <= 7) {
            Log.i("Color", "Little bad color")
            textTop.setBackgroundColor(resources.getColor(R.color.colorLittleBad))
        } else {
            Log.i("Color", "Normal color")
            textTop.setBackgroundColor(resources.getColor(R.color.colorNormal))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("level", level)
    }
}