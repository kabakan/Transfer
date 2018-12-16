package com.revolut.api;

import com.revolut.exception.RevolutException;
import com.revolut.model.Transfer;
import com.revolut.utils.BodyResponse;

import java.util.List;

/**
* Read of archive transfer
* @author Kanat K.B.
*/
public interface ArchTransferService {
    public BodyResponse add(Transfer transfer) throws RevolutException;

    public List<Transfer> get(String fromAccCode, String toAccCode) throws RevolutException;

    public List<Transfer> getAll() throws RevolutException;
}
