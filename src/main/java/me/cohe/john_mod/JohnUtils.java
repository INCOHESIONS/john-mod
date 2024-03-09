package me.cohe.john_mod;

import java.util.function.Predicate;

public final class JohnUtils {
    public static <T> boolean all(Iterable<T> iterable, Predicate<T> predicate) {
        for (T item : iterable) {
            if (!predicate.test(item)) {
                return false;
            }
        }

        return true;
    }
}
