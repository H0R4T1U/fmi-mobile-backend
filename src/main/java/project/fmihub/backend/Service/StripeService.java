package project.fmihub.backend.Service;


import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
//import com.stripe.net.APIResource;
import com.stripe.exception.AuthenticationException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.ChargeRequest;
import project.fmihub.backend.Domain.PaymentRequest;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;


    @PostConstruct
    public void init(){
        Stripe.apiKey = secretKey;
    }
//    public Charge charge(ChargeRequest chargeRequest)
//        throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
//        Map<String, Object> chargeParams = new HashMap<>();
//        chargeParams.put("amount", chargeRequest.getAmount());
//        chargeParams.put("currency", chargeRequest.getCurrency());
//        chargeParams.put("description", chargeRequest.getDescription());
//        chargeParams.put("source", chargeRequest.getStripeToken());
//        return Charge.create(chargeParams);
//    }

    public String createPaymentIntent(PaymentRequest paymentRequest) throws StripeException {
        long amountInCents = (long) (paymentRequest.getAmount() * 100);

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amountInCents)
                .setCurrency(paymentRequest.getCurrency().toLowerCase())
                .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL)
                .addPaymentMethodType("card")
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent.getClientSecret();
    }
}
