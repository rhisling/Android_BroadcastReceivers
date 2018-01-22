package com.example.aravi.currencybroadcastsender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

            MainActivity.isApproved = intent.getBooleanExtra("isApproved",false);;
            MainActivity.status=  intent.getIntExtra("status",0);;
            MainActivity.convertedAmount = intent.getDoubleExtra("convertedAmount", 0);
            //Toast.makeText(context, "Converted  Amount: " + MainActivity.convertedAmount+"Status: "+MainActivity.status, Toast.LENGTH_SHORT).show();

    }
}
