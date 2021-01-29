package com.wzhe.sparrowrecsys.online.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzhe.sparrowrecsys.online.datamanager.Movie;
import com.wzhe.sparrowrecsys.online.recprocess.SimilarMovieProcess;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * SimilarMovieService, recommend similar movies given by a specific movie
 * 相似电影服务
 * http://localhost:6010/getsimilarmovie?movieId=924&size=16&model=emb
 */
public class SimilarMovieService extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");

            //movieId 电影id
            String movieId = request.getParameter("movieId");
            //number of returned movies 返回电影数目
            String size = request.getParameter("size");
            //model of calculating similarity, e.g. embedding, graph-embedding 相似度模型
            String model = request.getParameter("model");

            //use SimilarMovieFlow to get similar movies
            List<Movie> movies = SimilarMovieProcess.getRecList(Integer.parseInt(movieId), Integer.parseInt(size), model);

            //convert movie list to json format and return
            ObjectMapper mapper = new ObjectMapper();
            String jsonMovies = mapper.writeValueAsString(movies);
            response.getWriter().println(jsonMovies);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("");
        }
    }
}
