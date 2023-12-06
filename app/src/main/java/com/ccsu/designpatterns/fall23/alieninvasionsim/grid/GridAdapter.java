package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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

/**
 * Adapter for drawing the tiles and lifeforms on the grid of the simulation.
 *
 * @author Joseph Lumpkin
 * @version 1.0
 */
public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<Tile> mTiles;

    /**
     * Constructor.
     * @param context   - Android activity context these views are bound to
     * @param tiles - List of tiles to iterate over and draw to the grid
     *
     * @author Joseph Lumpkin
     * @version 1.0
     */
    public GridAdapter(Context context, List<Tile> tiles) {
        mContext = context;
        mTiles = tiles;
    }

    /**
     * Get the number of tiles being drawn by this adapter interface.
     *
     * @return  - Number of tiles being drawn.
     *
     * @author Joseph Lumpkin
     * @version 1.0
     */
    @Override
    public int getCount() {
        return mTiles.size();
    }

    /**
     * Get a tile from this adapter's list of tiles.
     *
     * @param i - Index of the item to retrieve
     *          from the this adapter's list of tiles.
     *
     * @return Retrieved tile from the list that is
     *          associated with the supplied index.
     *
     * @author Joseph Lumpkin
     * @version 1.0
     */
    @Override
    public Object getItem(int i) {
        return mTiles.get(i);
    }

    /**
     * Get a tile's ID when supplied it's
     * index in this adapter's list of tiles.<br>
     * (NOT IMPLEMENTED! NOT NECESSARY FOR OUR USE CASE)
     *
     * @param i - Index of the item containing the ID to retrieve
     *              from the this adapter's list of tiles.
     * @return The item's ID (At this point it's just the index)
     *
     * @author Joseph Lumpkin
     * @version 1.0
     */
    @Override
    public long getItemId(int i) {
        Log.d("GridAdapter",
                "getItemId failed. Method not implemented " +
                        "as it was not necessary for our use case."
        );
        return i;
    }

    /**
     * Sets tiles to appropriate display and puts proper sprites on the tiles that they occupy.
     *
     * @param i tile ref for grid tile
     * @param view the application view refernece
     * @param viewGroup
     * @return View what the application will render
     *
     * @author Joseph Lumpkin, Vince Capra
     * @since 2023-12-3
     * @version 1.0
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Get the layout of the tile to setup
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.grid_item, null);
        ImageView iv = view.findViewById(R.id.ivGridItemImage);
        CardView cardView = view.findViewById(R.id.cvTile);

        // Color the background of the tile to match the terrain or resource
        if (mTiles.get(i) instanceof ResourceTile &&
                ((ResourceTile) mTiles.get(i)).getResourceType() == WATER) { // If water tile
            cardView.setBackgroundColor(Color.rgb(0, 161, 255));
        } else if (mTiles.get(i) instanceof ResourceTile &&
                ((ResourceTile) mTiles.get(i)).getResourceType() == IRON) { // If iron tile
            cardView.setBackgroundColor(Color.GRAY);
            iv.setImageResource(R.drawable.iron);
        } else if (mTiles.get(i) instanceof ResourceTile &&
                ((ResourceTile) mTiles.get(i)).getResourceType() == URANIUM) { // If uranium tile
            cardView.setBackgroundColor(Color.GRAY);
            iv.setImageResource(R.drawable.uranium);
        } else if (mTiles.get(i) instanceof ResourceTile &&
                ((ResourceTile) mTiles.get(i)).getResourceType() == OIL) { // If oil tile
            cardView.setBackgroundColor(Color.GRAY);
            iv.setImageResource(R.drawable.oil_barrel);
        } else if (mTiles.get(i) instanceof ResourceTile &&
                ((ResourceTile) mTiles.get(i)).getResourceType() == ONEUP) { // If one-up tile
            cardView.setBackgroundColor(Color.GRAY);
            iv.setImageResource(R.drawable.one_up);
        } else { // If this is the standard terrain tile
            cardView.setBackgroundColor(Color.rgb(149, 246, 121));
        }

        // Check if there is a LifeForm on this tile
        LifeForm occupant = mTiles.get(i).getOccupant();
        if (occupant != null) { // A LifeForm occupies this tile
            // Draw the LifeForm onto the tile
            iv.setImageResource((occupant instanceof Martian) ?
                    R.drawable.alien : R.drawable.human_1);
            // Show the population on the tile
            TextView tv = view.findViewById(R.id.tilePopulation);
            tv.setText(Integer.toString(occupant.getPopulationCount()));
        }

        // Return the compiled view to the grid for drawing to the screen
        return view;
    }
}
