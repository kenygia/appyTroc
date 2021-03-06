package fr.lille.iut.appytroc;


import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by thibault on 30/03/2017.
 */

public class RequestServeur {


    /**
     * Created by tibo on 29/03/17.
     */



        private String url ="http://";
        private String methode="UNKNOW";
        private String baseAuthStr;
        private String encoded ;

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

        public JSONObject jsonObject = new JSONObject();


        public RequestServeur(User user) throws JSONException {

            jsonObject.put("name", user.getName());
            jsonObject.put("password", user.getPwd());
            baseAuthStr =  user.getName() + ":" + user.getPwd();
            encoded = Base64.encodeToString(baseAuthStr.getBytes(), Base64.NO_WRAP);
        }

        public RequestServeur(Offer offer) throws JSONException {

            jsonObject.put("id", offer.getId());
            jsonObject.put("id_user", offer.getId_user());
            jsonObject.put("titre", offer.getTitre());
            jsonObject.put("detail", offer.getDetail());
            jsonObject.put("active", offer.isActive());

        }

       public String execute(){

           String codereponse="";
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


               codereponse =""+ httpURLConnection.getResponseCode();

           } catch (MalformedURLException e) {
               e.printStackTrace();
               codereponse= e.toString();

           } catch (IOException e) {
               e.printStackTrace();
               codereponse= e.toString();
           }
           return codereponse;
       }


}
