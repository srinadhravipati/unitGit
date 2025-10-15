package galmart.unitPratice;

import java.math.BigDecimal;

    public class Payment {
        private String id;
        private String userId;
        private BigDecimal amount;
        private String status;

        public Payment(String id, String userId, BigDecimal amount, String status) {
            this.id = id;
            this.userId = userId;
            this.amount = amount;
            this.status = status;
        }

        // Getters and Setters
        public String getId() { return id; }
        public String getUserId() { return userId; }
        public BigDecimal getAmount() { return amount; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

