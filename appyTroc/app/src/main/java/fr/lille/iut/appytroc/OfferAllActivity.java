package fr.lille.iut.appytroc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OfferAllActivity extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_all);
        mListView = (ListView) findViewById(R.id.mListView);
        List<Offer> offers = generateOffers(getAllOffer());
        AdapterOffer adapter = new AdapterOffer(OfferAllActivity.this, offers);
        mListView.setAdapter(adapter);

    }

    private List<Offer> generateOffers(JSONObject jso) {
        List<Offer> list_offer = new ArrayList<>();
        try {
            JSONArray array = jso.getJSONArray("offer");

            for( int i = 0 ; i < array.length() ; i++ ) {
                JSONObject c = array.getJSONObject(i);
                int id = c.getInt("id");
                int id_user = c.getInt("id_user");
                String titre = c.getString("titre");
                String detail = c.getString("detail");
                boolean active = c.getBoolean("active");

                list_offer.add(new Offer(id,id_user,titre,detail));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list_offer;
    }

    private JSONObject getAllOffer() {

        JSONObject jso = new JSONObject();

        try {
            Asynct async = new Asynct();
            async.setUrl("http://172.19.162.94:8080/v1/login");
            async.setMethode("GET");
            async.execute();

            String reponse = async.codereponse;


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jso;
    }
}
