package fr.lille.iut.appytroc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

public class UserRegister extends AppCompatActivity {

    String login, pwd;
    private EditText login_text, pwd_text, retypePasswd;
    private Button valid_inscr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        login_text = (EditText) findViewById(R.id.login_text);
        pwd_text = (EditText) findViewById(R.id.pwd_text);
        valid_inscr = (Button) findViewById(R.id.valid_inscr);
        retypePasswd = (EditText) findViewById(R.id.pwdVerif_text);
        valid_inscr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login = login_text.getText().toString();
                pwd = pwd_text.getText().toString();

                if (login.isEmpty() || login.length() < 4 || login.length() > 100) {
                    login_text.setError("A Login is required!");
                } else if (pwd.isEmpty() || pwd.length() < 4 || pwd.length() > 64) {
                    pwd_text.setError("A Password is required!"); //

                }else if(retypePasswd.getText().toString().isEmpty() || (!retypePasswd.getText().toString().equals(pwd))){
                    retypePasswd.setError("passwd is different !");
                }

                else {
                    try {
                        Asynct async = new Asynct(new User(login, pwd));
                        async.setMethode("POST");
                        async.setUrl("http://osmar.io:8080/v1/user");
                        async.execute();
                        Toast.makeText(getApplicationContext(), "" + async.codereponse + "", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
//                        AsyncTGet.codereponse = e.toString();
                    }
                    finish();
                }
            }
        });
    }
}

