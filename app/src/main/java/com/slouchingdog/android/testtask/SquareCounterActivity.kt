package com.slouchingdog.android.testtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.slouchingdog.android.testtask.databinding.ActivitySquareCounterBinding
import java.io.File
import java.util.Locale
import kotlin.math.pow

class SquareCounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySquareCounterBinding
    lateinit var fileForLogs: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquareCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val counter = intent.getIntExtra(COUNTER_STATE, 0)

        binding.tvSquareActivityCounter.text = String.format(
            Locale.getDefault(), "%d", counter.toDouble().pow(2.0).toInt()
        )
        fileForLogs = File("${filesDir.path}/activityLifecycleLogs.txt")
        fileForLogs.appendText("SquareCounterActivity CREATED\n")
    }

    override fun onStart() {
        super.onStart()

        fileForLogs.appendText("SquareCounterActivity STARTED\n")
    }

    override fun onResume() {
        super.onResume()

        fileForLogs.appendText("SquareCounterActivity RESUMED\n")
    }

    override fun onPause() {
        super.onPause()

        fileForLogs.appendText("SquareCounterActivity PAUSED\n")
    }

    override fun onStop() {
        super.onStop()

        fileForLogs.appendText("SquareCounterActivity STOPPED\n")
    }

    override fun onDestroy() {
        super.onDestroy()

        fileForLogs.appendText("SquareCounterActivity DESTROYED\n")
    }
}