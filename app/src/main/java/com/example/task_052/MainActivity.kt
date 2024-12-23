package com.example.task_052

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.task_052.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var notificationManager: NotificationManager? = null

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val CHANNEL_ID = "channelID"
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, "Уведомление", importance)
            notificationManager = applicationContext.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }

        binding.notification1BTN.setOnClickListener{
            val link = "https://urban-unversity.ru/"
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            val pendingIntent = PendingIntent.getActivity(this, 0, webIntent, PendingIntent.FLAG_IMMUTABLE)

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.sunny_ic)
                .setContentTitle("Simple Notification")
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_birthday_big))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(applicationContext)) {
                if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                    return@setOnClickListener
                }
                notify(1, builder.build())
            }
        }

        binding.notification2BTN.setOnClickListener{
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.sunny_ic)
                .setContentTitle("BigTextStyle Notification")
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText("Когда кормить будут? \n Далее идёт очень длинный текст \n про бедного котика, " +
                            "которого морят голодом \n уже целых три минуты"))
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_birthday_big))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(applicationContext)) {
                if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                    return@setOnClickListener
                }
                notify(1, builder.build())
            }
        }

        binding.notification3BTN.setOnClickListener{
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.sunny_ic)
                .setContentTitle("BigPictureStyle Notification")
                .setContentText("Скоро стажировка.")
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_birthday_big))
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.ic_birthday_big)) // Указываем большое изображение
                        .bigLargeIcon(null as Bitmap?)
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(applicationContext)) {
                if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                    return@setOnClickListener
                }
                notify(1, builder.build())
            }
        }

        binding.notification4BTN.setOnClickListener{
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.sunny_ic)
                .setContentTitle("InboxStyle Notification")
                .setStyle(
                    NotificationCompat.InboxStyle()
                        .addLine("This is line 1")
                        .addLine("This is line 2")
                        .addLine("This is line 3")
                        .addLine("This is line 4")
                        .addLine("This is line 5")
                        .addLine("This is line 6")
                        .addLine("This is line 7")
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(applicationContext)) {
                if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                    return@setOnClickListener
                }
                notify(1, builder.build())
            }
        }

        binding.notification5BTN.setOnClickListener{
            val notificationIntent = Intent(this@MainActivity, SecondActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this@MainActivity, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.sunny_ic)
                .setContentTitle("Action Notification")
                .addAction(R.drawable.open_in_icon, "Открыть", pendingIntent)
                    .addAction(R.drawable.refresh_icon, "Отказаться",
                        PendingIntent.getActivity(
                            this@MainActivity,
                            0,
                            Intent(this@MainActivity, MainActivity::class.java),
                            PendingIntent.FLAG_IMMUTABLE
                        )
                    )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(applicationContext)) {
                if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                    return@setOnClickListener
                }
                notify(1, builder.build())
            }
        }

    }
}

