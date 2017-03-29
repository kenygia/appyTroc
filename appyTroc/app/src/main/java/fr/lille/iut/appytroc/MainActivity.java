package fr.lille.iut.appytroc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button createUser;
    Button longin;
    EditText enterUserID;
    EditText enterPasswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createUser = (Button) findViewById(R.id.createAccount);

        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InscriptionActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        longin = (Button) findViewById(R.id.login);
        enterPasswd = (EditText) findViewById(R.id.enterPasswd);
        enterUserID = (EditText) findViewById(R.id.enterID);


    }

}
