package com.bipincodes.testing;

import java.util.Map;

public class Client {
    private String addresses;
    Map<String, String> tags;
    public Client(String addresses, Map<String, String> tags){
        this.tags = tags;
        this.addresses = getAddresses();
    }

    public String getAddresses() {
        return addresses;
    }


    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public Map<String, String> getTags() {
        return tags;
    }
}
