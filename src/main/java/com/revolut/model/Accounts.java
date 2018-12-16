package com.revolut.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * Entity of Accounts
 * @author Kanat K.B.
 */
@Getter
@Setter
public class Accounts {
    private Integer id;
    private String clientName;
    private String accCode;
    private String currCode;
    private Double summ;
    private String status;
    private Date changeDate;
    private Date createDate;
}
