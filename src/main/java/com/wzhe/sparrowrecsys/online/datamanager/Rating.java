package com.wzhe.sparrowrecsys.online.datamanager;

/**
 * Rating Class, contains attributes loaded from movielens ratings.csv
 */
public class Rating {
    int movieId;    // 电影id
    int userId;     // 用户id
    float score;    // 评分
    long timestamp; // 评分时间

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
