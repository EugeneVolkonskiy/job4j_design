package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int i = 1;
        boolean bool = true;
        char ch = 'A';
        long l = 97665L;
        float f = 123.4f;
        double d = 456.44;
        short s = 20000;
        byte bt = 100;
        LOG.debug("Primitives : int : {}, boolean : {}, char : {}, long : {}, float : {}, double : {}, short : {}, byte : {} ", i, bool, ch, l, f, d, s, bt);
    }
}
