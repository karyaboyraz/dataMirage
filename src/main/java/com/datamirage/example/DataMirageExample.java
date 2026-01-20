package com.datamirage.example;

import com.datamirage.DataMirage;
import com.datamirage.locale.DataMirageLocale;

/**
 * Example class to demonstrate the usage of DataMirage library
 */
public class DataMirageExample {
    
    public static void main(String[] args) {
        // Create a DataMirage instance with default locale (Turkish)
        DataMirage dataMirageTR = new DataMirage();
        
        // Create a DataMirage instance with English locale
        DataMirage dataMirageEN = new DataMirage(DataMirageLocale.EN_US);
        
        // Generate and print person data in Turkish
        System.out.println("Turkish Person Example:");
        System.out.println("----------------------");
        System.out.println("Name: " + dataMirageTR.name().fullName());
        System.out.println("Address: " + dataMirageTR.address().fullAddress());
        System.out.println("Phone: " + dataMirageTR.phoneNumber().phoneNumber());
        System.out.println("Email: " + dataMirageTR.internet().email());
        System.out.println("Company: " + dataMirageTR.company().name());
        System.out.println("Industry: " + dataMirageTR.company().industry());
        System.out.println();
        
        // Generate and print person data in English
        System.out.println("English Person Example:");
        System.out.println("-----------------------");
        System.out.println("Name: " + dataMirageEN.name().fullName());
        System.out.println("Address: " + dataMirageEN.address().fullAddress());
        System.out.println("Phone: " + dataMirageEN.phoneNumber().phoneNumber());
        System.out.println("Email: " + dataMirageEN.internet().email());
        System.out.println("Company: " + dataMirageEN.company().name());
        System.out.println("Industry: " + dataMirageEN.company().industry());
        System.out.println();
        
        // Generate and print other examples
        System.out.println("Other Examples:");
        System.out.println("---------------");
        System.out.println("Color: " + dataMirageEN.color().name());
        System.out.println("Book: " + dataMirageEN.book().title() + " by " + dataMirageEN.book().author());
        System.out.println("Food: " + dataMirageEN.food().dish());
        System.out.println("Vehicle: " + dataMirageEN.vehicle().manufacturer() + " " + dataMirageEN.vehicle().model());
        System.out.println("Weather: " + dataMirageEN.weather().temperatureCelsius() + ", " + dataMirageEN.weather().getDescription());
        System.out.println("Crypto: " + dataMirageEN.crypto().walletAddress());
    }
} 