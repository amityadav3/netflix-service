package com.reactivespring.Netflixinfoservice.controller;

import com.reactivespring.Netflixinfoservice.model.MovieInfo;
import com.reactivespring.Netflixinfoservice.repository.MovieInfoRepository;
import com.reactivespring.Netflixinfoservice.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class MoviesInfoController {
    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private MovieInfoRepository movieInfoRepository;

    @GetMapping("/findByCountry/{country}")
    public Flux<MovieInfo> getShowsByCountry(@Valid @PathVariable(value="country", required = true) String country){
        return movieInfoService.getShowsByCountry(country);
    }

    @PutMapping("/updateMovie/{title}/{releaseYear}")
    public  Mono<ResponseEntity<MovieInfo>>updateMovieFromTitle(@Valid @PathVariable(value="title",required = true) String title,
                                                                @PathVariable(value="releaseYear",required = true) String releaseYear){
        return movieInfoService.updateMovieFromTitle(title,releaseYear)
                .map(movieInfo -> {
                    return ResponseEntity.ok().body(movieInfo);
                })
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping("/save")
    public Mono<MovieInfo> saveAllMovies(@Valid @RequestBody MovieInfo movieInfo){
        System.out.println(movieInfo);
        return movieInfoService.saveAllMovies(movieInfo);
    }
}
