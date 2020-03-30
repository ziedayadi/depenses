package com.zay.depensesbe.dto.responses.charges;

import com.zay.depensesbe.data.period.PeriodTime;
import com.zay.depensesbe.data.ref.ChargeCategory;
import com.zay.depensesbe.dto.requests.charges.ChargeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
public class ChargeDto {

    private ChargeType type;
    private Long id;
    private String label;
    private String description;
    private ChargeCategory category;
    private Double amount;
    private Long userId;
    private Date effectDate;
    private PeriodTime period;
    private Date startDate;
    private Date endDate;
    private boolean active;
    private Date debitDate;
}
