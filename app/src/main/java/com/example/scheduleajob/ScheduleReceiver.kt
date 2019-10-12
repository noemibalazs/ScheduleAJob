package com.example.scheduleajob

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ScheduleReceiver : BroadcastReceiver(){

    override fun onReceive(cont: Context?, data: Intent?) {
        val action = data?.action
        if (action.equals(NotificationJobService.NOTIFICATION_ACTION)){
            ScheduleUtils.createSchedule(cont!!)
        }
    }
}