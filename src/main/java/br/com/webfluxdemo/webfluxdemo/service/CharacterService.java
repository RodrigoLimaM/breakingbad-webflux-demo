package br.com.webfluxdemo.webfluxdemo.service;

import br.com.webfluxdemo.webfluxdemo.client.BreakingBadApiClient;
import br.com.webfluxdemo.webfluxdemo.model.out.CharacterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CharacterService {

    @Autowired
    CharacterConverter characterConverter;

    @Autowired
    BreakingBadApiClient breakingBadApiClient;

    public Mono<CharacterInfo> getCharacterByName(String name) {
        return Mono.just(name)
                .flatMap(breakingBadApiClient::getCharacterByName)
                .map(character -> characterConverter.convert(character));
    }
}
