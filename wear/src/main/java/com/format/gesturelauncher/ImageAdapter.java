package com.format.gesturelauncher;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 子恒 on 2017/9/23.
 * Copied from mobile
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<String> title;
    ArrayList<Bitmap> imageID;

    public ImageAdapter(Context c, ArrayList<String> title, ArrayList<Bitmap> imageID ) {
        mContext = c;
        this.imageID = imageID;
        this.title = title;
    }

    @Override
    public int getCount() {

        return title.size();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        View grid;
//        LayoutInflater inflater = (LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        if (convertView == null) {
//
//            grid = new View(mContext);
//            grid = inflater.inflate(R.layout.list_single, null);
//            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
//            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
//            textView.setText(title.get(position));
////            imageView.setImageResource(imageID[position]);
//            imageView.setImageBitmap(imageID.get(position));
//        } else {
//            grid = (View) convertView;
//        }


        //Deleted original (Above), Based on https://stackoverflow.com/questions/11245620/items-inside-gridview-getting-repeated-when-screen-scrolls

        View grid;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(     Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.list_single, parent, false);
        } else {
            grid = (View) convertView;
        }

        TextView textView = (TextView) grid.findViewById(R.id.grid_text);
        ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
        textView.setText(title.get(position));
        imageView.setImageBitmap(imageID.get(position));

        return grid;
    }
}
