package idv.kuma.kumahr;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.YearMonth;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class GetTurnoverControllerTest {


    @MockBean
    public GetTurnoverService getTurnoverService;
    public ResultActions actual;
    @Autowired
    MockMvc mockMvc;


    @Test
    void internal_error() throws Exception {

        Mockito.when(getTurnoverService.calculate(YearMonth.of(1234, 12))).thenThrow(new GetTurnoverServiceException());

        when_user_query("/turnover/1234/12");

        then_return_code_should_be(500);
    }


    private void when_user_query(String url) throws Exception {
        actual = mockMvc.perform(MockMvcRequestBuilders.get(url));
    }


    private void then_return_code_should_be(int status) throws Exception {
        actual.andExpect(status().is(status));
    }


    @Test
    void illegal_month() throws Exception {

        assume_service_returns(2000L);

        when_user_query("/turnover/1234/-1");

        then_return_code_should_be(400);
    }


    private void assume_service_returns(long expect) throws GetTurnoverServiceException {
        Mockito.when(getTurnoverService.calculate(any(YearMonth.class))).thenReturn(expect);
    }


    @Test
    void all_ok() throws Exception {


        assume_service_would_act_like(1234, 12, 2000L);


        when_user_query("/turnover/1234/12");

        then_return_ok();
        then_content_should_be("2000");
    }


    private void assume_service_would_act_like(int year, int month, long expect) throws GetTurnoverServiceException {
        YearMonth yearMonth = YearMonth.of(year, month);
        Mockito.when(getTurnoverService.calculate(yearMonth)).thenReturn(expect);
    }


    private void then_return_ok() throws Exception {
        actual.andExpect(status().isOk());
    }


    private void then_content_should_be(String content) throws Exception {
        actual.andExpect(content().string(content));
    }
}