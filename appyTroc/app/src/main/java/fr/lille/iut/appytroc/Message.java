package fr.lille.iut.appytroc;

import android.content.Context;
import android.content.Intent;

/**
 * Created by thibault on 30/03/2017.
 */

public class Message {

    private Context context;



    public Message(Context message){
        this.context= message;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void exec() {
        Intent intent = new Intent(this.context, OfferAllActivity.class);
        this.context.startActivity(intent);

            }
}
