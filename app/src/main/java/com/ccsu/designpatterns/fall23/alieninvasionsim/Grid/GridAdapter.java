package com.ccsu.designpatterns.fall23.alieninvasionsim.Grid;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.ccsu.designpatterns.fall23.alieninvasionsim.R;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Tile> mTiles;

    public GridAdapter(Context context, ArrayList<Tile> tiles) {
        mContext = context;
        mTiles = tiles;
    }

    @Override public int getCount() {
        return mTiles.size();
    }

    @Override public Object getItem(int i) {
        return mTiles.get(i);
    }

    @Override public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.grid_item, null);

        CardView cardView = view.findViewById(R.id.cvTile);
        if (mTiles.get(i) instanceof ResourceTile) {
            cardView.setBackgroundColor(Color.BLUE);
        } else {
            cardView.setBackgroundColor(Color.GREEN);
        }
        return view;
    }
}
