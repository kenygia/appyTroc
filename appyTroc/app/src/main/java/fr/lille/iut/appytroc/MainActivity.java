package fr.lille.iut.appytroc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

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
                Intent intent = new Intent(MainActivity.this,test.class);
                MainActivity.this.startActivityForResult(intent,1);
            }
        });
        longin = (Button) findViewById(R.id.login);
        enterPasswd = (EditText) findViewById(R.id.enterPasswd);
        enterUserID = (EditText) findViewById(R.id.enterID);

        longin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enterUserID.getText().toString().isEmpty() || (enterUserID.getText().length()<4 && enterUserID.getText().length()<100)) {
                    enterUserID.setError( "A Login is required! min 5 char and max 99" );
                } else if (enterPasswd.getText().toString().isEmpty() || (enterPasswd.getText().length()<4 && enterPasswd.getText().length()<64)) {
                    enterPasswd.setError("A Password is required! min 5 char");
                } else {
                    try {
                        AsyncTGet async = new AsyncTGet(new User( enterUserID.getText().toString(),enterPasswd.getText().toString() ));
                        async.execute("http://172.19.162.94/v1/login");
                        //String reponse = async.get().toString();


                        Toast.makeText(getApplicationContext(), "je suis la", Toast.LENGTH_LONG).show();
                    }catch (JSONException e) {
                        e.printStackTrace();

                    }
                    /*catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e) {
                        e.printStackTrace();
                    }*/
                }

            }
        });


    }

}
