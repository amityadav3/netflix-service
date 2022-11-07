package com.reactivespring.Netflixinfoservice.utilities;


import com.reactivespring.Netflixinfoservice.controller.MoviesInfoController;
import com.reactivespring.Netflixinfoservice.model.MovieInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Component
public class MovieUtility {

    private final String dataFile = "src/main/resources/netflix_titles.csv";
    private static final Logger LOGGER = LoggerFactory.getLogger(MoviesInfoController.class);

    public List<MovieInfo> initialLoad() {
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFile));
            br.readLine(); // Skipping header
            List<MovieInfo> movies = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                MovieInfo m = MovieInfo.createObject(data);
                //System.out.println(m);
                movies.add(m);
            }
            //moviesInfoController.initialLoad(movies);
            LOGGER.info(String.format("%s movies initially loaded",movies.size()));
            System.out.println(String.format("%s movies initially loaded",movies.size()));
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private String getMovieRatingAsCsvRecord(MovieInfo r) {
        return r.getShow_id() + "," + r.getType() + "," + r.getTitle() + "," + r.getDirector() + "," + r.getCast() + ","
                + r.getCountry() + "," + r.getDate_added() + "," + r.getRelease_year() + "," + r.getRating() + ","
                + r.getDuration() + "," + r.getListed_in() + "," + r.getDescription();
    }

    public void insertRecordInFile(MovieInfo r) {
        File file = new File(dataFile);
        try {
            FileWriter fw = new FileWriter(dataFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File(dataFile));
            x.useDelimiter("[,\n]");
            while (x.hasNext()) {
                String id = x.next();
                if (id.equals(r.getShow_id())) {
                    pw.println(getMovieRatingAsCsvRecord(r));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Unable to insert new record into file");
        }
    }
}
