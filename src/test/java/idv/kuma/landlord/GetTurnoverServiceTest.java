package idv.kuma.landlord;

import idv.kuma.landlord.turnover.entity.Contract;
import idv.kuma.landlord.turnover.service.ContractRepository;
import idv.kuma.landlord.turnover.service.GetTurnoverService;
import idv.kuma.landlord.turnover.service.GetTurnoverServiceException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetTurnoverServiceTest {


    public ContractRepository repository;
    public long actual;


    @Test
    void All_Ok() throws GetTurnoverServiceException {

        given_contracts(new Contract(30L, LocalDate.of(1234, 6, 30)));

        when_query(1234, 6);

        then_should_return(30L);

    }


    private void given_contracts(Contract... contract) {
        repository = Mockito.mock(ContractRepository.class);
        Mockito.when(repository.findAll()).thenReturn(

                Arrays.asList(
                        contract
                )
        );
    }


    private void when_query(int year, int month) throws GetTurnoverServiceException {
        GetTurnoverService service = new GetTurnoverService(repository);

        actual = service.calculate(YearMonth.of(year, month));
    }


    private void then_should_return(long expected) {
        assertEquals(expected, actual);
    }


    @Test
    void Early_leave_short() throws GetTurnoverServiceException {

        given_contracts(new Contract(30L, LocalDate.of(1234, 6, 14)));

        when_query(1234, 6);

        then_should_return(15L);

    }
    @Test
    void Early_leave_long() throws GetTurnoverServiceException {

        given_contracts(new Contract(30L, LocalDate.of(1234, 6, 15)));

        when_query(1234, 6);

        then_should_return(30L);

    }

}