package com.example.scheduleajob

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startJob.setOnClickListener {
            val intent = Intent(this, ScheduleReceiver::class.java)
            intent.action = NotificationJobService.NOTIFICATION_ACTION
            sendBroadcast(intent)
        }

        cancelJob.setOnClickListener {
            ScheduleUtils.stopSchedule(this)
            Snackbar.make(layout, getString(R.string.jobs_canceled), Snackbar.LENGTH_LONG).show()
        }
    }

}
