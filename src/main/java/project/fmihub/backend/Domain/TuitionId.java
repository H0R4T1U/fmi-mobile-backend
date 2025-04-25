package project.fmihub.backend.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

@Embeddable
public class TuitionId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 6399192967861418727L;
    @Column(name = "payer", nullable = false, length = 64)
    private String payer;

    @Column(name = "number", nullable = false)
    private Integer number;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TuitionId entity = (TuitionId) o;
        return Objects.equals(this.number, entity.number) &&
                Objects.equals(this.payer, entity.payer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, payer);
    }

}