package com.zay.depensesbe.data.charge;

import com.zay.depensesbe.data.period.PeriodTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@DiscriminatorValue("PERIODIC")
@Entity
public class PeriodicCharge extends Charge {

    @Column(name = "PERIOD")
    @Enumerated(EnumType.STRING)
    private PeriodTime period;


    @Column(name="START_DATE", nullable = true)
    private Date startDate;

    @Column(name="END_DATE", nullable = true)
    private Date endDate;

    @Column(name="ACTIVE")
    private boolean active;

}
