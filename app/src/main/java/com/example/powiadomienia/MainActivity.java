package com.example.powiadomienia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String CHANNEL_ID = "1";
    protected static int id = 1;
    protected static String key = "rm1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(view ->{
            Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Powiadomienie")
                    .setContentText("Treść powiadomienia")
                    .build();

            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0, notification);

        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                    android.R.drawable.sym_action_chat, "Otwórz", pendingIntent
            ).build();

            Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Powiadmoenie")
                    .setContentText("Treść powiadomienia")
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentIntent(pendingIntent)
                    .addAction(action)
                    .build();

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.notify(id, notification);

        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(view -> {
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            String[] messages = {"Zdarzenie 1", "Zdarzenie 2", "Zdarzenie 3", "Zdarzenie 4"};
            for(String s: messages)
                inboxStyle.addLine(s);

            Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Powiadomienie")
                    .setContentText("Wiadmość")
                    .setStyle(inboxStyle)
                    .build();

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.notify(2, notification);
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            RemoteInput remoteInput = new RemoteInput.Builder(key)
                    .setLabel("Odpowiedz")
                    .build();

            NotificationCompat.Action replayAction = new NotificationCompat.Action.Builder(
                    android.R.drawable.sym_action_chat, "Odpowiedz", pendingIntent
            )
                    .addRemoteInput(remoteInput)
                    .build();

            Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Powiadomienie")
                    .setContentText("Treść powiadomienia")
                    .addAction(replayAction)
                    .build();

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.notify(id, notification);


        });
    }

    private void createNotificationChannel(){
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "channel"+CHANNEL_ID,
                NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}