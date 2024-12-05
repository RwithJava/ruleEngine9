package com.example.ruleEngine9.model;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class GreetingRequest {

    private String greetingString;
    private Integer length;

    public String getGreetingString() {
        return greetingString;
    }

    public void setGreetingString(String greetingString) {
        this.greetingString = greetingString;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
