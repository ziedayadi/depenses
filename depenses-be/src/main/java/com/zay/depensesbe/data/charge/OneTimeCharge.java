package com.zay.depensesbe.data.charge;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@DiscriminatorValue("ONE_TIME")
@Entity
public class OneTimeCharge extends Charge{

    @Column(name = "EFFECT_DATE", nullable = true)
    private Date effectDate;
}
