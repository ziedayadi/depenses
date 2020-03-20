package com.zay.depensesbe.repositories.charge;

import com.zay.depensesbe.data.charge.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ChargesJpaRepositories extends JpaRepository<Charge, Long> {


    @Query("select c from Charge c " +
            "where c.user.id  = :userId")
    Collection<Charge> findByUserId(@Param("userId")  Long userId);


    @Query("select c from OneTimeCharge c " +
            "where c.user.id  = :userId " +
            "and c.effectDate <= :endDate " +
            "and c.effectDate >= :startDate" )
    List<Charge> findOneTimeChargesByDateAndUser(Long userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query("select c from PeriodicCharge c " +
            "where c.user.id  = :userId " +
            "and c.active = 1 ")
    List<Charge>  findPeriodicChargesByDateAndUser(Long userId);
}
