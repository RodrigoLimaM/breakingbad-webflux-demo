package br.com.webfluxdemo.webfluxdemo.service;

import br.com.webfluxdemo.webfluxdemo.model.in.Character;
import br.com.webfluxdemo.webfluxdemo.model.out.CharacterInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class CharacterConverter implements Converter<Character, CharacterInfo> {


    @Override
    public CharacterInfo convert(Character source) {
        return CharacterInfo.builder()
                .characterDescription(createCharacterDescription(source))
                .image(source.getImg())
                .build();
    }

    private String createCharacterDescription(Character source) {
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer.append(source.getName())
                .append(" is ")
                .append(source.getStatus())
                .append(" and is acted by ")
                .append(source.getPortrayed())
                .toString();
    }
}
