package com.test.vkphotoviewer;

import android.content.Context;
import android.content.Intent;

import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PhotoGetter {
    private Context context;
    ImageContainer container = new ImageContainer();
    public int count = 0;
    public int shift = 0;
    final public int numOfPhoto = 30;

    VKRequest.VKRequestListener req = new VKRequest.VKRequestListener() {
        @Override
        public void onComplete(VKResponse response) {
            super.onComplete(response);
            getLinks(response);
        }
    };

    PhotoGetter(Context context){
        this.context = context;
    }


    public void getLinks(VKResponse response){
        try {
            JSONObject jsonObject = (JSONObject) response.json.get("response");
            this.count = (int) jsonObject.get("count");
            this.shift = (this.shift + numOfPhoto > this.count) ? this.count : this.shift + numOfPhoto;
            JSONArray jsonArray = (JSONArray) jsonObject.get("items");
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject currentObj = (JSONObject) jsonArray.get(i);
                JSONArray sizesArray = (JSONArray) currentObj.get("sizes");
                JSONObject bestPhoto = null;
                JSONObject worstPhoto = null;
                int min = 1000000000;
                int max = -1;
                for (int j = 0; j < sizesArray.length(); ++j) {
                    JSONObject currentPhoto = (JSONObject) sizesArray.get(j);
                    int width = (int) currentPhoto.get("width");
                    int height = (int) currentPhoto.get("height");
                    if (width*height < min){
                        min=height*width;
                        worstPhoto = currentPhoto;
                    }
                    if (width * height > max) {
                        max = width * height;
                        bestPhoto = currentPhoto;
                    }
                }
                this.container.add(worstPhoto.get("src").toString(),
                        bestPhoto.get("src").toString(),
                        currentObj.get("text").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getAllPhoto(this.count,this.shift);
    }

    private void sendIntent(){
        Intent intent = new Intent(context, View_Grid.class);

        intent.putExtra("ARRAY_OF_SMALL_SIZES",this.container.getArrayOfSmallSize());
        intent.putExtra("ARRAY_OF_BIG_SIZES",this.container.getArrayOfBigSize());
        intent.putExtra("ARRAY_OF_TITLES",this.container.getArrayOfTitle());

        context.startActivity(intent);
    }

    public void getAllPhoto() {
        getAllPhoto(-1,0);
    }

    public void getAllPhoto(int count, int shift) {
        if (count == shift) {
            sendIntent();
        } else {
            VKRequest request = new VKRequest("photos.getAll", VKParameters.from(VKApiConst.PHOTO_SIZES,
                    1, VKApiConst.COUNT, numOfPhoto, VKApiConst.OFFSET, shift));
            request.executeWithListener(req);
        }
    }
}
