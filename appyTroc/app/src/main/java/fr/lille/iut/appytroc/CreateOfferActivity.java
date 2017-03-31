package fr.lille.iut.appytroc;

import android.content.Intent;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import static fr.lille.iut.appytroc.OfferAllActivity.offers;

public class CreateOfferActivity extends AppCompatActivity {
    EditText description;
    TextView infoDescription;
    EditText EditTextTitre;
    TextView TextViewTitre;
    ImageButton imageButton;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offer);
        description = (EditText) findViewById(R.id.EditTextDescription);
        infoDescription = (TextView) findViewById(R.id.TextViewDescription);
        EditTextTitre = (EditText) findViewById(R.id.EditTextTitre);
        TextViewTitre = (TextView) findViewById(R.id.TextVIewTitre);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        submit = (Button) findViewById(R.id.submit);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateOfferActivity.this, cameraActivity.class);
                CreateOfferActivity.this.startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offers.add(new Offer( 3,3,EditTextTitre.getText().toString(), description.getText().toString()));
            }
        });

    }
}
