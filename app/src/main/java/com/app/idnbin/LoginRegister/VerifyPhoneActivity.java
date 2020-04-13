package com.app.idnbin.LoginRegister;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.app.idnbin.util.GlobalConstants;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends BaseActivity {

    private ConstraintLayout Cl_Verify;
    private PinView pinView;
    private TextView Tv_ResendCode, Tv_Timer;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private String mobile, mobileCode;
    String AndroidId, DeviceId, IMEI, meid, serialNumber, networkCountryIso, simCountryIso, networkOperatorName, simOperatorName, line1Number, model,
            device, brand_Id, manufacturer, product, type, host, hardware, base_OS, codename, release, security_Patch, incrimental;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        Cl_Verify = findViewById(R.id.Cl_Verify);
        pinView = findViewById(R.id.pinView);
        Button btn_Continue = findViewById(R.id.Btn_Continue);
        Tv_ResendCode = findViewById(R.id.Tv_ResendCode);
        Tv_Timer = findViewById(R.id.Tv_Timer);

        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        mobileCode = intent.getStringExtra("mobileCode");
        sendVerificationCode(mobile, mobileCode,mCallbacks);
        startCounter();

        btn_Continue.setOnClickListener(v -> {
            String code = pinView.getText().toString().trim();
            if (code.isEmpty() || code.length() < 6) {
                pinView.setError("Enter valid code");
                pinView.requestFocus();
                return;
            } else {
                verifyNumber(mAuth,mVerificationId,code,mobileCode,mobile,Cl_Verify,"VerifyPhoneActivity");
            }

        });

        Tv_ResendCode.setOnClickListener(v -> {
            sendVerificationCode(mobile, mobileCode,mCallbacks);
            startCounter();
        });

        retrieveDeviceData();
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    void retrieveDeviceData() {
        if (isPermissionGranted(this, Manifest.permission.READ_PHONE_STATE)) {

            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            if (telephonyManager != null) {

                if (Build.VERSION.SDK_INT >= 26) {
                    AndroidId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
                } else {
                    AndroidId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
                    DeviceId = telephonyManager.getDeviceId();
                }

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        meid = String.valueOf(telephonyManager.getMeid());
                    }
                }
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                    IMEI = String.valueOf(telephonyManager.getDeviceId());
                }

                line1Number = "";
                networkCountryIso = telephonyManager.getNetworkCountryIso();
                simCountryIso = telephonyManager.getSimCountryIso();
                networkOperatorName = telephonyManager.getNetworkOperatorName();
                simOperatorName = telephonyManager.getSimOperatorName();

            } else {
                networkCountryIso = "";
                simCountryIso = "";
            }

            model = Build.MODEL;
            device = Build.DEVICE;
            brand_Id = Build.ID;
            manufacturer = Build.MANUFACTURER;
            product = Build.PRODUCT;
            type = Build.TYPE;
            host = Build.HOST;
            hardware = Build.HARDWARE;
            serialNumber = "";
            base_OS = "";
            codename = Build.VERSION.CODENAME;
            release = Build.VERSION.RELEASE;
            security_Patch = "";
            incrimental = Build.VERSION.INCREMENTAL;
        }
    }

    private void startCounter() {
        new CountDownTimer(21000, 1000) {

            public void onTick(long millisUntilFinished) {
                Tv_Timer.setVisibility(View.VISIBLE);
                Tv_ResendCode.setVisibility(View.GONE);
                Tv_Timer.setText("Seconds Remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Tv_Timer.setVisibility(View.GONE);
                Tv_ResendCode.setVisibility(View.VISIBLE);
            }

        }.start();


    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                pinView.setText(code);
                //verifying the code
                verifyVerificationCode(mAuth,mVerificationId,code,mobileCode,mobile,Cl_Verify,"VerifyPhoneActivity");
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.d("KALAALA", "" + e.getMessage());
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            mVerificationId = s;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void verifyNumber(FirebaseAuth mAuth, String mVerificationId, String code, String mobileCode, String mobile, ViewGroup Cl_Verify, String activityName) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        if (activityName.equalsIgnoreCase("Codeactivity")) {
            SharedPreferences shared = getSharedPreferences("LoginDetails", MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            editor.putString("Phone", mobile);
            editor.putString("phoneCode", mobileCode);
            editor.putString("securityType", "2Auth");
            editor.putString("securityCode", "");
            editor.apply();
        }

        phoneLogin(mAuth, credential, mobile, Cl_Verify, activityName);
    }

    private void phoneLogin(FirebaseAuth mAuth, PhoneAuthCredential credential, String mobile, ViewGroup Cl_Verify, String activityName) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        setPref(this, "LoginSuccess", "true");
                        setPref(this, "NormalLogin", "false");
                        startActivity(new Intent(this, HomeActivity.class));
                        this.finishAffinity();
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        if (firebaseUser != null) {
                            UserDetails user = new UserDetails(firebaseUser.getUid(), firebaseUser.getEmail(), "", mobile, currentDateTimeString, "", "2Auth", "","");
                            GlobalConstants.UsersInstance.child(firebaseUser.getUid()).setValue(user);
                            DeviceDetails deviceDetails = new DeviceDetails(AndroidId, DeviceId, IMEI, meid, serialNumber, networkCountryIso, simCountryIso, networkOperatorName, simOperatorName, line1Number, model, device, brand_Id, manufacturer, product, type, host, hardware, base_OS, codename, release, security_Patch, incrimental);
                            GlobalConstants.DeviceDetailsInstance.child(firebaseUser.getUid()).setValue(deviceDetails);
                        }

                        if (task.getResult() != null) {
                            FirebaseUser user = task.getResult().getUser();

                            if (user != null) {
                                GlobalConstants.UsersInstance.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                                        if (!dataSnapshot.exists()) {
                                            updateUI(user);
                                        } else {
                                            updateUI(user);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }


                    } else {
                        if (task.getException() != null) {
                            snackBar(Cl_Verify, "" + task.getException().getMessage());
                        }
                    }
                });
    }

}
