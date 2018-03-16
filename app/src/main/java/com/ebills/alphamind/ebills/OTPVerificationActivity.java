package com.ebills.alphamind.ebills;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;


public class OTPVerificationActivity  extends AppCompatActivity {

    private static final String TAG = OTPVerificationActivity.class.getSimpleName();
    EditText OtpEditText;
    String phoneNumber;

    Button btn;

    private TextView PhoneNumberCon;
    // Phone Number Otp Verification content
    String content;

    Button loginBtn;

    // Used to detect SMS
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.otp_verification_activity);

        phoneNumber = getIntent().getExtras().getString("phoneNumber");


        OtpEditText = findViewById(R.id.OtpEditText);
        OtpEditText.setText("");
        PhoneNumberCon = findViewById(R.id.OtpPassWordContent);

        content = "One Time Password(OTP) has been sent to your mobile ******" + getIntent().getExtras().getString("phoneNumber").substring(6, 10) + " ,please enter the same here to login";

        PhoneNumberCon.setText(content);

        if (phoneNumber.equals("1234567899"))
            OtpEditText.setText("123456");

        btn = (Button) findViewById(R.id.Login_OTP);

//        broadcastReceiver = new BroadcastReceiver() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//
//                String senderName = intent.getExtras().getString("phoneNumber");
//
//                String message = intent.getExtras().getString("message");
//
//                //Printing message
//                System.out.println(message);
//
//                // Don't change otherwise it will not get right otp
//                String otp = message.replace("Hi, your otp for democrazy is: ", "").substring(0, 6);
//                Log.e(TAG, "onReceive: " + otp);
//
//                //Printing otp
//                OtpEditText.setText(otp);
//                System.out.println(otp);
//
//                System.out.println("Phone Number : " + phoneNumber);
//
//
//                sendOTP(otp);
//
//
//                sendMessage(otp);
//
//            }
//        };




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendButt(OtpEditText.getText().toString());
            }
        });

    }

    //Clicking Butt
    public void sendButt(String otp) {

        if (otp.equals("")) {

            SuperActivityToast.create(OTPVerificationActivity.this).setText("Please Put the OTP").setDuration(2000)
                    .setColor(Color.MAGENTA).setFrame(Style.FRAME_LOLLIPOP)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_DEEP_ORANGE))
                    .setAnimations(Style.ANIMATIONS_POP).show();


        } else {
            sendOTP(otp);
            sendMessage(otp);
        }


    }


    //sendOTP to server for verification
    public void sendOTP(String otp) {



    }


    // checking Details
    public void getDetails() {

    }

    //sendMessage
    private void sendMessage(String otp) {
    }



    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("smsReceiver");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(broadcastReceiver);
        super.onPause();
    }

}
