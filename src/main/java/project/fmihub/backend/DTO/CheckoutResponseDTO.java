package project.fmihub.backend.DTO;

import project.fmihub.backend.Domain.ChargeRequest;

public class CheckoutResponseDTO {
    private int amount;
    private String stripePublicKey;
    private ChargeRequest.Currency currency;

    public CheckoutResponseDTO(int amount, String stripePublicKey, ChargeRequest.Currency currency) {
        this.amount = amount;
        this.stripePublicKey = stripePublicKey;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getStripePublicKey() {
        return stripePublicKey;
    }
    public void setStripePublicKey(String stripePublicKey) {
        this.stripePublicKey = stripePublicKey;
    }
    public ChargeRequest.Currency getCurrency() {
        return currency;
    }
    public void setCurrency(ChargeRequest.Currency currency) {
        this.currency = currency;
    }
}
