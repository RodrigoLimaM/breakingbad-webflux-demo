package br.com.webfluxdemo.controller;

import br.com.webfluxdemo.model.out.CharacterInfo;
import br.com.webfluxdemo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class BreakingBadCharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/character")
    public Mono<ResponseEntity> getCharacterByName(@RequestParam String name) {
        return characterService.getCharacterByName(name)
                .map(ResponseEntity::ok)
                .cast(ResponseEntity.class)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping("/characters")
    public Flux<CharacterInfo> getCharacters() {
        return characterService.getCharacters();
    }
}
