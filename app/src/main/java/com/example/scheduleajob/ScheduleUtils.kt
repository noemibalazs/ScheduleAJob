package com.example.scheduleajob

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.widget.Toast

class ScheduleUtils() {

    companion object{

        fun createSchedule(context: Context){
            val component = ComponentName(context, NotificationJobService::class.java)
            val jobInfo = JobInfo.Builder(9, component)
            jobInfo.setMinimumLatency(30 * 1000L)
            jobInfo.setOverrideDeadline(120 * 1000L)
            val scheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.schedule(jobInfo.build())
            Toast.makeText(context, context.getString(R.string.job_scheduled), Toast.LENGTH_LONG).show()
        }

        fun stopSchedule(context: Context){
            val scheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.cancelAll()
        }
    }
}