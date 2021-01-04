package idv.kuma.kumahr;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class GetTurnoverControllerTest {


    @Autowired
    MockMvc mockMvc;


    @MockBean
    public GetTurnoverService getTurnoverService;


    @Test
    void all_ok() throws Exception {


        Mockito.when(getTurnoverService.calculate(1234,12)).thenReturn(2000L);


        mockMvc.perform(MockMvcRequestBuilders.get("/turnover/1234/12"))
                .andExpect(status().isOk())
                .andExpect(content().string("2000"));
    }
}