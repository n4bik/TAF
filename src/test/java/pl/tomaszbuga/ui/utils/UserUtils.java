package pl.tomaszbuga.ui.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtils {
    public static final String username = System.getenv("email");
    public static final String pass = System.getenv("password");
}