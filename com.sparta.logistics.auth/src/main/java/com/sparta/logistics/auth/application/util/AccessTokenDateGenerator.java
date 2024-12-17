package com.sparta.logistics.auth.application.util;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AccessTokenDateGenerator implements DateGenerator {

    @Override
    public Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    @Override
    public Date getExpireDate(long exp) {
        long currentTime = getCurrentDate().getTime();
        return new Date(currentTime + exp);
    }
}
