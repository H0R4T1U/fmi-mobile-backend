package project.fmihub.backend.DTO;

import java.time.LocalDate;

public class PaidTuitionDTO {
    private String payer;
    private Integer number;
    private String series;
    private Integer paymentNumber;
    private LocalDate date;
    private Double price;
    private String description;
    private String message;

    // Getters and setters
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

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(Integer paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}