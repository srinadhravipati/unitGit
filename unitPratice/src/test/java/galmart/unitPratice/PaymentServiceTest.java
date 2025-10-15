package galmart.unitPratice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentValidator paymentValidator;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void testProcessPayment_Success() {
        // Arrange
        String userId = "user123";
        BigDecimal amount = new BigDecimal("500.00");

        when(paymentValidator.isValidAmount(amount)).thenReturn(true);
        // repository and notification service methods return void, so no "when" needed

        // Act
        Payment result = paymentService.processPayment(userId, amount);

        // Assert
        assertNotNull(result);
        assertEquals("COMPLETED", result.getStatus());
        assertEquals(userId, result.getUserId());
        assertEquals(amount, result.getAmount());

        // Verify that dependencies were called correctly
        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(notificationService, times(1)).notifyUser(eq(userId), anyString());
        verify(paymentRepository, times(1)).updateStatus(anyString(), eq("COMPLETED"));
    }
}
