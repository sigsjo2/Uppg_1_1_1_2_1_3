package com.example.s.uppg_1_1_1_2_1_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    public static final String REPLY = "REPLY";
    private EditText editText;

    /** activated when a new movie should be added */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = (EditText) findViewById(R.id.editText);

        Intent intent = getIntent();
    }

    /** send movie name to main activity */
    public void addMovie(View view) {

        String reply = editText.getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(REPLY, reply);
        setResult(RESULT_OK, replyIntent);
        finish();

    }
}
