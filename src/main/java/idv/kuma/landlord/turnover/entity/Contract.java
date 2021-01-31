package idv.kuma.landlord.turnover.entity;

import java.time.LocalDate;

public class Contract {
    private long rent;
    private LocalDate startDate;
    private LocalDate endDate;


    public Contract(long rent, LocalDate startDate, LocalDate endDate) {

        this.rent = rent;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    public LocalDate getStartDate() {
        return startDate;
    }


    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public long getRent() {
        return rent;
    }


    public void setRent(long rent) {
        this.rent = rent;
    }
}
