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
 * Created by caroenk on 23/03/17.
 */

public class AdapterOffer extends ArrayAdapter<Offer> {

    private OfferViewHolder viewHolder;

    public AdapterOffer(Context context, List<Offer> offers) {
        super(context, 0, offers);
    }

    public View getView(int position, View convertView, final ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cellule_offer, parent, false);
        }

        OfferViewHolder viewHolder = (OfferViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new OfferViewHolder();
            viewHolder.titre = (TextView) convertView.findViewById(R.id.Titre);
            viewHolder.details = (TextView) convertView.findViewById(R.id.detail);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        Offer offer = getItem(position);
        viewHolder.titre.setText(offer.getTitre().toString());
        viewHolder.details.setText(offer.getDetail().toString());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(Color.BLUE));

        return convertView;
    }

    public class OfferViewHolder {

        public TextView titre;
        public TextView details;
        public ImageView avatar;
    }
}
