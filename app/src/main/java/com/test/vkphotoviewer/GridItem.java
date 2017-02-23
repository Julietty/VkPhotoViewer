package com.test.vkphotoviewer;

public class GridItem {
    private String bigSize;
    private String smallSize;
    private String title;
    GridItem(){
        bigSize=new String("");
        smallSize=new String("");
        title=new String("");
    }
    GridItem(String smallSize, String bigSize, String title){
        this.smallSize = new String(smallSize);
        this.bigSize = new String(bigSize);
        this.title = new String(title);
    }
    public String getBigSize() {
        return bigSize;
    }
    public String getSmallSize(){
        return smallSize;
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setBigSize(String bigSize) {
        this.bigSize = bigSize;
    }
    public void setSmallSize(String smallSize) {
        this.smallSize = smallSize;
    }
}
