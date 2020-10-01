package com.example.firebasecloudmessaging;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;

    String channelId = "com.example.firebasecloudmessaging";
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        createNotificationChannel(channelId, "firebasecloudmessaging", "Mi channel");
    }

    private void createNotificationChannel (String id, String name, String description){

        Intent intent = new Intent(this, ResultActivity.class);


        NotificationChannel channel = new NotificationChannel(id,name,NotificationManager.IMPORTANCE_LOW);
        channel.setDescription(description);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 300, 200, 400});

        notificationManager.createNotificationChannel(channel);
    }

    private void sendNotification(){
        int id = 100;

        Intent intent = new Intent(this, ResultActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Mi titulo")
                .setContentText("Esto es una notificacion local creada por la misma app")
                .setChannelId(channelId)
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.ic_dialog_info, "Abrir", pendingIntent)
                .build();

        notificationManager.notify(id, notification);
    }
}