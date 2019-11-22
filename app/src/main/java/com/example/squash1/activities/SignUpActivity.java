package com.example.squash1.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.squash1.R;
import com.example.squash1.SharedPreferencesManager;
import com.example.squash1.model.SignUpResponse;
import com.example.squash1.network.ServiceClient;
import com.example.squash1.network.ServiceGenerator;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.rengwuxian.materialedittext.MaterialEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "squash";
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth fAuth;

    MaterialEditText nameRegis, emailRegis, passRegis, confRegis;
    Button btnRegister;
    ProgressDialog loading;

    Context context;
    ServiceClient serviceClient;
    SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        context = this;
        serviceClient = ServiceGenerator.createService(ServiceClient.class);

        sharedPreferencesManager = new SharedPreferencesManager(this);
        if (sharedPreferencesManager.getSpSigned()) {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }


        fAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        findViewById(R.id.signup_with_google).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        dataComponents();


    }

    private void dataComponents() {
        nameRegis = findViewById(R.id.name_sign_up);
        emailRegis = findViewById(R.id.email_sign_up);
        passRegis = findViewById(R.id.pass_sign_up);
        confRegis = findViewById(R.id.confirm_sign_up);
        btnRegister = findViewById(R.id.btn_register_sign_up);
        confRegis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = passRegis.getText().toString();
                if (s.length() > 0 && password.length() > 0) {
                    if (!confRegis.equals(password)) {
                        Toast.makeText(SignUpActivity.this, "Not Match Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(context, null, "Wait...", true, false);
                final String nama = nameRegis.getText().toString().trim();
                String email = emailRegis.getText().toString().trim();
                String password = passRegis.getText().toString().trim();
                Call<SignUpResponse> request = serviceClient
                        .registerUser("register", "insert", nama, email, password);
                request.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        if (response.isSuccessful()) {
                            Log.i("debug", "onResponse: Insert Data Success");
                            loading.dismiss();
                            startActivity(new Intent(context, GenreActivity.class));
                            finish();
                        } else {
                            Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(context, "Koneksi Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (fAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //out
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:Success");
//                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(SignUpActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn() {
        Intent signIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signIntent, RC_SIGN_IN);
    }
}
