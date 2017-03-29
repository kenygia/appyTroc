package com.example.caroenk.troc;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {

    private EditText login_text, pwd_text;
    private Button valid_inscr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        login_text = (EditText) findViewById(R.id.login_text);
        pwd_text = (EditText) findViewById(R.id.pwd_text);
        valid_inscr = (Button) findViewById(R.id.valid_inscr);

        valid_inscr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(login_text.getText().toString().equals("") ) {
                    login_text.setError( "A Login is required!" );
                } else if (pwd_text.getText().toString().equals("")) {
                    login_text.setError("A Password is required!");
                } else {

                    Intent intent = new Intent(InscriptionActivity.this, InscriptionActivity.class);
                    InscriptionActivity.this.startActivity(intent);
                    finish();
                }
            }
        });
    }
}
