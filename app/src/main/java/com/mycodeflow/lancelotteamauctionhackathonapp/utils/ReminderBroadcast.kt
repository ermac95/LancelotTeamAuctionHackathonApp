package com.mycodeflow.lancelotteamauctionhackathonapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.mycodeflow.lancelotteamauctionhackathonapp.R

class ReminderBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        when (intent?.action) {
            Intent.ACTION_VIEW -> {
                val id = intent.data?.lastPathSegment
                if (id != null && context != null) {
                    AndroidNotifications().show(context, id)
                }
            }
        }

    }
}


