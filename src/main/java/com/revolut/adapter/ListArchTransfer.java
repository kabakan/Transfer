package com.revolut.adapter;

import com.revolut.model.Transfer;

import java.util.ArrayList;
import java.util.List;

/**
 * Archive for transfer
 * @author Kanat K.B.
 */
public class ListArchTransfer {
    private static List<Transfer> archTransfers = new ArrayList<Transfer>();

    private static Integer index = 0;

    public static Integer getIndex() {
        return index = index+1;
    }

    public static void addArchTransfer(Transfer transfer) {
        archTransfers.add(transfer);
    }

    public static List<Transfer> getArchTransfers() {
        return archTransfers;
    }

}
