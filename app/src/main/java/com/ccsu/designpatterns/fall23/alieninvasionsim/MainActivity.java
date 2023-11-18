package com.ccsu.designpatterns.fall23.alieninvasionsim;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        //mSimulationGrid = new Grid(10);
        mSimulationGrid = Grid.getInstance(10);
        GridView gridView = findViewById(R.id.gvGrid);
        gridView.setNumColumns((int) Math.sqrt(mSimulationGrid.getGridSize()));
        GridAdapter adapter = new GridAdapter(this, mSimulationGrid.getTiles());
        gridView.setAdapter(adapter);

        // Setup the Reverse Button
        findViewById(R.id.ibRvs).setOnClickListener(view -> {
            //TODO Implement reverse functionality here
        });
        // Setup the Forward Button
        findViewById(R.id.ibFwd).setOnClickListener(view -> {
            mSimulationGrid.progressLifeForms();
        });
    }
}