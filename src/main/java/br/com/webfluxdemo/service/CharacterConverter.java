package br.com.webfluxdemo.service;

import br.com.webfluxdemo.model.in.Character;
import br.com.webfluxdemo.model.out.CharacterInfo;
import br.com.webfluxdemo.util.DateUtils;
import br.com.webfluxdemo.util.TextPieces;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CharacterConverter implements Converter<Character, CharacterInfo> {

    private static final Pattern REPLACE_LAST_COMMA_TO_AND = Pattern.compile(",(?=[^,]+$)");

    private static final String UNKNOWN_BIRTHDAY_RESPONSE = "Unknown";

    private static final int TWO_OCCUPATIONS = 2;

    @Override
    public CharacterInfo convert(Character source) {
        return CharacterInfo.builder()
                .characterDescription(createCharacterDescription(source))
                .image(source.getImg())
                .build();
    }

    private String createCharacterDescription(Character source) {
        return source.getName() +
                TextPieces.BIRTHDAY_SUFFIX +
                formatBirthdayDate(source.getBirthday()) +
                TextPieces.NICKNAME_PREFIX +
                source.getNickname() +
                TextPieces.NICKNAME_SUFFIX +
                TextPieces.OCCUPATIONS_SUFFIX +
                getOccupations(source.getOccupations()).toLowerCase() +
                TextPieces.STATUS_SUFFIX +
                source.getStatus().toLowerCase() +
                TextPieces.PORTRAYED_SUFFIX +
                source.getPortrayed() +
                TextPieces.FINAL_DOT;
    }

    private String formatBirthdayDate(String birthdayDate) {
        if(birthdayDate.equals(UNKNOWN_BIRTHDAY_RESPONSE))
            return TextPieces.CHARACTER_UNKNOWN_BIRTHDATE;

        LocalDate date = LocalDate.parse(birthdayDate, DateUtils.ddMMyyyy_WITH_TRACE_FORMATTER);

        return date.format(DateUtils.ddMMyyyy_WHIT_STRIPE_FORMATTER);
    }

    private String getOccupations(List<String> occupations) {
        String phrasePrefix = occupations.size() < TWO_OCCUPATIONS ? TextPieces.SINGLE_OCCUPATIONS_PREFIX : TextPieces.MANY_OCCUPATIONS_PREFIX;
        return phrasePrefix +String.join(TextPieces.COMMA, occupations).replaceFirst(REPLACE_LAST_COMMA_TO_AND.pattern(), TextPieces.AND);
    }
}
