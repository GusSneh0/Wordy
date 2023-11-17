package com.example.wordy;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wordy.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[] words = {"happy", "jazzy", "drama", "dozen", "draft", "every"};
    private String answer = words[new Random().nextInt(words.length)].toUpperCase();
    private EditText userInputEditText;
    private TextView resultTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInputEditText = findViewById(R.id.userInputEditText);
        resultTextView = findViewById(R.id.resultTextView);

        userInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                processGuess(editable.toString().toUpperCase());
            }
        });
    }

    private void processGuess(String guess) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < guess.length() && i < answer.length(); i++) {
            char c = guess.charAt(i);
            if (answer.charAt(i) == c) {
                b.append(Character.toString(c));
                // You can also set color to the entered text in EditText here
                userInputEditText.setTextColor(Color.GREEN);
            } else if (answer.contains(Character.toString(c))) {
                b.append(Character.toString(c));
                // You can set color to the entered text in EditText here for partial match
                userInputEditText.setTextColor(Color.YELLOW);
            } else {
                b.append(Character.toString(c));
                // For incorrect characters, keep the default color of EditText
                userInputEditText.setTextColor(Color.BLACK);
            }
        }
        resultTextView.setText(b.toString());
    }
}



