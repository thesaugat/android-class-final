package com.thesaugat.androidclass.dataClases;

import java.util.ArrayList;

public class FeedData {

    String imageUrl;
    String personName;
    String dateTime;
    String description;
    int likeCount;
    ArrayList<LikeData> likeData;


   public void setImageUrl(String imageUrl){
       this.imageUrl = imageUrl;
   }

   public String getImageUrl(){
       return imageUrl;

   }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public ArrayList<LikeData> getLikeData() {
        return likeData;
    }

    public void setLikeData(ArrayList<LikeData> likeData) {
        this.likeData = likeData;
    }


}
