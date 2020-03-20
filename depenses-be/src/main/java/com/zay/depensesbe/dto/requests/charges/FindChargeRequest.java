package com.zay.depensesbe.dto.requests.charges;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FindChargeRequest {

    private Long userId;
    private Date startDate;
    private Date endDate;
}
