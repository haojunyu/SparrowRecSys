package com.wzhe.sparrowrecsys.online.datamanager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wzhe.sparrowrecsys.online.model.Embedding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Movie Class, contains attributes loaded from movielens movies.csv and other advanced data like averageRating, emb, etc.
 */
public class Movie {
    int movieId;    // 电影id
    String title;   // 电影名称
    int releaseYear;    // 发行年份
    String imdbId;  // imdb的id
    String tmdbId;  // tmdb的id
    List<String> genres;    // 电影类型，多个
    //how many user rate the movie
    int ratingNumber;   // 电影评分人数
    //average rating score
    double averageRating;   // 电影评分

    //embedding of the movie 该字段Json序列化时会忽略
    @JsonIgnore
    Embedding emb;  // 电影embeding

    //all rating scores list 该字段Json序列化时会忽略
    @JsonIgnore
    List<Rating> ratings;  // 电影所有评分

    final int TOP_RATING_SIZE = 10;

    @JsonSerialize(using = RatingListSerializer.class)
    List<Rating> topRatings;

    public Movie() {
        ratingNumber = 0;
        averageRating = 0;
        this.genres = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.topRatings = new LinkedList<>();
        this.emb = null;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void addGenre(String genre){
        this.genres.add(genre);
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void addRating(Rating rating) {
        averageRating = (averageRating * ratingNumber + rating.getScore()) / (ratingNumber+1);
        ratingNumber++;
        this.ratings.add(rating);
        addTopRating(rating);
    }

    // TODO：使用堆是不是效率更高？？ PriorityQueue
    public void addTopRating(Rating rating){
        if (this.topRatings.isEmpty()){
            this.topRatings.add(rating);
        }else{
            int index = 0;
            for (Rating topRating : this.topRatings){
                if (topRating.getScore() >= rating.getScore()){
                    break;
                }
                index ++;
            }
            topRatings.add(index, rating);
            if (topRatings.size() > TOP_RATING_SIZE) {
                topRatings.remove(0);
            }
        }
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public Embedding getEmb() {
        return emb;
    }

    public void setEmb(Embedding emb) {
        this.emb = emb;
    }
}
