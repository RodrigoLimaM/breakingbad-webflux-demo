package br.com.webfluxdemo.webfluxdemo.controller;

import br.com.webfluxdemo.webfluxdemo.model.out.CharacterInfo;
import br.com.webfluxdemo.webfluxdemo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/character")
public class BreakingBadCharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public Flux<CharacterInfo> getCharacterByName(@RequestParam String name) {
        return characterService.getCharacterByName(name);
    }
}
