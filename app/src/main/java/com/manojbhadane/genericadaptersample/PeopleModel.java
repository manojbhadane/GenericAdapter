package com.manojbhadane.genericadaptersample;

public class PeopleModel {

    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public PeopleModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PeopleModel setAddress(String address) {
        this.address = address;
        return this;
    }
}
