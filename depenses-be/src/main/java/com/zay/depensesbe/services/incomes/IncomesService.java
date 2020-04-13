package com.zay.depensesbe.services.incomes;

import com.zay.depensesbe.data.User;
import com.zay.depensesbe.data.income.Income;
import com.zay.depensesbe.dto.requests.incomes.CreateNewIncome;
import com.zay.depensesbe.repositories.UsersJpaRepository;
import com.zay.depensesbe.repositories.incomes.IncomesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class IncomesService {

    @Autowired
    private IncomesRepository incomesRepository;

    @Autowired
    private UsersJpaRepository usersJpaRepository;

    public List<Income> findByUserAndEffectDateBetween(Long userId, Date fromDate, Date toDate) throws Exception {
        List<Income> incomes = this.incomesRepository.findByUserAndDateBetween(userId, fromDate, toDate);
        return incomes;
    }

    @Transactional
    public void createNewIncome(CreateNewIncome createNewIncome) {
        Income income = new Income();

        User user = this.usersJpaRepository.findById(createNewIncome.getUserId()).orElse(null);
        income.setLabel(createNewIncome.getLabel());
        income.setDescription(createNewIncome.getDescription());
        income.setEffectDate(createNewIncome.getEffectDate());
        income.setAmount(createNewIncome.getAmount());
        income.setUser(user);

        this.incomesRepository.save(income);
    }
}
