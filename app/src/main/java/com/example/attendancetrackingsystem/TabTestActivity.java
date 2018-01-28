package com.example.attendancetrackingsystem;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.restlet.resource.ClientResource;

import java.util.StringTokenizer;

public class TabTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_test);
        LstFragment lstFragment = (LstFragment) getSupportFragmentManager().findFragmentByTag("lstfragment");
        if (lstFragment == null) {
            lstFragment = new LstFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(android.R.id.content, lstFragment, "lstfragment");
            transaction.commit();
        }
        //Intent intent = getIntent();
    }
}
