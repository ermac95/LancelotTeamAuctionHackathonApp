package com.mycodeflow.lancelotteamauctionhackathonapp.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.mycodeflow.lancelotteamauctionhackathonapp.MainActivity
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement


interface Notifications {
    fun initialize(context: Context)
    fun show(context: Context, adId: String)
    fun dismiss(adId: String)
    fun dismissAll()
}

class AndroidNotifications : Notifications {

    companion object {
        private const val CHANNEL_AUCTION_START = "AuctionStartChannel"
        const val AUCTION_START_TAG = "AuctionStartTag"
        private const val REQUEST_CODE = 1
    }

    private lateinit var notificationManager: NotificationManagerCompat

    override fun initialize(context: Context) {
        notificationManager = NotificationManagerCompat.from(context)
        if (notificationManager.getNotificationChannel(CHANNEL_AUCTION_START) == null) {
            val notificationChannel = NotificationChannelCompat.Builder(
                CHANNEL_AUCTION_START,
                NotificationManagerCompat.IMPORTANCE_HIGH
            )
                .setName(context.resources.getString(R.string.auction_start_channel_name))
                .setDescription(context.resources.getString(R.string.auction_start_channel_description))
                .build()

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    override fun show(context: Context, adId: String) {
        initialize(context)
        showNotification(context, adId)
    }

    override fun dismiss(adId: String) {
        notificationManager.cancel(AUCTION_START_TAG, adId.hashCode())
    }

    override fun dismissAll() {
        notificationManager.cancelAll()
    }

    private fun showNotification(context: Context, adId: String) {
        val contentUri = "com.mycodeflow.lancelotteamauctionhackathonapp://ad/${adId}".toUri()
        //val poster = Picasso.get().load(movie.poster).resize(100, 100).get()

        val notification = NotificationCompat.Builder(context, CHANNEL_AUCTION_START)
            .setContentTitle(context.resources.getString(R.string.notification_text))// TODO replace it
            .setSmallIcon(R.drawable.ic_gavel)
            //.setLargeIcon(poster)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    REQUEST_CODE,
                    Intent(context, MainActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setData(contentUri),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .build()

        notificationManager.notify(
            AUCTION_START_TAG,
            adId.hashCode(),
            notification
        )
    }
}