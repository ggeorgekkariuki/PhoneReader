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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    public TextView emailTextView;
    public TextView phoneNumberTextView;
    public String phoneNumber;
    public String possibleEmail;

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

        HashMap<Integer, String> hashMap = new HashMap<>();


        ArrayList<String> googleAccounts = new ArrayList<String>();
        int index = 1;
        for (Account ac : phoneAccounts) {
            String acname = ac.name;
            String actype = ac.type;
            // Take your time to look at all available accounts

                if(actype.equals("com.google")) {
                    phoneNumber = ac.name;
                    Log.d("Google Accounts : ", phoneNumber);

                    hashMap.put(index, phoneNumber);
                    index++;
                }


            }

            Log.d("Hash map Accounts", String.valueOf(hashMap));


        emailTextView.setText(hashMap.get(0));
        }


//
//        TelephonyManager tm = (TelephonyManager)
//                getSystemService(Context.TELEPHONY_SERVICE);
//        //---get the phone number---
//        String telNumber = tm.getLine1Number();
//        if (telNumber.length() < 10)
//            Toast.makeText(this, "Phone number: UNKNOWN",
//                    Toast.LENGTH_LONG).show();
//        else
//       Toast.makeText(this, "Phone number: " + telNumber,
//                    Toast.LENGTH_LONG).show();
//        //---get the IMEI number---
//        String IMEI = tm.getDeviceId();
//        if (IMEI != null)
//            Toast.makeText(this, "IMEI number: " + IMEI,
//                    Toast.LENGTH_LONG).show();
//        //---get Service provider
//        String serviceProvider = tm.getNetworkOperatorName();
//        Toast.makeText(this, "Network operator: " + serviceProvider,
//                Toast.LENGTH_LONG).show();


    }

//    public String getUniqueID() {
//        String myAndroidDeviceId = "";
//        TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            if (mTelephony.getLine1Number() != null) {
//                myAndroidDeviceId = mTelephony.getLine1Number();
//            } else {
//                myAndroidDeviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
//            }
//        }
//        return myAndroidDeviceId;
//    }
//
//    public interface IOnlyOwnerSimSupport{
//
//    }

