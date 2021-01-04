package idv.kuma.kumahr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetTurnoverController {


    private GetTurnoverService service;


    @Autowired
    public GetTurnoverController(GetTurnoverService service) {
        this.service = service;
    }


    @GetMapping("/turnover/{year}/{month}")
    public long getTurnover(@PathVariable int year, @PathVariable int month) {

        return service.calculate(year, month);


    }
}
