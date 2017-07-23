package com.sdacademy.zientara.rafal.awesomeapp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdacademy.zientara.rafal.fileexplorer.Item;


public class FileArrayAdapter extends ArrayAdapter<Item> {
    private Context context;
    private int id;
    private List<Item> items;

    public FileArrayAdapter(Context context, int textViewResourceId,
                            List<Item> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        id = textViewResourceId;
        items = objects;
    }

    public Item getItem(int i) {
        return items.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(id, null);
        }
               
               /* create a new view of my layout and inflate it in the row */
        //convertView = ( RelativeLayout ) inflater.inflate( resource, null );

        final Item o = items.get(position);
        if (o != null) {
            TextView t1 = (TextView) v.findViewById(R.id.TextView01);
            TextView t2 = (TextView) v.findViewById(R.id.TextView02);
            TextView t3 = (TextView) v.findViewById(R.id.TextViewDate);
                       /* Take the ImageView from layout and set the city's image */
            ImageView imageCity = (ImageView) v.findViewById(R.id.fd_Icon1);
            //int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            //Drawable image = ContextCompat.getDrawable(context, imageResource);//context.getResources().getDrawable(imageResource);
            //imageCity.setImageDrawable(image);

            if (t1 != null)
                t1.setText(o.getName());
            if (t2 != null)
                t2.setText(o.getDescription());
            if (t3 != null)
                t3.setText(o.getDate());

        }
        return v;
    }

}
