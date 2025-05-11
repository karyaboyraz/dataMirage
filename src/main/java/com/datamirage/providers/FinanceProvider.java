package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;
import com.datamirage.util.LazyLoader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FinanceProvider {
    private final RandomService random;
    private List<String> ibanTemplates;

    public FinanceProvider(RandomService random) {
        this.random = random;
    }

    public String creditCardNumber(String prefix, int length) {
        StringBuilder number = new StringBuilder(prefix);
        while (number.length() < length - 1) {
            number.append(random.nextInt(0, 9));
        }

        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = number.charAt(i) - '0';
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        number.append(checkDigit);

        return number.toString();
    }

    public String visaCardNumber() {
        return creditCardNumber("4", 16);
    }

    public String masterCardNumber() {
        return creditCardNumber("5", 16);
    }

    public String amexCardNumber() {
        return creditCardNumber("34", 15);
    }

    public String creditCardExpiryDate() {
        LocalDate now = LocalDate.now();
        int monthsToAdd = random.nextInt(1, 48); // 1 to 48 months from now
        LocalDate expiryDate = now.plusMonths(monthsToAdd);
        return DateTimeFormatter.ofPattern("MM/yy").format(expiryDate);
    }

    public String cvv(int digits) {
        StringBuilder cvv = new StringBuilder();
        for (int i = 0; i < digits; i++) {
            cvv.append(random.nextInt(0, 9));
        }
        return cvv.toString();
    }

    /**
     * Generates a random bank account number.
     * The account number consists of 10 digits.
     *
     * @return A randomly generated bank account number
     */
    public String bankAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            accountNumber.append(random.nextInt(0, 9));
        }
        return accountNumber.toString();
    }

    /**
     * Generates a random IBAN (International Bank Account Number) based on predefined templates.
     *
     * @return A randomly generated IBAN
     */
    public String ibanBuilder() {
        ibanTemplates = LazyLoader.load("financeIbanTemplates", () -> DataLoader.getListData("finance", "ibanTemplate"));
        String format = random.randomElement(ibanTemplates);
        return random.randomize(format);
    }

    /**
     * Generates a random monetary amount within the specified range.
     *
     * @param min The minimum amount (inclusive)
     * @param max The maximum amount (inclusive)
     * @return A string representation of the amount with 2 decimal places
     */
    public String amount(double min, double max) {
        double amount = random.nextDouble(min, max);
        return String.format("%.2f", amount);
    }

    /**
     * Main method for testing the functionality of FinanceProvider.
     * This method demonstrates the usage of various financial data generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        FinanceProvider financeProvider = new FinanceProvider(new RandomService());
        System.out.println("Random Visa Card Number: " + financeProvider.visaCardNumber());
        System.out.println("Random MasterCard Number: " + financeProvider.masterCardNumber());
        System.out.println("Random Amex Card Number: " + financeProvider.amexCardNumber());
        System.out.println("Random Expiry Date: " + financeProvider.creditCardExpiryDate());
        System.out.println("Random CVV: " + financeProvider.cvv(3));
        System.out.println("Random Bank Account Number: " + financeProvider.bankAccountNumber());
        System.out.println("Random Turkish IBAN: " + financeProvider.ibanBuilder());
        System.out.println("Random Amount: " + financeProvider.amount(1000, 5000));
    }
}
