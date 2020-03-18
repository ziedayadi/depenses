package com.zay.depensesbe.repositories.charge;

import com.zay.depensesbe.data.charge.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ChargesJpaRepositories extends JpaRepository<Charge, Long> {


    @Query("select c from Charge c " +
            "where c.user.id  = :userId")
    Collection<Charge> findByUserId(@Param("userId")  Long userId);
}
