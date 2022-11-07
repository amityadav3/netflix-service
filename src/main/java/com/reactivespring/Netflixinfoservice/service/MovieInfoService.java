package com.reactivespring.Netflixinfoservice.service;

import com.reactivespring.Netflixinfoservice.model.MovieInfo;
import com.reactivespring.Netflixinfoservice.repository.MovieInfoRepository;
import com.reactivespring.Netflixinfoservice.utilities.MovieUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class MovieInfoService {
    @Autowired
    private MovieInfoRepository movieInfoRepository;
    @Autowired
    private MovieUtility movieUtility;

    @PostConstruct
    public void initialize() throws IOException {
        List<MovieInfo> movies = movieUtility.initialLoad();
        initialLoad(movies);
    }

    public void initialLoad(List<MovieInfo> movies) {
        movieInfoRepository.saveAll(movies).subscribe();
        //System.out.println(movie);
    }
    public Flux<MovieInfo> getShowsByCountry(String country){
        return movieInfoRepository.findByCountry(country);
    }
    public Mono<MovieInfo> updateMovieFromTitle(String title, String releaseYear) {
        return movieInfoRepository.findByTitle(title)
                .flatMap(movieInfo -> {
                    movieInfo.setRelease_year(releaseYear);
                    return movieInfoRepository.save(movieInfo);
                });
    }


    public Mono<MovieInfo> saveAllMovies(MovieInfo movieInfo){
        return movieInfoRepository.save(movieInfo);
    }

}
