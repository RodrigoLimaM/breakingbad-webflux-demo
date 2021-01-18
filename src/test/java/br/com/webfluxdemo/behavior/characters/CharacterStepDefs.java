package br.com.webfluxdemo.behavior.characters;

import br.com.webfluxdemo.model.out.CharacterInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

public class CharacterStepDefs {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    private MvcResult mvcResult;

    private String name;

    private CharacterInfo characterInfo;

    @Dado("o personagem com o nome {string}")
    public void o_personagem_com_o_nome(String name) {
        this.name = name;
    }

    //TODO improve this
    @Quando("for consultado as informações")
    public void for_consultado_as_informacoes() throws Exception {
        ResponseEntity<CharacterInfo > responseEntityCharacterInfo = (ResponseEntity<CharacterInfo>) mockMvc.perform(MockMvcRequestBuilders.get("/character")
                .param("name", name))
                .andReturn()
                .getAsyncResult()
                ;

        this.characterInfo = responseEntityCharacterInfo.getBody();
    }

    @Entao("ele deve retornar a seguinte resposta")
    public void ele_deve_retornar_a_seguinte_resposta(final String responseJson) throws Exception {
        final var expected = this.objectMapper.readValue(responseJson, CharacterInfo.class);

        Assertions.assertEquals(expected, this.characterInfo);
    }
}
