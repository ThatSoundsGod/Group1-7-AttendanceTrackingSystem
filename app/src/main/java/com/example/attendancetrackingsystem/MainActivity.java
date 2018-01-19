package com.example.attendancetrackingsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE_USERNAME = "com.example.myfirstapp.USERNAME";
    public final static String EXTRA_MESSAGE_PASSWORT = "com.example.myfirstapp.PASSWORT";
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestId().build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        //imageView = (ImageView) findViewById(R.id.main_image);
        //image = findViewById(R.drawable.background);
        //imageView.setImageDrawable(R.drawable.background);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.in_username);
        String username = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE_USERNAME,username);

        editText = (EditText) findViewById(R.id.in_passwort) ;
        String passwort = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE_PASSWORT,passwort);

        startActivity(intent);
    }
}
