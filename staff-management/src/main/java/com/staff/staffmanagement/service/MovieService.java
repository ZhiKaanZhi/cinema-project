package com.staff.staffmanagement.service;

import com.staff.staffmanagement.mapstruct.dtos.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MovieService {

    private final WebClient webClient;

    @Autowired
    public MovieService(WebClient.Builder webClientBuilder, @Value("${movies.api.url}") String apiUrl) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
    }

    public Mono<MovieDto> getMovieById(Integer movieId) {
        return webClient.get()
                .uri("/{id}", movieId)
                .retrieve()
                .bodyToMono(MovieDto.class);
    }

    public Mono<MovieDto> getMovieByTitle(String movieTitle) {
        return webClient.get()
                .uri("/title/{movieTitle}", movieTitle)
                .retrieve()
                .bodyToMono(MovieDto.class);
    }
}
