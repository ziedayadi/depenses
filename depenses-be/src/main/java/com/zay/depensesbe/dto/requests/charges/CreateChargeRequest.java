package com.zay.depensesbe.dto.requests.charges;

import com.zay.depensesbe.data.period.PeriodTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
public class CreateChargeRequest {
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
