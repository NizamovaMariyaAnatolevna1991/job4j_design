package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        byte mem = 1;
        LOG.debug("mem : {}", mem);
        short size = 1;
        LOG.debug("size : {}", size);
        int length = 100500;
        LOG.debug("length : {}", length);
        long money = 900500;
        LOG.debug("money : {}", money);
        float size2 = 1.05F;
        LOG.debug("size2 : {}", size2);
        double size3 = 100500.99;
        LOG.debug("size3 : {}", size3);
        boolean exists = true;
        LOG.debug("exists : {}", exists);
        char exit = 'Y';
        LOG.debug("exit : {}", exit);
    }
}
