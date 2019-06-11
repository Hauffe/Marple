package com.pucpr.alexandre.marple.entity;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private Long id;
    private String name;
    private List<Restriction> restrictions;

    public Person(String name) {
        this.name = name;
        restrictions = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }
}
