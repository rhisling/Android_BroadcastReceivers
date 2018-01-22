package com.example.aravi.currencybroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Double amount = intent.getDoubleExtra("amount",1.0);

        String currency = intent.getStringExtra("currencyType");
       // Toast.makeText(context,"Amount: "+amount+"Currency: "+currency,Toast.LENGTH_SHORT).show();

        Intent intentCurrent = new Intent();
        intentCurrent.setClassName("com.example.aravi.currencybroadcastreceiver","com.example.aravi.currencybroadcastreceiver.MainActivity");
        intentCurrent.putExtra("amount",amount);
        intentCurrent.putExtra("currency",currency);
        context.startActivity(intentCurrent);
    }
}
