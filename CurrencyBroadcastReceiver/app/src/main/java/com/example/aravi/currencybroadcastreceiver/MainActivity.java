package com.example.aravi.currencybroadcastreceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText amount;
    private EditText currency;
    private Button approveButton;
    private String currencystr;
    private Double amountDou;
    private Double convertedAmt;
    private Button rejectButton;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(this,"on create",Toast.LENGTH_SHORT).show();
        amount = (EditText)findViewById(R.id.amountId);
        currency = (EditText) findViewById(R.id.currencyId);
        approveButton = (Button) findViewById(R.id.approveButtonId);
        rejectButton = (Button) findViewById(R.id.rejectButtonId);

        currencystr = getIntent().getStringExtra("currency");
        amountDou = getIntent().getDoubleExtra("amount",1.0);
        amount.setText(String.valueOf(getIntent().getDoubleExtra("amount",1.0)));
        currency.setText(getIntent().getStringExtra("currency"));

        View.OnClickListener approveButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(currencystr.equalsIgnoreCase("pound")){
                        convertedAmt= amountDou * 0.74;
                    }
                    else if(currencystr.equalsIgnoreCase("rupee")){
                        convertedAmt= amountDou * 64.71;
                    }
                    else{
                        convertedAmt = amountDou * 0.84;
                    }

                Intent intent = new Intent();
                intent.setAction("com.havok.broadcastSender");
                intent.putExtra("convertedAmount",convertedAmt);
                intent.putExtra("isApproved",true);
                intent.putExtra("status",1);
                sendBroadcast(intent);
                finish();
                Log.d(TAG, "onClick: broadcast sent back to sender");
            }
        };

        View.OnClickListener rejectButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.havok.broadcastSender");
                intent.putExtra("isApproved",false);
                intent.putExtra("status",2);
                sendBroadcast(intent);
                finish();
            }
        };

        rejectButton.setOnClickListener(rejectButtonListener);
        approveButton.setOnClickListener(approveButtonListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart: in onStart");

    }
}
