package com.reactivespring.Netflixinfoservice.service;

import com.reactivespring.Netflixinfoservice.model.MovieInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import java.util.List;

@WebFluxTest
public class MovieInfoServiceTest {

    @BeforeAll
    static void init(){
        var moviesInfo = List.of(new
                        MovieInfo(null, "Movie", "1 Chance 2 Dance", "Adam Deyoe",
                        "Lexi Giovagnoli, Justin Ray, Rae Latt, Poonam Basu, Teresa Biter, Kalilah Harris, Alexia Dox, Adam Powell, Sean McBride",
                        "United States", "July 1, 2017", "1990", "TV-PG","89 min",
                        "Dramas, Romantic Movies", "When an aspiring dancer is uprooted during her senior year of high school, she finds herself torn between two boys – and with one shot at her dream."),
                new MovieInfo(null, "Series", "Mongodb", "Adam Deyoe",
                        "Lexi Giovagnoli, Justin Ray, Rae Latt, Poonam Basu, Teresa Biter, Kalilah Harris, Alexia Dox, Adam Powell, Sean McBride",
                        "India", "July 1, 2017", "1990", "TV-PG","89 min",
                        "Dramas, Romantic Movies", "When an aspiring dancer is uprooted during her senior year of high school, she finds herself torn between two boys – and with one shot at her dream.")
        );
        System.out.println("INITIALISATION COMPLETED");
    }
    @Test
    void getShowsByCountry() {
    }
}