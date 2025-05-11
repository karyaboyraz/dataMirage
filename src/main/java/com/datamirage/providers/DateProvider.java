package com.datamirage.providers;

import com.datamirage.util.RandomService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A provider class for generating fake date and time-related data.
 * This class provides methods to generate various date and time formats,
 * including current date, future dates, past dates, and formatted date strings.
 */
public class DateProvider {
    private final RandomService random;

    /**
     * Constructs a new DateProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public DateProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Returns today's date in ISO format (yyyy-MM-dd).
     *
     * @return Today's date as a string
     */
    public String today() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }

    /**
     * Returns tomorrow's date in ISO format (yyyy-MM-dd).
     *
     * @return Tomorrow's date as a string
     */
    public String tomorrow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return tomorrow.toString();
    }

    /**
     * Returns yesterday's date in ISO format (yyyy-MM-dd).
     *
     * @return Yesterday's date as a string
     */
    public String yesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return yesterday.toString();
    }

    /**
     * Generates a random date within the past year.
     *
     * @return A random past date as a string in ISO format
     */
    public String randomDateInPast() {
        int daysToSubtract = random.nextInt(0, 365);
        LocalDate randomDate = LocalDate.now().minusDays(daysToSubtract);
        return randomDate.toString();
    }

    /**
     * Generates a random date within the next year.
     *
     * @return A random future date as a string in ISO format
     */
    public String randomDateInFuture() {
        int daysToAdd = random.nextInt(1, 365);
        LocalDate randomDate = LocalDate.now().plusDays(daysToAdd);
        return randomDate.toString();
    }

    /**
     * Generates a random date between the specified start and end dates.
     *
     * @param start The start date (inclusive)
     * @param end The end date (inclusive)
     * @return A random date between start and end as a string in ISO format
     */
    public String randomDateBetween(LocalDate start, LocalDate end) {
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(start, end);
        long randomDays = random.nextInt(0, (int) daysBetween);
        LocalDate randomDate = start.plusDays(randomDays);
        return randomDate.toString();
    }

    /**
     * Generates a random hour of the day in 24-hour format.
     *
     * @return A random hour as a string in HH:00 format
     */
    public String randomHours() {
        int hours = random.nextInt(0, 23);
        return String.format("%02d:00", hours);
    }

    /**
     * Generates a random time with hours and minutes.
     *
     * @return A random time as a string in HH:mm format
     */
    public String RandomHoursAndMinutes() {
        int hours = random.nextInt(0, 23);
        int minutes = random.nextInt(0, 59);
        return String.format("%02d:%02d", hours, minutes);
    }

    /**
     * Generates a random date within a year from today.
     *
     * @return A random LocalDate object
     */
    public LocalDate date() {
        return LocalDate.now().plusDays(random.nextInt(-365, 365));
    }

    /**
     * Generates a random time of day.
     *
     * @return A random LocalTime object
     */
    public LocalTime time() {
        return LocalTime.of(
            random.nextInt(0, 23),
            random.nextInt(0, 59),
            random.nextInt(0, 59)
        );
    }

    /**
     * Generates a random date and time.
     *
     * @return A random LocalDateTime object
     */
    public LocalDateTime dateTime() {
        return LocalDateTime.of(date(), time());
    }

    /**
     * Generates a random date formatted according to the specified pattern.
     *
     * @param pattern The date format pattern
     * @return A formatted date string
     */
    public String dateString(String pattern) {
        return date().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Generates a random time formatted according to the specified pattern.
     *
     * @param pattern The time format pattern
     * @return A formatted time string
     */
    public String timeString(String pattern) {
        return time().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Generates a random date and time formatted according to the specified pattern.
     *
     * @param pattern The date-time format pattern
     * @return A formatted date-time string
     */
    public String dateTimeString(String pattern) {
        return dateTime().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Main method for testing the functionality of DateProvider.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        DateProvider dateProvider = new DateProvider(new RandomService());
        System.out.println("Today: " + dateProvider.today());
        System.out.println("Tomorrow: " + dateProvider.tomorrow());
        System.out.println("Yesterday: " + dateProvider.yesterday());
        System.out.println("Random Date in Past: " + dateProvider.randomDateInPast());
        System.out.println("Random Date in Future: " + dateProvider.randomDateInFuture());
        System.out.println("Random Date Between: " + dateProvider.randomDateBetween(LocalDate.of(2020, 1, 1), LocalDate.of(2023, 1, 1)));
        System.out.println("Random Hours: " + dateProvider.randomHours());
        System.out.println("Random Hours and Minutes: " + dateProvider.RandomHoursAndMinutes());
        System.out.println("Random Date: " + dateProvider.date());
        System.out.println("Random Time: " + dateProvider.time());
        System.out.println("Random DateTime: " + dateProvider.dateTime());
        System.out.println("Random Date String: " + dateProvider.dateString("yyyy-MM-dd"));
        System.out.println("Random Time String: " + dateProvider.timeString("HH:mm:ss"));
        System.out.println("Random DateTime String: " + dateProvider.dateTimeString("yyyy-MM-dd HH:mm:ss"));
        System.out.println("Random Date String: " + dateProvider.dateString("dd/MM/yyyy"));
    }
}
