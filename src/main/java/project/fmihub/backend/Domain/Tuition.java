package project.fmihub.backend.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "tuitions")
public class Tuition {
    @EmbeddedId
    private TuitionId id;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "description", nullable = false, length = 30)
    private String description;

    @Column(name = "year", nullable = false, length = 12)
    private String year;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "payment_term", nullable = false)
    private LocalDate paymentTerm;

    public TuitionId getId() {
        return id;
    }

    public void setId(TuitionId id) {
        this.id = id;
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