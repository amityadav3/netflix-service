package com.reactivespring.Netflixinfoservice.controller;

import com.reactivespring.Netflixinfoservice.model.MovieInfo;
import com.reactivespring.Netflixinfoservice.repository.MovieInfoRepository;
import com.reactivespring.Netflixinfoservice.service.MovieInfoService;
import com.reactivespring.Netflixinfoservice.utilities.MovieUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = MoviesInfoController.class)
@AutoConfigureWebTestClient
class MoviesInfoControllerUnitTest {

    @Autowired
    WebTestClient webTestClient;

    static List<MovieInfo> movieList;
    @MockBean
    private MovieInfoService movieInfoServiceMock;

    @MockBean
    private MovieInfoRepository movieInfoRepositoryMock;

    @MockBean
    private MovieUtility movieUtility;

    @BeforeAll
    static void init(){
        movieList= new ArrayList<>();
        movieList.add(new
                        MovieInfo("s580", "Movie", "1 Chance 2 Dance", "Adam Deyoe",
                        "Lexi Giovagnoli, Justin Ray, Rae Latt, Poonam Basu, Teresa Biter, Kalilah Harris, Alexia Dox, Adam Powell, Sean McBride",
                        "United States", "July 1, 2017", "1990", "TV-PG","89 min",
                        "Dramas, Romantic Movies", "When an aspiring dancer is uprooted during her senior year of high school, she finds herself torn between two boys – and with one shot at her dream."));
        movieList.add(new
                        MovieInfo("s581", "Series", "Mongodb", "Adam Deyoe",
                        "Lexi Giovagnoli, Justin Ray, Rae Latt, Poonam Basu, Teresa Biter, Kalilah Harris, Alexia Dox, Adam Powell, Sean McBride",
                        "India", "July 1, 2017", "1990", "TV-PG","89 min",
                        "Dramas, Romantic Movies", "When an aspiring dancer is uprooted during her senior year of high school, she finds herself torn between two boys – and with one shot at her dream.")
        );
    }
    @Test
    void getShowsByCountry() {
        when(movieInfoServiceMock.getShowsByCountry(isA(String.class))).thenReturn(Flux.fromIterable(findByCountry("India")));

        webTestClient
                .get()
                .uri("http://localhost:8080/v1/findByCountry/India")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(MovieInfo.class)
                .hasSize(1);
    }

    @Test
    void updateMovieFromTitle() {
        var releaseYear = "2025";
        when(movieInfoServiceMock.updateMovieFromTitle(isA(String.class),isA(String.class)))
                .thenReturn(Mono.just(updateMovieFromTitle("Mongodb",releaseYear)));

        webTestClient
                .put()
                .uri("http://localhost:8080/v1/updateMovie/Mongodb/2025")
                .bodyValue(MovieInfo.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(MovieInfo.class)
                .consumeWith(moviesInfoEntityExchangeResult -> {
                    var savedMovieInfo = moviesInfoEntityExchangeResult.getResponseBody();
                    assert savedMovieInfo != null;
                    assert savedMovieInfo.get(0) !=null;
                    assert savedMovieInfo.get(0).getRelease_year().equals(releaseYear);
                });
    }


    public List<MovieInfo> findByCountry(String country){
        System.out.println(movieList);
        return movieList.stream()
                .filter(each -> each.getCountry().equalsIgnoreCase("India"))
                .collect(Collectors.toList());
    }

    public MovieInfo updateMovieFromTitle(String title, String releaseYear){
        return movieList.stream()
                .filter(each -> each.getTitle().equalsIgnoreCase(title))
                .peek(each -> each.setRelease_year(releaseYear))
                .findFirst().orElse(null);
    }
}