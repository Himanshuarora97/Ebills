package com.ebills.alphamind.ebills;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by anmol on 16/3/18.
 */

public class NewAccountActivity extends AppCompatActivity {


    private EditText phoneNumber;
    private EditText password;
    private EditText email;
    private Button otpBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_account_login_activity);

        // Initialize
        Initialize();


        otpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pn = phoneNumber.getText().toString();
                String pass = password.getText().toString();
                String em = email.getText().toString();

                sendDetails(pn , pass , em);

            }
        });

    }

    //Initialize
    private void Initialize(){
        phoneNumber = findViewById(R.id.UnregPhoneNumber);
        password = findViewById(R.id.Create_Password);
        email = findViewById(R.id.Email_id);
        otpBtn = findViewById(R.id.SubmitUnReg);
    }

    //Send Details to server
    private void sendDetails(String pn, String pass, String em){

        if (pn.equals("") || pass.equals("") || em.equals("")){
            // Empty Warning
        }

        else{
            // Send the details to the server

            boolean ok = false;

            if (ok){
                Intent i =new Intent(NewAccountActivity.this , OTPVerificationActivity.class);
                i.putExtra("phoneNumber" , pn);
                startActivity(i);
            }

        }
    }
}
