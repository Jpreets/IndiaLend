/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.util;

import java.util.Random;

/**
 *
 * @author jaspreetsingh
 */
public class PasswordUtil {

    private static final String AB = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String numbers = "0123456789";
    private static final String specials = ":<=>?@_!#%&()*+,-.~";
    private static final Random rnd = new Random();

    public static String randomPassword() {
        StringBuilder sb = new StringBuilder();
        int len = rnd.nextInt(5) + 5;

        // Generate password with letters first. This part is the same as the original code.
        for (int i = 0; i <= len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }

        // Generate an index to replace with a number
        int numberIndex = rnd.nextInt(len);
        // Generate an index to replace with a special character
        int specialIndex;
        do {
            specialIndex = rnd.nextInt(len);
        } while (specialIndex == numberIndex);
        // Replace one letter with a number
        sb.setCharAt(numberIndex, numbers.charAt(rnd.nextInt(numbers.length())));
        // Replace one letter (or the number if you're unlucky) with a special character
        sb.setCharAt(specialIndex, specials. charAt(rnd.nextInt(specials.length())));
        
        return sb.toString();
    }

}
