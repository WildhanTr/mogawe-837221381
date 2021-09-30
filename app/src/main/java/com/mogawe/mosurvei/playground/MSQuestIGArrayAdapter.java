package com.mogawe.mosurvei.playground;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mogawe.mosurvei.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobiletech on 5/21/17.
 */

public class MSQuestIGArrayAdapter extends ArrayAdapter<String>{

    private Context mContext;
    private List<String> list = new ArrayList<>();

    public MSQuestIGArrayAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
        mContext = context;
        list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.l_ig_swipe_items,parent,false);

        String currentMovie = list.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.txvIG);
        name.setText(currentMovie);

        ImageView img = (ImageView) listItem.findViewById(R.id.imvIG);
        Picasso.get().load("https://launchbox365.com/wp-content/uploads/2014/11/Happy-Staff-small.jpg")
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .error(R.color.grey_200)
                .into(img);

        return listItem;
    }
}