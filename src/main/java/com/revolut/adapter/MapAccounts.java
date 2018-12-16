package com.revolut.adapter;

import com.revolut.model.Accounts;

import java.util.Hashtable;
import java.util.Map;

/**
 * List of accounts
 * @author Kanat K.B.
 */
public class MapAccounts {
    private static Map<Integer, Accounts> accountMap = new Hashtable<>();

    private static Integer index = 0;

    public static Integer getIndex() {
        return index = index+1;
    }

    public static Map<Integer, Accounts> get() { return accountMap; }

    public static void add(Accounts accounts) { accountMap.put(accounts.getId(),accounts); }

    public static void update(Accounts accounts) {
        accountMap.replace(accounts.getId(),accounts);
    }

    public static void delete(Accounts accounts) {  accountMap.replace(accounts.getId(),accounts); }
}
