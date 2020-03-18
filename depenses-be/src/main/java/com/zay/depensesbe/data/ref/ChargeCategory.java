package com.zay.depensesbe.data.ref;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "REF_CHARGE_CATEGORY")
public class ChargeCategory implements Serializable {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODE", length = 3)
    private String code;

    @Column(name="LABEL")
    private String label;

    public ChargeCategory(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
