package com.example.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class Controller {


    @GetMapping("/demo")
    public Mono<String> greetingMessage() {
        return computeMessage().zipWith(getNameFromDb())
                .map(value -> value.getT1() + value.getT2());
    }

    private Mono<String> computeMessage() {
        return Mono.just("Hello ").delayElement(Duration.ofSeconds(3));
    }

    @GetMapping
    public String greet() {
        return "Hi!";
    }

    private Mono<String> getNameFromDb() {
        return Mono.just("Olena!").delayElement(Duration.ofSeconds(3));
    }
}
