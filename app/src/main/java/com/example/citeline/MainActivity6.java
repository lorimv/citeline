package com.example.citeline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void login(View view) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.pw1);
        ll.setVisibility(View.GONE);

        LinearLayout ll2 = (LinearLayout) findViewById(R.id.pw2);
        ll2.setVisibility(View.GONE);

        LinearLayout ll3 = (LinearLayout) findViewById(R.id.loggedIn);
        ll3.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}