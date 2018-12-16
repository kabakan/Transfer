package com.revolut.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Entity of Transfer
 * @author Kanat K.B.
 */
@Getter
@Setter
public class Transfer {
    private Integer id;
    private String fromAccCode;
    private String toAccCode;
    private String currCode;
    private String title;
    private String status;
    private Double summ;
    private Date sendDate;
    private Date createDate;
}
