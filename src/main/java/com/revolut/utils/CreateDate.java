package com.revolut.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Date of transaction
 * @author Kanat K.B.
 */
public class CreateDate {
    public static Date getDate() {
        return java.sql.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

}
