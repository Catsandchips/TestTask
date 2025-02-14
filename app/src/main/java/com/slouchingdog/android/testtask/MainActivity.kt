package com.slouchingdog.android.testtask

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.slouchingdog.android.testtask.databinding.ActivityMainBinding
import java.io.File
import java.util.Locale

const val COUNTER_STATE = "COUNTER STATE"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fileForLogs: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCounter(0)

        fileForLogs = File(filesDir.path, "activityLifecycleLogs.txt")
        fileForLogs.appendText("MainActivity CREATED\n")
    }

    private fun setCounter(counterValue: Int) {
        binding.tvMainActivityCounter.text = String.format(Locale.getDefault(), "%d", counterValue)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COUNTER_STATE, binding.tvMainActivityCounter.text.toString().toInt())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        setCounter(savedInstanceState.getInt(COUNTER_STATE) + 1)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        binding.btnGoToSecondActivity.setOnClickListener {
            val intent = Intent(this, SquareCounterActivity::class.java)
            intent.putExtra(COUNTER_STATE, binding.tvMainActivityCounter.text.toString().toInt())
            startActivity(intent)
        }

        fileForLogs.appendText("MainActivity STARTED\n")
    }

    override fun onResume() {
        super.onResume()

        fileForLogs.appendText("MainActivity RESUMED\n")
    }

    override fun onPause() {
        super.onPause()

        fileForLogs.appendText("MainActivity PAUSED\n")
    }

    override fun onStop() {
        super.onStop()

        fileForLogs.appendText("MainActivity STOPPED\n")
    }

    override fun onDestroy() {
        super.onDestroy()

        fileForLogs.appendText("MainActivity DESTROYED\n")
    }
}