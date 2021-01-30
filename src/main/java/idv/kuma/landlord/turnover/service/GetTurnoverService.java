package idv.kuma.landlord.turnover.service;


import idv.kuma.landlord.turnover.entity.Contract;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

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
            return contract.getRent() / 2;
        }


        return contract.getRent();
    }
}
