package com.riskyo.moarkitproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button btndaftar,lo;
    EditText daftarnamapengguna, daftarnamalengkap, daftarkatasandi, daftaremail, daftarnotelp, daftarkodeotp;
    private FirebaseAuth mAuth;
    private String verificationId;
    private Button verifyOTPBtn, generateOTPBtn;

    Button btnverifyCaptcha;
    String SITE_KEY = "6Ldgg1IdAAAAAPpH4K7ykte7IGBtIHeqvYLFn8HC";
    String SECRET_KEY = "6Ldgg1IdAAAAAKGD5tpnOB5Jonmb_0jBErU-3vr7";
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getSupportActionBar().hide();

        generateOTPBtn = findViewById(R.id.btnotp);

        daftarnamapengguna = findViewById(R.id.daftarnamapengguna);
        daftarnamalengkap = findViewById(R.id.daftarnamalengkap);
        daftarkatasandi = findViewById(R.id.daftarkatasandi);
        daftarnotelp = findViewById(R.id.daftarnotelp);
        daftaremail = findViewById(R.id.daftaremail);
        daftarkodeotp = findViewById(R.id.daftarkodeotp);

        queue = Volley.newRequestQueue(getApplicationContext());

        lo = (Button) findViewById(R.id.btnye);
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "lolo", Toast.LENGTH_SHORT).show();
            }
        });

        btnverifyCaptcha = (Button) findViewById(R.id.btnha);
       btnverifyCaptcha.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(RegisterActivity.this,activity_login.class);
               startActivity(intent);
               Toast.makeText(RegisterActivity.this, "yew", Toast.LENGTH_SHORT).show();

           }
       });

        generateOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is for checking weather the user
                // has entered his mobile number or not.
                if (TextUtils.isEmpty(daftarnotelp.getText().toString())) {
                    // when mobile number text field is empty
                    // displaying a toast message.
                    Toast.makeText(RegisterActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    // if the text field is not empty we are calling our
                    // send OTP method for getting OTP from Firebase.
                    String phone = "+62" + daftarnotelp.getText().toString();
                    sendVerificationCode(phone);
                }
            }
        });


        btndaftar = (Button) findViewById(R.id.btndaftar3);
        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(daftarkodeotp.getText().toString())) {
                    // if the OTP text field is empty display
                    // a message to user to enter OTP

                    Toast.makeText(RegisterActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    // if OTP field is not empty calling
                    // method to verify the OTP.
//                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    verifyGoogleReCAPTCHA();
                    verifyCode(daftarkodeotp.getText().toString());

                }

                String email = daftaremail.getText().toString();
                String pass = daftarkatasandi.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(RegisterActivity.this, "Email Betul", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(RegisterActivity.this, "Email Salah", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(pass) || pass.length() < 8) {
                    Toast.makeText(RegisterActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                } else {


//              HomeBalik();
                }
            }

            ;




        });
    }

    private void verifyGoogleReCAPTCHA() {

        // below line is use for getting our safety
        // net client and verify with reCAPTCHA
        SafetyNet.getClient(this).verifyWithRecaptcha(SITE_KEY)
                // after getting our client we have
                // to add on success listener.
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                        // in below line we are checking the response token.
                        if (!response.getTokenResult().isEmpty()) {
                            // if the response token is not empty then we
                            // are calling our verification method.
                            handleVerification(response.getTokenResult());
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // this method is called when we get any error.
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            // below line is use to display an error message which we get.
                            Log.d("TAG", "Error message: " +
                                    CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                        } else {
                            // below line is use to display a toast message for any error.
                            Toast.makeText(RegisterActivity.this, "Error found is : " + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    protected void handleVerification(final String responseToken) {
        // inside handle verification method we are
        // verifying our user with response token.
        // url to sen our site key and secret key
        // to below url using POST method.
        String url = "https://www.google.com/recaptcha/api/siteverify";

        // in this we are making a string request and
        // using a post method to pass the data.
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // inside on response method we are checking if the
                        // response is successful or not.
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("success")) {
                                // if the response is successful then we are
                                // showing below toast message.
                                RegisterModel registerModel = new RegisterModel();
                                registerModel.setLevel(daftarnamapengguna.getText().toString());
                                registerModel.setRelasi(daftarnamalengkap.getText().toString());
                                registerModel.setPassword(daftarkatasandi.getText().toString());
                                registerModel.setEmail(daftaremail.getText().toString());
                                Register(registerModel);
                                Toast.makeText(RegisterActivity.this, "User verified with reCAPTCHA", Toast.LENGTH_SHORT).show();
                            } else {
                                // if the response if failure we are displaying
                                // a below toast message.
                                Toast.makeText(getApplicationContext(), String.valueOf(jsonObject.getString("error-codes")), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            // if we get any exception then we are
                            // displaying an error message in logcat.
                            Log.d("TAG", "JSON exception: " + ex.getMessage());
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // inside error response we are displaying
                        // a log message in our logcat.
                        Log.d("TAG", "Error message: " + error.getMessage());
                    }
                }) {
            // below is the getParamns method in which we will
            // be passing our response token and secret key to the above url.
            @Override
            protected Map<String, String> getParams() {
                // we are passing data using hashmap
                // key and value pair.
                Map<String, String> params = new HashMap<>();
                params.put("secret", SECRET_KEY);
                params.put("response", responseToken);
                return params;
            }
        };
        // below line of code is use to set retry
        // policy if the api fails in one try.
        request.setRetryPolicy(new DefaultRetryPolicy(
                // we are setting time for retry is 5 seconds.
                50000,

                // below line is to perform maximum retries.
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // at last we are adding our request to queue.
        queue.add(request);
    }

    public void Register(RegisterModel registerModel) {
        Call<GetRegister> registerCall = apiClientLogin.loginRegInterfaace().register(registerModel);
        registerCall.enqueue(new Callback<GetRegister>() {
            @Override
            public void onResponse(Call<GetRegister> call, Response<GetRegister> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(RegisterActivity.this, activity_login.class));
                    finish();

                } else {
                    String pesan1 = "gagal";
                    Toast.makeText(RegisterActivity.this, pesan1, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
//                            Intent i = new Intent(RegisterActivity.this, HomeUtamaActivity.class);

//                            startActivity(i);

                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                daftarkodeotp.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
            }
        }


        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void verifyCode(String code) {
        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }


}

