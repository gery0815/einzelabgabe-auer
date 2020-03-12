package com.example.einzelabgabe_auer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView_digitSum;
    private EditText editText_matInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get GUI elements.
        textView_digitSum = findViewById(R.id.textView_digitSum);
        editText_matInput = findViewById(R.id.editText_matInput);
    }

    public void clickSendButton(View view) {
        String matNr = editText_matInput.getText().toString();
        editText_matInput.onEditorAction(EditorInfo.IME_ACTION_DONE);
        if (!matNr.equals("")) {
            int digitSum = digitSum(Integer.parseInt(matNr));
            textView_digitSum.setText("Die Quersumme der Matrikelnummer = " + digitSum + "\nBinärdarstellung: " + Integer.toBinaryString(digitSum));
        }


    }

    private int digitSum(int n) {
        if (n < 10) {
            return n;
        }
        return n % 10 + digitSum(n / 10);
    }
}
