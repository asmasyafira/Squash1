package com.example.squash1.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.squash1.R;
import com.example.squash1.SharedPreferencesManager;
import com.example.squash1.model.SignInResponse;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.rengwuxian.materialedittext.MaterialEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "squash";
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth fAuth;

    Button btnMasuk;
    MaterialEditText etNameUser, etPassUser;
    ProgressDialog progressDialog;

    SharedPreferencesManager sharedPreferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etNameUser = findViewById(R.id.et_name_user);
        etPassUser = findViewById(R.id.et_pass_user);
        sharedPreferencesManager = new SharedPreferencesManager(this);

        if (sharedPreferencesManager.getSpSigned()) {
            startActivity(new Intent(SignInActivity.this, GenreFragment.class));
            finish();
        }

        progressDialog = new ProgressDialog(this);
        btnMasuk = findViewById(R.id.btn_masuk);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                if (etNameUser.getText().toString().isEmpty()){
                    progressDialog.dismiss();
                    Toast.makeText(SignInActivity.this, "Nama pengguna tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etPassUser.getText().toString().isEmpty()){
                    progressDialog.dismiss();
                    Toast.makeText(SignInActivity.this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                String nameUser = etNameUser.getText().toString().trim().toUpperCase();
                String pass = etPassUser.getText().toString().trim();

                ServiceClient serviceClient = ServiceGenerator.createService(ServiceClient.class);

                Call<SignInResponse> requestSignin = serviceClient.signinUser("loginUser", "login", nameUser, pass);
                requestSignin.enqueue(new Callback<SignInResponse>() {
                    @Override
                    public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                        progressDialog.dismiss();
                        if (response.body().getHasil().equals("success")){
                            sharedPreferencesManager.saveSpBoolean(SharedPreferencesManager.SP_SIGNED, true);
                            startActivity(new Intent(SignInActivity.this, GenreActivity.class));
                            finish();

                        } else {
                            Toast.makeText(SignInActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignInResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(SignInActivity.this, "Koneksi Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        fAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        findViewById(R.id.signin_with_google).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (fAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //out
                firebaseAuthWithGoogle(account);
            } catch (ApiException e){
                Toast.makeText(SignInActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        if (task.isSuccessful()){
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(SignInActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn() {
        Intent signIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signIntent, RC_SIGN_IN);
    }
}
