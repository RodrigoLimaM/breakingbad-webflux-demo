package br.com.webfluxdemo.model.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class CharacterInfo {

    private String characterDescription;

    private String image;
}
