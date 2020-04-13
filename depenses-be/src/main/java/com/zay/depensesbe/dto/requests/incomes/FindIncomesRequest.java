package com.zay.depensesbe.dto.requests.incomes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class FindIncomesRequest {

    @JsonProperty("user")
    private Long userId;

    @JsonProperty("from")
    private Date fromDate;

    @JsonProperty("to")
    private Date toDate;

}
