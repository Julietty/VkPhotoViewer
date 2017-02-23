package com.test.vkphotoviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
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
        ImageView image = (ImageView)findViewById(R.id.background);
        image.setImageResource(R.drawable.background);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Toast.makeText(getApplicationContext(),R.string.success_login,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(),R.string.error_login,Toast.LENGTH_LONG).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    public void onClickGetPhoto(View view){
        if (!VKSdk.isLoggedIn()){
            Toast.makeText(getApplicationContext(),R.string.error_login,Toast.LENGTH_LONG).show();
        }else {
            PhotoGetter allPhoto = new PhotoGetter(VkPhotoViewerActivity.this);
            allPhoto.getAllPhoto();
        }
    }

    public void onClickLogin(View view){
        VKSdk.initialize(this);
        if (VKSdk.isLoggedIn()) {
            VKSdk.logout();
        }
        VKSdk.login(this, scope);
    }
}