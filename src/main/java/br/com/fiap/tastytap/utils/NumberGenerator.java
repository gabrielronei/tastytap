package br.com.fiap.tastytap.utils;

import java.util.concurrent.atomic.AtomicLong;

public class NumberGenerator {

    private static final AtomicLong number = new AtomicLong(System.currentTimeMillis()  / 1000);

    public static long getNext() {
        return number.incrementAndGet();
    }
}
