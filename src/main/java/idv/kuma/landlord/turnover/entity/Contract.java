package idv.kuma.landlord.turnover.entity;

import java.time.LocalDate;

public class Contract {
    private long rent;


    public LocalDate getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    private LocalDate endDate;


    public Contract(long rent, LocalDate endDate) {

        this.rent = rent;
        this.endDate = endDate;
    }


    public long getRent() {
        return rent;
    }


    public void setRent(long rent) {
        this.rent = rent;
    }
}
