package pl.tomaszbuga.ui.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtils {
    public static final String username = System.getProperty("email");
    public static final String pass = System.getProperty("password");
}