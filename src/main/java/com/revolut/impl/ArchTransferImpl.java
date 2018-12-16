package com.revolut.impl;

import com.revolut.adapter.ListArchTransfer;
import com.revolut.api.ArchTransferService;
import com.revolut.exception.RevolutException;
import com.revolut.model.Transfer;
import com.revolut.utils.BodyResponse;
import com.revolut.utils.CreateDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ArchTransferService
 * @author Kanat K.B.
 */
@Component
public class ArchTransferImpl implements ArchTransferService {
    @Autowired BodyResponse bodyResponse;

    @Override
    public BodyResponse add(Transfer transfer) throws RevolutException {
        transfer.setId(ListArchTransfer.getIndex());
        transfer.setCreateDate(CreateDate.getDate());
        ListArchTransfer.addArchTransfer(transfer);
        return bodyResponse.set("SUCCESS","Add archive transfer");
    }

    @Override
    public List<Transfer> get(String fromAccCode, String toAccCode) throws RevolutException {
        List<Transfer> list = new ArrayList<Transfer>();
        ListArchTransfer.getArchTransfers().forEach(acc -> {
            if (acc.getFromAccCode().equals(fromAccCode) || acc.getToAccCode().equals(toAccCode)) {
                list.add(acc);
            }
        });
        return list;
    }

    @Override
    public List<Transfer> getAll() throws RevolutException {
        return ListArchTransfer.getArchTransfers();
    }
}
