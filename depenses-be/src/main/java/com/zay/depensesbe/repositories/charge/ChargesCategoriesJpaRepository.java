package com.zay.depensesbe.repositories.charge;

import com.zay.depensesbe.data.ref.ChargeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargesCategoriesJpaRepository extends JpaRepository<ChargeCategory, Long> {

}
