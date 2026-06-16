package com.irctc.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserServiceUtil {
    public static String hashPassword(String plainPassword) {
        // 12 is the cost factor (work factor). Higher means more secure but slower.
        return BCrypt.withDefaults().hashToString(12, plainPassword.toCharArray());
    }

    // 2. "Decrypt" (Verify/Match) the password
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword);
        return result.verified;
    }
}
