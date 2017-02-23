package com.test.vkphotoviewer;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FullScreenView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_view);

        String photoURL = new String((String)getIntent().getExtras().get("PHOTO"));
        String title = new String((String)getIntent().getExtras().get("TITLE"));
        View view = new View(this);

        ImageView image = (ImageView)findViewById(R.id.grid_item_image);
        TextView text = (TextView) findViewById(R.id.grid_item_title);
        if (!title.isEmpty()) {
            text.setText(title);
        }else{
            text.setVisibility(View.INVISIBLE);
        }
        Picasso.with(this).load(photoURL).into(image);
        System.out.println("hui");
        System.out.print("miu"+title.length());
    }

}
