package project.fmihub.backend.DTO;

import java.time.LocalDate;

public class TuitionDTO {
    private String payer;
    private Integer number;
    private String type;
    private String description;
    private String year;
    private Double price;
    private LocalDate paymentTerm;


    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(LocalDate paymentTerm) {
        this.paymentTerm = paymentTerm;
    }
}