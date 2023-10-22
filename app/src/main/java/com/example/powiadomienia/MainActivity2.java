package com.example.powiadomienia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        Bundle bundle = RemoteInput.getResultsFromIntent(intent);

        if(bundle != null){
            String text = bundle.getString(MainActivity.key);
            TextView textView = findViewById(R.id.textView);
            textView.setText(text);
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.cancel(MainActivity.id);
    }
}