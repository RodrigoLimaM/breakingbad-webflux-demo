package br.com.webfluxdemo.webfluxdemo.model.in;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Character {

    private Long char_id;

    private String name;

    private String birthday;

    private List<String> occupation;

    private String status;

    private String portrayed;

    private String img;
}
