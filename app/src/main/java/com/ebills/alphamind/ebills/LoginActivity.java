package com.ebills.alphamind.ebills;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity{


        private static final String TAG = LoginActivity.class.getSimpleName();
        EditText editText;
        Button submitButton;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_activity);

            // initViews
            initViews();

            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("1234567899")
                    .setContentText("use above number for demo purpose ")
                    .setConfirmText("Ok")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
            // Add Listener to button
            submitButton.setOnClickListener(submitListener);
        }

        public void initViews() {
            editText = (EditText) findViewById(R.id.PhoneNumber);
            submitButton = (Button) findViewById(R.id.SubmitPhoneNumber);
            String text = editText.getText().toString();
            Log.e(TAG, "initViews: " + text);
            editText.setText("");
        }

        View.OnClickListener submitListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String number = editText.getText().toString();
                Log.d(TAG, "submitListener : phoneNumber :  " + number);
            }
        };

}
