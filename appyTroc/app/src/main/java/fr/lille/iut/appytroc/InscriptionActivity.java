package fr.lille.iut.appytroc;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;;import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class InscriptionActivity extends AppCompatActivity {

    private EditText login_text, pwd_text;
    private Button valid_inscr;
    String login, pwd;

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

                login = login_text.getText().toString();
                pwd = pwd_text.getText().toString();

                if (login.isEmpty() || login.length() < 4 || login.length() > 100 ) {
                    login_text.setError("A Login is required!");
                } else if (pwd.isEmpty() || pwd.length() < 4 || pwd.length() > 64 ) {
                    login_text.setError("A Password is required!");
                } else {
                    try {
                        AsyncT async = new AsyncT(new User(login, pwd));
                        async.execute();
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }
        });
    }
}
