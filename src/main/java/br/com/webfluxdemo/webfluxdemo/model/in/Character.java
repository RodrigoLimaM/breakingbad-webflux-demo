package br.com.webfluxdemo.webfluxdemo.model.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Character {

    @JsonProperty(value = "char_id")
    private Long charId;

    private String name;

    private String nickname;

    private String birthday;

    @JsonProperty(value = "occupation")
    private List<String> occupations;

    private String status;

    private String portrayed;

    private String img;

}
