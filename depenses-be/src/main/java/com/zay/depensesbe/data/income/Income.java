package com.zay.depensesbe.data.income;

import com.zay.depensesbe.data.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "INCOME")
@Getter
@Setter
@NoArgsConstructor
public class Income implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LABEL")
    private String label;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "EFFECT_DATE", nullable = false)
    private Date effectDate;

}
