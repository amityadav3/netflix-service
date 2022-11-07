package com.reactivespring.Netflixinfoservice.repository;

import com.reactivespring.Netflixinfoservice.model.MovieInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieInfoRepository extends ReactiveMongoRepository<MovieInfo, String> {
    Flux<MovieInfo> findByCountry(String country);

    Mono<MovieInfo> findByTitle(String title);

}
