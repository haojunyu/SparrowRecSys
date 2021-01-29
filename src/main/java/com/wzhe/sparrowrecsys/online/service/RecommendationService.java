package com.wzhe.sparrowrecsys.online.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzhe.sparrowrecsys.online.datamanager.DataManager;
import com.wzhe.sparrowrecsys.online.datamanager.Movie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * RecommendationService, provide recommendation service based on different input
 * 首页推荐服务
 * http://localhost:6010/getrecommendation?genre=Action&size=8&sortby=rating
 */

public class RecommendationService extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,
            IOException {
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");

            //genre - movie category 电影类型
            String genre = request.getParameter("genre");
            //number of returned movies 返回的电影个数
            String size = request.getParameter("size");
            //ranking algorithm 排序字段
            String sortby = request.getParameter("sortby");
            //a simple method, just fetch all the movie in the genre
            List<Movie> movies = DataManager.getInstance().getMoviesByGenre(genre, Integer.parseInt(size),sortby);

            //convert movie list to json format and return 返回 json 字段
            ObjectMapper mapper = new ObjectMapper();
            String jsonMovies = mapper.writeValueAsString(movies);
            response.getWriter().println(jsonMovies);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("");
        }
    }
}
