package fr.lille.iut.appytroc;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Button createUser;
    Button longin;
    EditText enterUserID;
    EditText enterPasswd;
    static String serverReturnCode = "";


    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createUser = (Button) findViewById(R.id.createAccount);
        enterPasswd = (EditText) findViewById(R.id.enterPasswd);
        enterUserID = (EditText) findViewById(R.id.enterID);
        longin = (Button) findViewById(R.id.login);
        show = (Button) findViewById(R.id.show);


        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserRegister.class);
                MainActivity.this.startActivityForResult(intent, 1);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OfferAllActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        longin.setOnClickListener(new monListener());
        enterUserID.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
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

                    MainAsynct async = new MainAsynct(new User(enterUserID.getText().toString(), enterPasswd.getText().toString()));
                    async.setUrl("http://osmar.io:8080/v1/login");
                    async.setMethode("GET");
                    async.execute();
                    Integer serverCodeReponse= async.get();

                    if(serverCodeReponse==200){
                        Intent intent = new Intent(MainActivity.this, OfferAllActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,""+serverCodeReponse,Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
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

     private class MainAsynct extends AsyncTask<Void, Void, Integer> {

        private String url = "http://";
        private String methode = "UNKNOW";
        private String baseAuthStr;
        private String encoded;
        private JSONObject jsonObject = new JSONObject();

         public String codereponse="";

        public String getUrl() {
            return url;
        }

        public String getMethode() {
            return methode;
        }

        public void setMethode(String methode) {
            this.methode = methode;
        }

        public void setUrl(String url) {
            this.url = url;
        }



        public MainAsynct(User user) throws JSONException {

            jsonObject.put("name", user.getName());
            jsonObject.put("password", user.getPwd());
            baseAuthStr = user.getName() + ":" + user.getPwd();

            encoded = Base64.encodeToString(baseAuthStr.getBytes(), Base64.NO_WRAP);
        }

        public MainAsynct() {
            baseAuthStr = "android:android";
            encoded = Base64.encodeToString(baseAuthStr.getBytes(), Base64.NO_WRAP);

        }

        @Override
        protected void onPostExecute(Integer result){

        }

        @Override
        protected Integer doInBackground(Void... params) {
            Integer result;
            try {
                URL url = new URL(this.getUrl());

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setDoOutput(true);

                httpURLConnection.setRequestMethod(this.getMethode());

                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.addRequestProperty("Authorization", encoded);


                httpURLConnection.connect();


                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());

                wr.writeBytes(jsonObject.toString());
                wr.flush();

                wr.close();

                result = httpURLConnection.getResponseCode();


            } catch (MalformedURLException e) {
                e.printStackTrace();
                result = 0;

            } catch (IOException e) {
                e.printStackTrace();
                result = 0;
            }
            return result;
        }


    }
    }

