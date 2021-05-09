package com.finartz.skyscanner.utility;

public class PaymentUtil {
        public static boolean getPayment(String creditCardNumber, int price) {
            // TODO get payment
            return true;
        }

        public static boolean returnPayment(int returnAmount, String creditCardInfo) {
            // TODO return payment
            return true;
        }

        public static String validateAndMaskCardNumber(String creditCardNumber) throws Exception {
            creditCardNumber= creditCardNumber.replaceAll("[^0-9]", "");
            if (creditCardNumber.length() != 16) {
                throw new Exception("Invalid credit card number format");
            }

            StringBuilder maskedNumber = new StringBuilder(creditCardNumber);
            return maskedNumber.substring(0, 6) + "******" + creditCardNumber.substring(12);
        }

        public static int calculateTicketPrice(int initialPrice, float soldTotalRatio) {
            int numberOfRaise = (int) (soldTotalRatio * 10);

            while (numberOfRaise-- > 0) {
                initialPrice += initialPrice / 10;
            }
            return initialPrice;
        }
}
