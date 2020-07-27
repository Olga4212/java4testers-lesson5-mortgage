package ru.live.toofast.mortgage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageList;
import ru.live.toofast.mortgage.repository.MortgageApplicationRepository;

import javax.transaction.Transactional;
import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MortgageSaveTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MortgageApplicationRepository repository;

    @Test
    public void testSaving() throws Exception {

        repository.deleteAll();

        String name = "Yuri" + new Random().nextInt();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/mortgage")
                .content("{ \"name\":\"" + name +"\",\"salary\":1000,\"period\":100,\"creditAmount\":200 }")
                .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/mortgages"))
                .andReturn()
                .getResponse()
                .getContentAsString();


        MortgageList response = objectMapper.readValue(contentAsString, MortgageList.class);
        Assertions.assertThat(response.getValues().size())
                .isEqualTo(1);
        MortgageApplication application = response.getValues().get(0);

        Assertions.assertThat(application.getName())
                .isEqualTo(name);
        Assertions.assertThat(application.getStatus())
                .isEqualTo(MortgageApplication.Status.SUCCESS);
    }

    protected void prepareData() {
        repository.deleteAll();
        MortgageApplication ma = new MortgageApplication();
        ma.setStatus(MortgageApplication.Status.SUCCESS);
        ma.setName("Ivan");
        ma.setDeclineReason(MortgageApplication.DeclineReason.NONE);
        repository.save(ma);

        MortgageApplication ma2 = new MortgageApplication();
        ma2.setStatus(MortgageApplication.Status.DECLINE);
        ma2.setName("Semen");
        ma2.setDeclineReason(MortgageApplication.DeclineReason.SCORING_FAILED);
        repository.save(ma2);
    }

    @Test
    public void testGetDeclined() throws Exception {

        this.prepareData();

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/mortgages/declined"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        MortgageList response = objectMapper.readValue(contentAsString, MortgageList.class);
        Assertions.assertThat(response.getValues().size())
                .isEqualTo(1);
        MortgageApplication application = response.getValues().get(response.getValues().size()-1);

        Assertions.assertThat(application.getName())
                .isEqualTo("Semen");

    }

    @Test
    public void testGetSuccessful() throws Exception {

        this.prepareData();

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/mortgages/successful"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        MortgageList response = objectMapper.readValue(contentAsString, MortgageList.class);
        Assertions.assertThat(response.getValues().size())
                .isEqualTo(1);
        MortgageApplication application = response.getValues().get(response.getValues().size()-1);

        Assertions.assertThat(application.getName())
                .isEqualTo("Ivan");

    }


}
