package com.example.attendancetrackingsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{
    //public final static String EXTRA_MESSAGE_USERNAME = "com.example.myfirstapp.USERNAME";
   // public final static String EXTRA_MESSAGE_PASSWORT = "com.example.myfirstapp.PASSWORT";
    public final static String GOOGLE_ID = "com.example.myfirstapp.ID";
    public final static String GOOGLE_USERNAME = "com.example.myfirstapp.USERNAME";
    public final static String GOOGLE_EMAIL = "com.example.myfirstapp.EMAIL";
    ImageView imageView;

    private LinearLayout Prof_Section;
    private Button SignOut;
    private SignInButton SignIn;
    private TextView Name,Email;
    private ImageView Prof_Pic;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private String code_id;
    private String username;
    private String email;
    private EditText Passwort;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        Prof_Section = (LinearLayout) findViewById(R.id.prof_section);
        SignOut = (Button) findViewById(R.id.bn_logout);
        SignIn = (SignInButton) findViewById(R.id.bn_login);
        Name = (TextView) findViewById(R.id.name);
        Email = (TextView) findViewById(R.id.email);
        Prof_Pic = (ImageView) findViewById(R.id.prof_pic);
        Passwort = (EditText) findViewById(R.id.GoogleID);
        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        Prof_Section.setVisibility(View.GONE);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestId().build();
        //googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, signInOptions);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        //EditText editText = (EditText) findViewById(R.id.in_username);
        //String username = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE_USERNAME,username);

        //editText = (EditText) findViewById(R.id.in_passwort) ;
        //String passwort = editText.getText().toString();
        intent.putExtra(GOOGLE_ID,code_id);
        intent.putExtra(GOOGLE_USERNAME,username);
        intent.putExtra(GOOGLE_EMAIL,email);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bn_login:
                signIn();
                break;

            case R.id.bn_logout:
                signOut();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void signIn(){
        //Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        //startActivityForResult(intent,REQ_CODE);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, REQ_CODE);
    }


    private void signOut(){
        mGoogleSignInClient.signOut();
        updateUI(false);
        /*Auth.GoogleSignInApi.signOut(mGoogleSignInClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });*/

    }
    private void handleResult(Task<GoogleSignInAccount> completedTask){ //GoogleSignInResult result
        /*if(result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            String img_url = account.getPhotoUrl().toString();
            Name.setText(name);
            Email.setText(email);
            Glide.with(this).load(img_url).into(Prof_Pic);
            updateUI(true);
        }
        else
        {
            updateUI(false);
        }*/
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String name = account.getDisplayName();
            String e_mail = account.getEmail();

           // GoogleSignInClient Client = completedTask.getResult()
           code_id = account.getId().toString();
           username=name;
           email = e_mail;
            //code_id = account.getServerAuthCode().toString();
            String img_url = account.getPhotoUrl().toString();
            Name.setText(name);
            Email.setText(e_mail);
            Glide.with(this).load(img_url).into(Prof_Pic);
            updateUI(true);
        } catch (Exception e)
        {
            updateUI(false);
        }
    }
    private void updateUI(boolean isLogin){

        if(isLogin){
            Prof_Section.setVisibility(View.VISIBLE);
            SignIn.setVisibility(View.GONE);
            Passwort.setVisibility(View.GONE);

        }
        else{
            Prof_Section.setVisibility(View.GONE);
            SignIn.setVisibility(View.VISIBLE);
            Passwort.setVisibility(View.VISIBLE);


        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
       /* super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);

            }*/
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == REQ_CODE) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleResult(task);
        }
    }
}
