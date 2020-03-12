package com.example.einzelabgabe_auer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.einzelabgabe_auer.network.NetworkClient;

public class MainActivity extends AppCompatActivity {
    private TextView textView_digitSum;
    private TextView textView_serverAnswer;
    private EditText editText_matInput;
    private  NetworkClient nc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create network client Instance
        nc = new NetworkClient("se2-isys.aau.at", 53212);
        // get GUI elements.
        textView_digitSum = findViewById(R.id.textView_digitSum);
        textView_serverAnswer = findViewById(R.id.textView_serverAnswer);
        editText_matInput = findViewById(R.id.editText_matInput);
    }

    public void clickSendButton(View view) {
        String matNr = editText_matInput.getText().toString();
        editText_matInput.onEditorAction(EditorInfo.IME_ACTION_DONE);
        if (!matNr.equals("")) {
            textView_serverAnswer.setText(getServerAnswer(matNr));
            int digitSum = digitSum(Integer.parseInt(matNr));
            textView_digitSum.setText("Die Quersumme der Matrikelnummer = " + digitSum + "\nBinärdarstellung: " + Integer.toBinaryString(digitSum));
        }
    }

    private String getServerAnswer(String payload) {
        nc.setPayload(payload);
        nc.start();
        try {
            nc.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nc.getServerAnswer();
    }

    private int digitSum(int n) {
        if (n < 10) {
            return n;
        }
        return n % 10 + digitSum(n / 10);
    }
}
