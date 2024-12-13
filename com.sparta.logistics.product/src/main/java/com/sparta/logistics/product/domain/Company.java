package com.sparta.logistics.product.domain;

import java.util.Objects;

public class Company {

    private final Long id;
    private final Long hubId;
    private final String name;
    private final String type;

    public Company(Long id, Long hubId, String name, String type) {
        this.id = id;
        this.hubId = hubId;
        this.name = name;
        this.type = type;
    }

    public boolean isConsumer() {
        return Objects.equals("consumer", type);
    }

    public Long getId() {
        return id;
    }

    public Long getHubId() {
        return hubId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
