package com.zay.depensesbe.dto.requests.charges;

import com.zay.depensesbe.data.period.PeriodTime;
import lombok.Data;

import java.util.Date;


@Data
public class SaveChargeRequest {
    private Long chargeId;
    private Long userId;
    private Long categoryId;
    private String label;
    private String description;
    private Double amount;
    private ChargeType type;


    // IF ONE_TIME
    private Date effectDate;

    // IF PERIODIC
    private PeriodTime period;
    private Date startDate;
    private Date endDate;
    private boolean active;

}
