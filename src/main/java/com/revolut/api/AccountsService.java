package com.revolut.api;

import com.revolut.exception.RevolutException;
import com.revolut.model.Accounts;
import com.revolut.utils.BodyResponse;

import java.util.List;
import java.util.Map;

/**
 * Write of account
 * @author Kanat K.B.
 */
public interface AccountsService {
    public BodyResponse add(Accounts accounts) throws RevolutException;

    public BodyResponse update(Accounts accounts) throws RevolutException;

    public BodyResponse delete(Accounts accounts) throws RevolutException;

    public List<Accounts> get(String clientName, String accCode) throws RevolutException;

    public List<Accounts> getAll() throws RevolutException;

    public Map<String,String> generate() throws RevolutException;
}
