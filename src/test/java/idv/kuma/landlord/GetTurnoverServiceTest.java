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

    @Test
    void All_Ok() throws GetTurnoverServiceException {



        ContractRepository mockedRepository = Mockito.mock(ContractRepository.class);
        Mockito.when(mockedRepository.findAll()).thenReturn(

                Arrays.asList(
                        new Contract(30L, LocalDate.of(1234, 6, 30))
                )
        );

        GetTurnoverService service = new GetTurnoverService(mockedRepository);

        long actual = service.calculate(YearMonth.of(1234, 6));


        assertEquals(30L, actual);

    }
    @Test
    void Early_leave_short() throws GetTurnoverServiceException {



        ContractRepository mockedRepository = Mockito.mock(ContractRepository.class);
        Mockito.when(mockedRepository.findAll()).thenReturn(

                Arrays.asList(
                        new Contract(30L, LocalDate.of(1234, 6, 14))
                )
        );

        GetTurnoverService service = new GetTurnoverService(mockedRepository);

        long actual = service.calculate(YearMonth.of(1234, 6));


        assertEquals(15L, actual);

    }

}