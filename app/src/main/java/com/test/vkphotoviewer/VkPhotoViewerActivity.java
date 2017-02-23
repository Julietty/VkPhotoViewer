package com.test.vkphotoviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ListView;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKError;
import com.vk.sdk.VKCallback;
import android.content.Intent;
import android.view.View;

public class VkPhotoViewerActivity extends AppCompatActivity {
    private String[] scope = new String[]{VKScope.PHOTOS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vk_photo_viewer);

        VKSdk.login(this, scope);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Toast.makeText(getApplicationContext(),"Авторизация успешно завершена",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onClickStartViewing(View view){
        Intent intent = new Intent(this,View_Grid.class);
        startActivity(intent);
    }

    public void onClickGetPhoto(View view){
        PhotoGetter allPhoto = new PhotoGetter(VkPhotoViewerActivity.this);
        allPhoto.getAllPhoto();
    }

}