package ru.live.toofast.mortgage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageList;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MortgageSaveTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testSaving() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/mortgage")
                .content("{ \"name\":\"Yuri\" }")
                .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/mortgages"))
                .andReturn()
                .getResponse()
                .getContentAsString();


        MortgageList response = objectMapper.readValue(contentAsString, MortgageList.class);
        Assertions.assertThat(response.getValues()).hasSize(2);
    }



}
