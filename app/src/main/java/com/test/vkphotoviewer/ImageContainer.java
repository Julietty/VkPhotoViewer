package com.test.vkphotoviewer;


import android.widget.GridView;
import android.widget.ImageView;

import java.sql.Struct;
import java.util.ArrayList;

public class ImageContainer{
    ArrayList<GridItem> imageArray;

    ImageContainer(){
        imageArray = new ArrayList<GridItem>();
    }

    ImageContainer(ImageContainer img){
        if (this != img) {
            this.imageArray = img.imageArray;
        }
    }
    ImageContainer(ArrayList<GridItem> imageArray){
        this.imageArray = imageArray;
    }
    ImageContainer(ArrayList<String>small, ArrayList<String> big, ArrayList<String> title){
        int size = small.size();
        imageArray = new ArrayList<GridItem>();
        for (int i = 0; i < size; ++i){
            imageArray.add(new GridItem(small.get(i),big.get(i),title.get(i)));
        }
    }

    ImageContainer(GridItem item){
        imageArray = new ArrayList<GridItem>();
        imageArray.add(item);
    }


    public void add(GridItem item){
        imageArray.add(item);
    }

    public void add(String small, String big, String text) {
        GridItem item = new GridItem(small,big,text);
        imageArray.add(item);
    }


    public void setSmallSize(String str, int position){
        imageArray.get(position).setSmallSize(str);
    }
    public void setBigSize(String str, int position){
        imageArray.get(position).setBigSize(str);
    }
    public void setTitle(String str, int position){
        imageArray.get(position).setTitle(str);
    }


    public int length(){
        return imageArray.size();
    }


    public String getSmallSize(int i){
        return imageArray.get(i).getSmallSize();
    }
    public String getBigSize(int i){
        return imageArray.get(i).getBigSize();
    }
    public String getTitle(int i){
        return imageArray.get(i).getTitle();
    }
    public GridItem getItem(int i){
        return imageArray.get(i);
    }

    public ArrayList<String> getArrayOfSmallSize(){
        ArrayList<String> list = new ArrayList<String>();
        int size = imageArray.size();
        for (int i = 0; i < size; ++i){
            list.add(this.imageArray.get(i).getSmallSize());
        }
        return list;
    }
    public ArrayList<String> getArrayOfBigSize(){
        ArrayList<String> list = new ArrayList<String>();
        int size = imageArray.size();
        for (int i = 0; i < size; ++i){
            list.add(this.imageArray.get(i).getBigSize());
        }
        return list;
    }
    public ArrayList<String> getArrayOfTitle(){
        ArrayList<String> list = new ArrayList<String>();
        int size = imageArray.size();
        for (int i = 0; i < size; ++i){
            list.add(this.imageArray.get(i).getTitle());
        }
        return list;
    }
}
