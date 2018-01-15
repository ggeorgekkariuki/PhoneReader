package com.example.android.phoneread;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    public TextView emailTextView;
    public TextView IMEITextView;
    public TextView phoneNumbersView;
    public TextView simSerialView;
    public TextView networkView;
    public int emailCount = 1;
    public int phoneNumberCount = 1;
    public int IMEICount = 1;
    public int networkCount = 1;
    public int simSerialCount = 1;
    public String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //Get the emails from the Google Accounts in the phone
//        emailTextView = (TextView) findViewById(R.id.emailView);
//
//        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
//        Account[] accounts = AccountManager.get(this).getAccounts();
//
//        for(int i = 0; i < accounts.length; i++ ){
//           // Log.d("Accounts are", String.valueOf(accounts[i]));

//        }
//
//        for (Account account : accounts) {
//            if (emailPattern.matcher(account.name).matches()) {
//                possibleEmail = account.name;
//
//            }
//        }

        //emailTextView.setText(phoneNumber);

        // Get the Google Email details from the phone
        emailTextView = (TextView) findViewById(R.id.emailView);

        AccountManager am = AccountManager.get(this);
        Account[] phoneAccounts = am.getAccounts();

        HashMap<String, String> hashMap = new HashMap<>();


        ArrayList<String> googleAccounts = new ArrayList<String>();

        for (Account ac : phoneAccounts) {
            String acname = ac.name;
            String actype = ac.type;
            // Take your time to look at all available accounts

            if (actype.equals("com.google")) {
                email = ac.name;
                Log.d("Google Accounts : ", email);

                hashMap.put("email_address" + emailCount, email);
                emailCount++;
            }


        }

        Log.d("Hash map Accounts", String.valueOf(hashMap));


        emailTextView.setText(hashMap.get("email_address1"));


//        HashMap<Integer, String> telephoneHash = new HashMap<>();
//        int telIndex = 1;

        phoneNumbersView = (TextView) findViewById(R.id.phoneNumberView);
        TelephonyManager tm = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        //---get the phone number---
        String telNumber = tm.getLine1Number();
        if(telNumber.trim().length() == 0){
            hashMap.put("phone_number" + phoneNumberCount, "No number");
            phoneNumberCount++;
        } else {
            hashMap.put("phone_number" + phoneNumberCount, telNumber);
            phoneNumberCount++;
        }
        Log.d("Hash map Accounts", String.valueOf(hashMap));

        phoneNumbersView.setText(hashMap.get("phone_number1"));
////        if (telNumber.length() < 10)
////            Toast.makeText(this, "Phone number: UNKNOWN",
////                    Toast.LENGTH_LONG).show();
////        else
////            Toast.makeText(this, "Phone number: " + telNumber,
////                    Toast.LENGTH_LONG).show();
////        //---get the IMEI number---

//
        IMEITextView = (TextView) findViewById(R.id.IMEIView);
        String IMEI = tm.getDeviceId();
        if(IMEI.length() != 0){
            hashMap.put("imei_number" + IMEICount, tm.getDeviceId());
            IMEICount++;
        }
        Log.d("Hash map Accounts", String.valueOf(hashMap));
        IMEITextView.setText(hashMap.get("imei_number1"));

//
//
//
//        if (IMEI != null)
//            Toast.makeText(this, "IMEI number: " + IMEI,
//                    Toast.LENGTH_LONG).show();
        //---get Service provider
        networkView = (TextView) findViewById(R.id.serviceProviderView);
        String serviceProvider = tm.getNetworkOperatorName();
        hashMap.put("network_provider" + networkCount, serviceProvider);
        networkCount++;
        Log.d("Hash map Accounts", String.valueOf(hashMap));
        networkView.setText(hashMap.get("network_provider1"));

        simSerialView = (TextView) findViewById(R.id.simSerialNumberView);
        hashMap.put("sim_serial_number"+simSerialCount, tm.getSimSerialNumber());
        simSerialCount++;
        simSerialView.setText(hashMap.get("sim_serial_number1"));

//
//        Toast.makeText(this, "Network operator: " + serviceProvider,
//                Toast.LENGTH_LONG).show();
//


    }

}

