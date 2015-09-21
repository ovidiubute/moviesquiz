package com.ovi.apps.movieserver.business;

import org.springframework.stereotype.Component;

@Component
public class TimeProviderImpl implements TimeProvider {
    @Override
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
