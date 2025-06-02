package project.fmihub.backend.Service;


import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
//import com.stripe.net.APIResource;
import com.stripe.exception.AuthenticationException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.*;
import project.fmihub.backend.Repository.PaidTuitionRepository;
import project.fmihub.backend.Repository.TuitionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StripeService {

    private static final Logger logger = LoggerFactory.getLogger(StripeService.class);

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @Autowired
    private TuitionRepository tuitionRepository;

    @Autowired
    private PaidTuitionRepository paidTuitionRepository;

    @PostConstruct
    public void init(){
        Stripe.apiKey = secretKey;
        logger.info("Stripe API Key initialized");
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

        Map<String, String> metadata = new HashMap<>();

        if (paymentRequest.getTuitionNumber() != null) {
            TuitionId tuitionId = new TuitionId();
            tuitionId.setPayer(paymentRequest.getPayer());
            tuitionId.setNumber(paymentRequest.getTuitionNumber());

            Optional<Tuition> optionalTuition = tuitionRepository.findById(tuitionId);
            if (optionalTuition.isEmpty()) {
                logger.warn("Tuition not found for payer: {} and number: {}", paymentRequest.getPayer(), paymentRequest.getTuitionNumber());
                throw new RuntimeException("Tuition not found");
            }

            metadata.put("payer", paymentRequest.getPayer());
            metadata.put("tuitionNumber", paymentRequest.getTuitionNumber().toString());
            metadata.put("paymentType", "tuition");
        } else {
            if (paymentRequest.getPayer() != null) {
                metadata.put("payer", paymentRequest.getPayer());
            }
            metadata.put("paymentType", "general");
        }

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amountInCents)
                .setCurrency(paymentRequest.getCurrency().toLowerCase())
                .putAllMetadata(metadata)
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .build();


        PaymentIntent paymentIntent = PaymentIntent.create(params);
        logger.info("Created PaymentIntent with ID: {}", paymentIntent.getId());
        return paymentIntent.getClientSecret();
    }

    @Transactional
    public boolean processSuccessfulPayment(String paymentIntentId) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

        if (!"succeeded".equals(paymentIntent.getStatus())) {
            logger.warn("PaymentIntent {} not successful. Status: {}", paymentIntentId, paymentIntent.getStatus());
            return false;
        }

        Map<String, String> metadata = paymentIntent.getMetadata();
        String paymentType = metadata.getOrDefault("paymentType", "general");


        if("tuition".equals(paymentType) && metadata.containsKey("payer") && metadata.containsKey("tuitionNumber")) {
            String payer = paymentIntent.getMetadata().get("payer");
            Integer tuitionNumber = Integer.parseInt(paymentIntent.getMetadata().get("tuitionNumber"));

            logger.info("Processing tuition payment for payer: {}, tuition number: {}", payer, tuitionNumber);

            TuitionId tuitionId = new TuitionId();
            tuitionId.setPayer(payer);
            tuitionId.setNumber(tuitionNumber);
            System.out.println("Payer given: " + payer + "with tuition number: " + tuitionNumber);

            Optional<Tuition> optionalTuition = tuitionRepository.findById(tuitionId);
            if (optionalTuition.isEmpty()) {
                logger.error("Tuition not found for payer: {}, number: {}", payer, tuitionNumber);
                throw new RuntimeException("Tuition not found");
            }
            Tuition tuition = optionalTuition.get();
            double originalPrice = tuition.getPrice();

            tuition.setPrice(0.0);
            tuitionRepository.save(tuition);

            PaidTuition paidTuition = new PaidTuition();

            PaidTuitionId paidTuitionId = new PaidTuitionId();
            paidTuitionId.setPayer(payer);
            paidTuitionId.setNumber(tuitionNumber);

            paidTuition.setId(paidTuitionId);
            paidTuition.setSeries("ONL");
            paidTuition.setPaymentNumber(generatePaymentNumber());
            paidTuition.setDate(LocalDate.now());
            paidTuition.setPrice(originalPrice);
            paidTuition.setDescription(tuition.getDescription());
            paidTuition.setMessage("Paid");

            tuitionRepository.delete(tuition);
            paidTuitionRepository.save(paidTuition);

            logger.info("Tuition marked as paid and moved to PaidTuition for payer: {}, number: {}", payer, tuitionNumber);
        }
        return true;
    }

    private Integer generatePaymentNumber() {
        return (int) (System.currentTimeMillis() % 10000);
    }
}
