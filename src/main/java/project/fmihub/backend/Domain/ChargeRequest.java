package project.fmihub.backend.Domain;

import lombok.Data;

@Data
public class ChargeRequest {

    public enum Currency {
        EUR, RON;
    }
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}
