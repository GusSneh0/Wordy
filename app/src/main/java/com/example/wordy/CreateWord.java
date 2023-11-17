package com.example.wordy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class CreateWord extends AppCompatActivity {

    private EditText wordET;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_word);
        wordET = findViewById(R.id.editTextText31);

    }
    public void add(View view) {
        String word = wordET.getText().toString();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Words");

        reference.push().setValue(word).addOnCompleteListener(new OnCompleteListener<Void>() {
           // @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    wordET.setText("");

                    Toast.makeText(CreateWord.this, "Word added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateWord.this, "Word could not be added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void cancel(View view) {
        Intent switchToMainActivity = new Intent(this, MainActivity.class);
        startActivity(switchToMainActivity);
    }
}