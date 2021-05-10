package com.finartz.skyscanner;

import com.finartz.skyscanner.utility.PaymentUtil;
import org.junit.Test;

import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentUtilityTestCase {
    @Test
    public void validateAndMaskCardNumberTestCase() throws Exception {
        final String creditCard1 = new String("1234.1234.1234");
        Assertions.assertThrows(Exception.class, () ->
                PaymentUtil.validateAndMaskCardNumber(creditCard1));

        final String creditCard2 = "1234-1234-1234-1234";
        final String maskedCardNumber = "123412*****1234";
        assertThat(maskedCardNumber == PaymentUtil.validateAndMaskCardNumber(creditCard2));


        final String creditCard3 = "1234-1234-1234-1234";
        assertThat(maskedCardNumber == PaymentUtil.validateAndMaskCardNumber(creditCard3));
    }

    @Test
    public void calculateTicketPriceTestCase() {
        PaymentUtil.calculateTicketPrice(100, 0);
        assertThat(PaymentUtil.calculateTicketPrice(100, 0) == 100);
        assertThat(PaymentUtil.calculateTicketPrice(100, 0.09f) == 100);
        assertThat(PaymentUtil.calculateTicketPrice(100, 0.1f) == 110);
        assertThat(PaymentUtil.calculateTicketPrice(100, 0.19f) == 110);
        assertThat(PaymentUtil.calculateTicketPrice(100, 0.20f) == 121);

    }
}
