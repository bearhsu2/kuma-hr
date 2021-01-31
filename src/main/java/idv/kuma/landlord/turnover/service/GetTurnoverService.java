package idv.kuma.landlord.turnover.service;


import idv.kuma.landlord.turnover.entity.Contract;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class GetTurnoverService {

    private ContractRepository repository;


    public GetTurnoverService(ContractRepository mockedRepository) {
        this.repository = mockedRepository;
    }


    public long calculate(YearMonth yearMonth) throws GetTurnoverServiceException {
        // 向 Repository 要全部 Contracts，一個一個算 turnover，再加起來 return 出去。

        Contract contract = repository.findAll().get(0);

        if (contract.getEndDate().isBefore(yearMonth.atEndOfMonth())) {

            long diff = DAYS.between(yearMonth.atDay(1), contract.getEndDate()) + 1;

            return diff >= 15 ? contract.getRent() : contract.getRent() / 2;

        } else if (contract.getStartDate().isAfter(yearMonth.atDay(1))) {

            long diff = DAYS.between(contract.getStartDate(), yearMonth.atEndOfMonth()) + 1;

            return diff >= 15 ? contract.getRent() : contract.getRent() / 2;
        }


        return contract.getRent();
    }
}
