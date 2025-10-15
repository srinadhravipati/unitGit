package galmart.unitPratice;
import java.math.BigDecimal;
import java.util.UUID;

    public class PaymentService {

        private final PaymentRepository paymentRepository;
        private final PaymentValidator paymentValidator;
        private final NotificationService notificationService;

        public PaymentService(PaymentRepository paymentRepository,
                              PaymentValidator paymentValidator,
                              NotificationService notificationService) {
            this.paymentRepository = paymentRepository;
            this.paymentValidator = paymentValidator;
            this.notificationService = notificationService;
        }

        public Payment processPayment(String userId, BigDecimal amount) {
            if (userId == null || amount == null) {
                throw new IllegalArgumentException("User ID and amount must not be null");
            }

            // Validate payment before proceeding
            if (!paymentValidator.isValidAmount(amount)) {
                throw new IllegalArgumentException("Invalid payment amount");
            }

            // Create payment object
            Payment payment = new Payment(UUID.randomUUID().toString(), userId, amount, "PENDING");

            // Save to repository
            paymentRepository.save(payment);

            // Simulate external call (like sending notification)
            notificationService.notifyUser(userId, "Your payment is being processed");

            // Mark as completed
            payment.setStatus("COMPLETED");
            paymentRepository.updateStatus(payment.getId(), "COMPLETED");

            return payment;
        }

        public Payment getPaymentById(String id) {
            return paymentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Payment not found for ID: " + id));
        }
    }

