package com.example.aravi.currencybroadcastsender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText amountTextField;
    private Button convertButton;
    private String currencyType;
    private Spinner spinner;
    private Double amount;
    public static int status = 0;
    public static boolean isApproved;
    public static double convertedAmount;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextField = (EditText) findViewById(R.id.amountFieldId);
         spinner = (Spinner) findViewById(R.id.currencyDropdownId);

        convertButton = (Button) findViewById(R.id.convertButtonId);

        View.OnClickListener convertButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: sending broadcast"+"Amount: "+amountTextField.getText()+"Currency"+currencyType);
                if(!amountTextField.getText().toString().equals("")){
                    currencyType =  spinner.getSelectedItem().toString();
                    amount= Double.parseDouble(amountTextField.getText().toString());
                    Intent intent = new Intent();
                    intent.setAction("com.havok.broadcastReceiver");
                    intent.putExtra("amount",amount);
                    intent.putExtra("currencyType",currencyType);
                    sendBroadcast(intent);
                    Log.d(TAG, "onClick: Sent a broadcast");
                }
              else
                {
                    Toast.makeText(MainActivity.this,"Please enter the amount to be converted", Toast.LENGTH_SHORT).show();
                }

            }
        };
        convertButton.setOnClickListener(convertButtonListener);




    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: In resume");
        //Toast.makeText(this,"Onresume"+"Is Approved:"+isApproved+"status:"+status,Toast.LENGTH_SHORT).show();
        if (isApproved && status == 1) {
         // Toast.makeText(this,"Converted  Amount: "+MainActivity.convertedAmount,Toast.LENGTH_SHORT).show();
            TextView textView = (TextView) findViewById(R.id.statusId);
            textView.setText("Dollar amount $" + amount + " converted to " + convertedAmount + " " + currencyType);
            amountTextField.clearFocus();
        }
        else if(!isApproved && status == 2){
            //Toast.makeText(this,"Rejected",Toast.LENGTH_SHORT).show();
            TextView textView = (TextView) findViewById(R.id.statusId);
            textView.setText("Conversion Rejected");
            amountTextField.clearFocus();
        }
    }
}
