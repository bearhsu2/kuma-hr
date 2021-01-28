package idv.kuma.landlord.turnover.controller;

import idv.kuma.landlord.turnover.service.GetTurnoverService;
import idv.kuma.landlord.turnover.service.GetTurnoverServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Long> getTurnover(@PathVariable int year, @PathVariable int month) {

        try {

            return ResponseEntity.ok(
                    service.calculate(YearMonth.of(year, month))
            );

        } catch (DateTimeException e) {

            return ResponseEntity.badRequest().build();

        } catch (GetTurnoverServiceException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }
}
