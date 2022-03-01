/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raj.vendingmachine.ui;

import java.util.Scanner;

/**
 *
 * @author romeroalicia
 */
public class UserIOConsoleImpl implements UserIO {
    private final Scanner sc = new Scanner(System.in);
    
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String stringRead = sc.nextLine();
        return stringRead;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int intRead = Integer.parseInt(sc.nextLine());
        return intRead;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int intRead;
        do {
            System.out.println(prompt);
            intRead = Integer.parseInt(sc.nextLine());
        } while (intRead < min || intRead > max);
        return intRead;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double doubleRead = Double.parseDouble(sc.nextLine());
        return doubleRead;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double doubleRead;
        do {
            System.out.println(prompt);
            doubleRead = Double.parseDouble(sc.nextLine());
        } while (doubleRead < min || doubleRead > max);
        return doubleRead;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float floatRead = Float.parseFloat(sc.nextLine());
        return floatRead;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float floatRead;
        do {
            System.out.println(prompt);
            floatRead = Float.parseFloat(sc.nextLine());
        } while (floatRead < min || floatRead > max);
        return floatRead;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long longRead = Long.parseLong(sc.nextLine());
        return longRead;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long longRead;
        do {
            System.out.println(prompt);
            longRead = Long.parseLong(sc.nextLine());
        } while (longRead < min || longRead > max);
        return longRead;
    }   
}
