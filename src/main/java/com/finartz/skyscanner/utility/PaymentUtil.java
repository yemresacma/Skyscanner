package com.finartz.skyscanner.utility;

public class PaymentUtil {
    /**
     * gets payment from given card
     * @param creditCardNumber
     * @param price
     * @return
     */
        public static boolean getPayment(String creditCardNumber, int price) {
            // TODO get payment
            return true;
        }

    /**
     * returns the payment to the given card after user returned the ticket
     * @param returnAmount
     * @param creditCardInfo
     * @return
     */
        public static boolean returnPayment(int returnAmount, String creditCardInfo) {
            // TODO return payment
            return true;
        }

    /**
     * validates credit card and masks according to requirements
     * @param creditCardNumber
     * @return
     * @throws Exception
     */
        public static String validateAndMaskCardNumber(String creditCardNumber) throws Exception {
            creditCardNumber = creditCardNumber.replaceAll("[^0-9]", "");
            if (creditCardNumber.length() != 16) {
                throw new Exception("Invalid credit card number format");
            }

            StringBuilder maskedNumber = new StringBuilder(creditCardNumber);
            return maskedNumber.substring(0, 6) + "******" + creditCardNumber.substring(12);
        }

    /**
     * Calculate ticket price according to requirement that
     * ticket price is raised %10 after every %10 increase of sold Tickets / Total tickets ratio
     * @param initialPrice
     * @param soldTotalRatio
     * @return
     */
        public static int calculateTicketPrice(int initialPrice, float soldTotalRatio) {
            int numberOfRaise = (int) (soldTotalRatio * 10);

            while (numberOfRaise-- > 0) {
                initialPrice += initialPrice / 10;
            }
            return initialPrice;
        }
}
