package com.example.hayleyprior.androidslotmachine;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClickNewGameButton(View button){
        Intent i = new Intent(this, MoneyActivity.class);
        startActivity(i);
    }

}
