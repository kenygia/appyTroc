package fr.lille.iut.appytroc;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    Button createUser;
    Button longin;
    EditText enterUserID;
    EditText enterPasswd;
    static String serverReturnCode="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createUser = (Button) findViewById(R.id.createAccount);

        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserRegister.class);
                MainActivity.this.startActivityForResult(intent, 1);
            }
        });
        longin = (Button) findViewById(R.id.login);
        enterPasswd = (EditText) findViewById(R.id.enterPasswd);
        enterUserID = (EditText) findViewById(R.id.enterID);

        longin.setOnClickListener(new monListener());


        enterUserID.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    enterUserID.clearFocus();
                    enterPasswd.requestFocus();
                    enterPasswd.setCursorVisible(true);
                    return true;
                }
                return false;
            }
        });


        enterPasswd.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    longin.performClick();
                    return true;
                }
                return false;
            }
        });


    }

    class monListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (enterUserID.getText().toString().isEmpty() || (enterUserID.getText().length() < 4 && enterUserID.getText().length() < 100)) {
                enterUserID.setError("A Login is required! min 5 char and max 99");
            } else if (enterPasswd.getText().toString().isEmpty() || (enterPasswd.getText().length() < 4 && enterPasswd.getText().length() < 64)) {
                enterPasswd.setError("A Password is required! min 5 char");
            } else {
                try {
                    Asynct async = new Asynct(new User( enterUserID.getText().toString(),enterPasswd.getText().toString() ));
                    async.setUrl("http://172.19.162.94:8080/v1/login");
                    async.setMethode("GET");
                    async.execute();

                    //serverCodeReponse= async.codereponse;


                    Toast.makeText(getApplicationContext(), "code = " + serverReturnCode, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
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
    }
}