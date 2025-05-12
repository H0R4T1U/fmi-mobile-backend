package project.fmihub.backend.Domain;

import lombok.Data;

@Data
public class PaymentRequest {
    private double amount;
    private String currency;
}
