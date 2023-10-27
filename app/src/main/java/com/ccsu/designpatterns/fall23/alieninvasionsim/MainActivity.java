package com.ccsu.designpatterns.fall23.alieninvasionsim;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ccsu.designpatterns.fall23.alieninvasionsim.Grid.GridBuilder;

public class MainActivity extends AppCompatActivity {

    int GridSize = 10;
    private GridBuilder myGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Build the grid
        myGrid = new GridBuilder(GridSize);
    }
    //newtest
}