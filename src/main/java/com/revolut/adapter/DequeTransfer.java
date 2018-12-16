package com.revolut.adapter;

import com.revolut.model.Transfer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Queue for transfer
 * @author Kanat K.B.
 */
public class DequeTransfer {
    private static Deque<Transfer> transfers = new ArrayDeque<Transfer>();

    private  static Integer index = 0;

    public static Integer getIndex() {
        return index = index+1;
    }

    public static void add(Transfer transfer) {
        transfers.add(transfer);
    }

    public static Deque<Transfer> get() {
        return transfers;
    }

}
