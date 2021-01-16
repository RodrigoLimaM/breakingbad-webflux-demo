package br.com.webfluxdemo.webfluxdemo.client;

import br.com.webfluxdemo.webfluxdemo.config.BreakingBadApiProperties;
import br.com.webfluxdemo.webfluxdemo.model.in.Character;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.logging.Level;

@Component
public class BreakingBadApiClient {

    private WebClient webClient;

    public BreakingBadApiClient(BreakingBadApiProperties breakingBadApiProperties, WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl(breakingBadApiProperties.getUrl())
                .build();
    }

    public Mono<Character> getCharacterByName(String name) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                .path("/api/characters/")
                .queryParam("name", name)
                        .build())
                .retrieve()
                .bodyToFlux(Character.class)
                .distinct()
                .next()
                .log("br.com.webfluxdemo.webfluxdemo.client.BreakingBadApiClient", Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR);
    }
}
