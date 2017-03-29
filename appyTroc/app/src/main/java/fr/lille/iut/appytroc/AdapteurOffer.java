package fr.lille.iut.appytroc;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by thibault on 29/03/2017.
 */

public class AdapterOffer extends ArrayAdapter<Offer> {

    //tweets est la liste des models à afficher
    public AdapterOffer(Context context, List<Offer> Offers) {
        super(context, 0, Offers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cellule_offer,parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TweetViewHolder();
            viewHolder.titre = (TextView) convertView.findViewById(R.id.Titre);
            viewHolder.detail = (TextView) convertView.findViewById(R.id.text);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Offer offer = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder. titre.setText(offer.getTitre());
        viewHolder.detail.setText(offer.getDetail());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(Color.BLUE));

        return convertView;
    }

    private class TweetViewHolder{
        public TextView titre;
        public TextView detail;
        public ImageView avatar;
    }
}
