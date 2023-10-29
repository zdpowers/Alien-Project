package com.ccsu.designpatterns.fall23.alieninvasionsim;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ccsu.designpatterns.fall23.alieninvasionsim.Grid.Grid;

public class MainActivity extends AppCompatActivity {
    private Grid mSimulationGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Build the simulation grid
        mSimulationGrid = new Grid(10);
    }
}