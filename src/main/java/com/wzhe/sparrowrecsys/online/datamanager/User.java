package com.wzhe.sparrowrecsys.online.datamanager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wzhe.sparrowrecsys.online.model.Embedding;

import java.util.ArrayList;
import java.util.List;

/**
 * User class, contains attributes loaded from movielens ratings.csv
 */
public class User {
    int userId;     // 用户id
    double averageRating = 0;   // 用户平均评分
    double highestRating = 0;   // 用户最高评分
    double lowestRating = 5.0;  // 用户最低评分
    int ratingCount = 0;    // 评分次数

    @JsonSerialize(using = RatingListSerializer.class)
    List<Rating> ratings;

    //embedding of the movie 该字段Json序列化时会忽略
    @JsonIgnore
    Embedding emb;

    public User(){
        this.ratings = new ArrayList<>();
        this.emb = null;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    // 这里不适用堆PriorityQueue，因为不需要取topK
    public void addRating(Rating rating) {
        this.ratings.add(rating);
        this.averageRating = (this.averageRating * ratingCount + rating.getScore()) / (ratingCount + 1);
        if (rating.getScore() > highestRating){
            highestRating = rating.getScore();
        }

        if (rating.getScore() < lowestRating){
            lowestRating = rating.getScore();
        }

        ratingCount++;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public double getHighestRating() {
        return highestRating;
    }

    public void setHighestRating(double highestRating) {
        this.highestRating = highestRating;
    }

    public double getLowestRating() {
        return lowestRating;
    }

    public void setLowestRating(double lowestRating) {
        this.lowestRating = lowestRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Embedding getEmb() {
        return emb;
    }

    public void setEmb(Embedding emb) {
        this.emb = emb;
    }
}
