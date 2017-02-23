package com.test.vkphotoviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.AdapterView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;

public class View_Grid extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__grid);

        final ImageContainer container = new ImageContainer(
                (ArrayList<String>)getIntent().getExtras().get("ARRAY_OF_SMALL_SIZES"),
                (ArrayList<String>)getIntent().getExtras().get("ARRAY_OF_BIG_SIZES"),
                (ArrayList<String>)getIntent().getExtras().get("ARRAY_OF_TITLES"));



        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this, container));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                Intent intent = new Intent(View_Grid.this,FullScreenView.class);
                intent.putExtra("PHOTOS", container.getArrayOfBigSize());
                intent.putExtra("TITLES", container.getArrayOfTitle());
                intent.putExtra("POSITION", position);
                startActivity(intent);
            }
        });

    }


}
