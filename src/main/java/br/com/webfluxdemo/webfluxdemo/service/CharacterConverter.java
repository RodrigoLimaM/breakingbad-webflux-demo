package br.com.webfluxdemo.webfluxdemo.service;

import br.com.webfluxdemo.webfluxdemo.model.in.Character;
import br.com.webfluxdemo.webfluxdemo.model.out.CharacterInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CharacterConverter implements Converter<Character, CharacterInfo> {

    private final static Pattern REPLACE_LAST_COMMA_TO_AND = Pattern.compile(",(?=[^,]+$)");

    private final static DateTimeFormatter ddMMyyyy_WITH_TRACE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final static DateTimeFormatter ddMMyyyy_WHIT_STRIPE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public CharacterInfo convert(Character source) {
        return CharacterInfo.builder()
                .characterDescription(createCharacterDescription(source))
                .image(source.getImg())
                .build();
    }

    private String createCharacterDescription(Character source) {
        return source.getName() +
                " was born in " +
                formatDate(source.getBirthday()) +
                ", has as nickname " +
                source.getNickname() +
                ", the character occupation(s) are " +
                getOccupations(source.getOccupations()) +
                ", is " +
                source.getStatus() +
                " and is acted by " +
                source.getPortrayed() +
                ".";
    }

    private String formatDate(String birthday) {
        if(birthday.equals("Unknown"))
            return "a unknown date";

        LocalDate date = LocalDate.parse(birthday, ddMMyyyy_WITH_TRACE_FORMATTER);

        return date.format(ddMMyyyy_WHIT_STRIPE_FORMATTER);
    }

    private String getOccupations(List<String> occupations) {
        return String.join(", ", occupations).replaceFirst(REPLACE_LAST_COMMA_TO_AND.pattern(), " and");
    }
}
