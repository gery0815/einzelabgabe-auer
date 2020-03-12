package com.example.einzelabgabe_auer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int digitSum(int n) {
        if (n < 10) {
            return n;
        }
        return n%10 + digitSum(n/10);
    }
}
