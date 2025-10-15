package galmart.unitPratice;

import java.util.Optional;


public interface PaymentRepository {

    void save(Payment payment);
    void updateStatus(String paymentId, String status);
    Optional<Payment> findById(String id);
}
