package com.ccsu.designpatterns.fall23.alieninvasionsim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.GridAdapter;

public class MainActivity extends AppCompatActivity {
    private Grid mSimulationGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView background = findViewById(R.id.ivBackground);
        Glide.with(this).load(R.drawable.earth_rotation).into(background);

        // Build the simulation grid
        mSimulationGrid = Grid.getInstance(10);
        GridView gridView = findViewById(R.id.gvGrid);
        gridView.setNumColumns((int) Math.sqrt(mSimulationGrid.getGridSize()));
        GridAdapter adapter = new GridAdapter(this, mSimulationGrid.getTiles());
        gridView.setAdapter(adapter);

        // Begin observing changes to the simulation year to refresh the UI
        Observer<Integer> yearObserver = this::updateDisplay;
        mSimulationGrid.getYear().observeForever(yearObserver);

        // Setup the Reverse Button
        findViewById(R.id.ibRvs).setOnClickListener(view -> {
            //TODO Implement reverse functionality here
        });
        // Setup the Forward Button
        findViewById(R.id.ibFwd).setOnClickListener(view -> {
            mSimulationGrid.progressSimulation();
        });
    }

    /**
     * Update what is displayed to the user on screen.
     *
     * @param year  - Current year in the simulation.
     *
     * @author
     * @version
     * @since
     */
    private void updateDisplay(int year) {
        //TODO add code to iterate and print logs to the console here
        //findViewById(R.id.tvConsole);
    }
}