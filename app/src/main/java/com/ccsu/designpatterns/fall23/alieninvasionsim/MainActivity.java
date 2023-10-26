package com.ccsu.designpatterns.fall23.alieninvasionsim;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ccsu.designpatterns.fall23.alieninvasionsim.Gridfeatures.GridBuilder;

public class MainActivity extends AppCompatActivity {

    int GridSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    GridBuilder myGrid = new GridBuilder(GridSize);


}