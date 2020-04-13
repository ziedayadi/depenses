package com.zay.depensesbe.dto.requests.incomes;

import lombok.Data;

import java.util.Date;

@Data
public class CreateNewIncome {

    private Long userId;
    private String label;
    private String description;
    private Date effectDate;
    private Double amount;
}
