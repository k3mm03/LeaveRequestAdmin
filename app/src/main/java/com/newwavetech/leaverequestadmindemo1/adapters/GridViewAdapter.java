package com.newwavetech.leaverequestadmindemo1.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.newwavetech.leaverequestadmindemo1.R;

public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    private String[] cardList;
    private int[] cardImageList;

    public GridViewAdapter(Context mContext, String[] cardList, int[] cardImageList) {
        this.mContext = mContext;
        this.cardList = cardList;
        this.cardImageList = cardImageList;
    }

    @Override
    public int getCount() {
        return cardList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.gridview_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.materialcardview_imageview);
        TextView textView = convertView.findViewById(R.id.materialcardview_textview);

        imageView.setImageResource(cardImageList[position]);
        textView.setText(cardList[position]);

        return convertView;
    }
}
