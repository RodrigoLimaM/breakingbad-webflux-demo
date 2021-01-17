package br.com.webfluxdemo.client;

import br.com.webfluxdemo.config.BreakingBadApiProperties;
import br.com.webfluxdemo.model.in.Character;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.logging.Level;

@Component
public class BreakingBadApiClient {

    public static final String CHARACTERS_ENDPOINT = "/api/characters";

    public static final String CLIENT_PACKAGE = "br.com.webfluxdemo.client.BreakingBadApiClient";

    private WebClient webClient;

    public BreakingBadApiClient(BreakingBadApiProperties breakingBadApiProperties, WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl(breakingBadApiProperties.getUrl())
                .build();
    }

    public Mono<Character> getCharacterByName(String name) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                .path(CHARACTERS_ENDPOINT)
                .queryParam("name", name)
                        .build())
                .retrieve()
                .bodyToFlux(Character.class)
                .distinct()
                .next()
                .log(CLIENT_PACKAGE, Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR);
    }

    public Flux<Character> getCharacters() {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                .path(CHARACTERS_ENDPOINT)
                .build())
                .retrieve()
                .bodyToFlux(Character.class)
                .log(CLIENT_PACKAGE, Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR);
    }
}
