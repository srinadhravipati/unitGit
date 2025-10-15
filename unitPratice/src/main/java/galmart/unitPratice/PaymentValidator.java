package galmart.unitPratice;



import java.math.BigDecimal;

    public class PaymentValidator {
        public boolean isValidAmount(BigDecimal amount) {
            return amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(new BigDecimal("10000")) <= 0;
        }
    }


