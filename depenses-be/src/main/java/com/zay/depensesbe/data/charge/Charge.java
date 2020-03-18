
package com.zay.depensesbe.data.charge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zay.depensesbe.data.User;
import com.zay.depensesbe.data.ref.ChargeCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CHARGE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="CHARGE_TYPE",discriminatorType = DiscriminatorType.STRING)
public class Charge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LABEL", nullable = false)
    private String label;

    @Column(name = "DESCRIPTION")
    private String  description;

    @Column(name="AMOUNT", nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "CHARGE_CATEGORY_REF_ID", nullable = false)
    private ChargeCategory category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
}
