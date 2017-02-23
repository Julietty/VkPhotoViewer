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

        ImageContainer container = new ImageContainer(
                (ArrayList<String>)getIntent().getExtras().get("ARRAY_OF_SMALL_SIZES"),
                (ArrayList<String>)getIntent().getExtras().get("ARRAY_OF_BIG_SIZES"),
                (ArrayList<String>)getIntent().getExtras().get("ARRAY_OF_TITLES"));

        System.out.println("Что тут у нас");
        for (int i = 0; i<container.length();++i) {
            System.out.println(container.getSmallSize(i));
            System.out.println(container.getBigSize(i));
            System.out.println(container.getTitle(i));
        }


        /*ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Picasso.with(this)
                .load(url.get(0))
                .into(imageView);*/


       // ImageView imageView = (ImageView) findViewById(R.id.imageView);
       // ImageView imageView = new ImageView(null);
      /*  ArrayList<ImageView> pictures = new ArrayList<ImageView>();
        for (int i = 0; i < url.size(); ++i){
            Picasso.with(this)
                    .load(url.get(i))
                    .into(imageView);
            pictures.add(imageView);
        }*/
        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this, container));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                GridItem item = (GridItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(View_Grid.this,FullScreenView.class);
                intent.putExtra("PHOTO", item.getBigSize());
                System.out.println("fromCliker");
                if (item.getTitle().isEmpty()) System.out.println("isEmpty");
                intent.putExtra("TITLE", item.getTitle());
                startActivity(intent);
            }
        });



        //VKRequest request = VKApi.photos().saveMessagesPhoto(VKParameters.from(VKApiConst.FIELDS, "album_id"));
        //VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.ONLINE,1));
        //VKRequest request = new VKRequest("photos.getAll");
        // VKRequest request = new VKRequest("friends.get", VKParameters.from(VKApiConst.FIELDS, "o"));
        //request.attempts = 50

    }


}
