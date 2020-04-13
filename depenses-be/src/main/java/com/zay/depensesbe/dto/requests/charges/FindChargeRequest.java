package com.zay.depensesbe.dto.requests.charges;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FindChargeRequest {

    private Long userId;
    private Date startDate;
    private Date endDate;
    private List<String> categories;
}
