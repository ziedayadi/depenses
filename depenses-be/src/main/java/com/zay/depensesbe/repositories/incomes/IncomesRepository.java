package com.zay.depensesbe.repositories.incomes;

import com.zay.depensesbe.data.income.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IncomesRepository extends JpaRepository<Income, Long> {

    @Query("select i from Income i " +
            "where i.user.id = :userId " +
            "and i.effectDate >= :fromDate" +
            " and i.effectDate <= :toDate")
    List<Income> findByUserAndDateBetween(@Param("userId") Long userId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
}
