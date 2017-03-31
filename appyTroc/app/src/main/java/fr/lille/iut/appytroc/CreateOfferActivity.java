package fr.lille.iut.appytroc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CreateOfferActivity extends AppCompatActivity {
    EditText description;
    TextView infoDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offer);
        description = (EditText) findViewById(R.id.EditTextDescription);
        infoDescription = (TextView) findViewById(R.id.TextViewDescription);
    }
}
