package project.fmihub.backend.Controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.fmihub.backend.Domain.PaymentRequest;
import project.fmihub.backend.Service.StripeService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<?> createCheckoutSession(@RequestBody Map<String, Object> requestMap) throws StripeException {
        try {
            double amount = ((Number) requestMap.get("amount")).doubleValue();

            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setAmount(amount);
            paymentRequest.setCurrency("RON");

            String currentUser = "defauldUser";
            if (requestMap.containsKey("payer")) {
                currentUser = (String) requestMap.get("payer");
            }
            paymentRequest.setPayer(currentUser);

            Integer tuitionNumber = null;
            if (requestMap.containsKey("tuitionNumber")) {
                tuitionNumber = ((Number) requestMap.get("tuitionNumber")).intValue();
                paymentRequest.setTuitionNumber(tuitionNumber);
            }

            String clientSecret = stripeService.createPaymentIntent(paymentRequest);

            Map<String, String> response = new HashMap<>();
            response.put("clientSecret", clientSecret);

            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/success")
    public ResponseEntity<?> confirmPayment(@RequestParam String paymentIntentId) throws StripeException {
        try {
            boolean success = stripeService.processSuccessfulPayment(paymentIntentId);

            if (success) {
                Map<String, String> response =  new HashMap<>();
                response.put("status", "success");
                response.put("message", "Payment confirmed successfully");
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error =  new HashMap<>();
                error.put("error", "Payment could not be confirmed");
                return ResponseEntity.badRequest().body(error);
            }
        } catch (StripeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
