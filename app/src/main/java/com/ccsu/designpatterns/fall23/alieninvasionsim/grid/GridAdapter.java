package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.IRON;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.OIL;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.ONEUP;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.URANIUM;
import static com.ccsu.designpatterns.fall23.alieninvasionsim.grid.ResourceTile.resourceType.WATER;
import androidx.cardview.widget.CardView;
import java.util.List;
import com.ccsu.designpatterns.fall23.alieninvasionsim.R;
import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.LifeForm;
import com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms.Martian;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<Tile> mTiles;

    public GridAdapter(Context context, List<Tile> tiles) {
        mContext = context;
        mTiles = tiles;
    }

    @Override
    public int getCount() {
        return mTiles.size();
    }

    @Override
    public Object getItem(int i) {
        return mTiles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Sets tiles to appropriate display and puts proper sprites on the tiles that they occupy.
     *
     * @author Joseph Lumpkin
     * @param i tile ref for grid tile
     * @param view the application view refernece
     * @param viewGroup
     * @return View what the application will render
     * @since 2023-12-3
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.grid_item, null);
        ImageView iv = view.findViewById(R.id.ivGridItemImage);

        CardView cardView = view.findViewById(R.id.cvTile);
        if (mTiles.get(i) instanceof ResourceTile && ((ResourceTile) mTiles.get(i)).getResourceType() == WATER) {
            cardView.setBackgroundColor(Color.rgb(0, 161, 255));
        }
        else if (mTiles.get(i) instanceof ResourceTile && ((ResourceTile) mTiles.get(i)).getResourceType() == IRON) {
            cardView.setBackgroundColor(Color.GRAY);
            iv.setImageResource(R.drawable.iron);
        }
        else if (mTiles.get(i) instanceof ResourceTile && ((ResourceTile) mTiles.get(i)).getResourceType() == URANIUM) {
            cardView.setBackgroundColor(Color.GRAY);
            iv.setImageResource(R.drawable.uranium);
        }
        else if (mTiles.get(i) instanceof ResourceTile && ((ResourceTile) mTiles.get(i)).getResourceType() == OIL) {
            cardView.setBackgroundColor(Color.GRAY);
            iv.setImageResource(R.drawable.oil_barrel);
        }
        else if (mTiles.get(i) instanceof ResourceTile && ((ResourceTile) mTiles.get(i)).getResourceType() == ONEUP) {
            cardView.setBackgroundColor(Color.GRAY);
            iv.setImageResource(R.drawable.one_up);
        }
        else {
            cardView.setBackgroundColor(Color.rgb(149, 246, 121));
        }

        LifeForm occupant = mTiles.get(i).getOccupant();
        if (occupant != null) {
            iv.setImageResource((occupant instanceof Martian) ?
                    R.drawable.alien : R.drawable.human_1);

            //shows the population on the tile
            TextView tv = view.findViewById(R.id.tilePopulation);
            tv.setText(Integer.toString(occupant.getPopulationCount()));
        }

        //TODO add code to resume animations here?
        return view;
    }
}
