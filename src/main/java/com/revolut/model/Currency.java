package com.revolut.model;

import java.util.ArrayList;
import java.util.List;

/**
 * List of Currency
 * @author Kanat K.B.
 */

public class Currency {
    private List<String> currCode = new ArrayList<>();

    public List<String> getCurrCode() {
        currCode.add("USD");
        currCode.add("EURO");
        currCode.add("RUB");
        currCode.add("POUND");
        return currCode;
    }
}
