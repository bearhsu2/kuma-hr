package idv.kuma.kumahr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.DateTimeException;
import java.time.YearMonth;

@RestController
public class GetTurnoverController {


    private final GetTurnoverService service;


    @Autowired
    public GetTurnoverController(GetTurnoverService service) {
        this.service = service;
    }


    @GetMapping("/turnover/{year}/{month}")
    public long getTurnover(@PathVariable int year, @PathVariable int month) {

        try {
            return service.calculate(YearMonth.of(year, month));

        } catch (DateTimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


    }
}
