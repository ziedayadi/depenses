package com.zay.depensesbe.services.charges;

import com.zay.depensesbe.data.User;
import com.zay.depensesbe.data.charge.Charge;
import com.zay.depensesbe.data.ref.ChargeCategory;
import com.zay.depensesbe.dto.requests.charges.CreateChargeRequest;
import com.zay.depensesbe.mappers.ChargeMapper;
import com.zay.depensesbe.repositories.UsersJpaRepository;
import com.zay.depensesbe.repositories.charge.ChargesCategoriesJpaRepository;
import com.zay.depensesbe.repositories.charge.ChargesJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ChargeService {

    @Autowired
    private ChargesJpaRepositories chargesJpaRepositories;
    @Autowired
    private ChargesCategoriesJpaRepository categoryJpaRepository;
    @Autowired
    private UsersJpaRepository usersJpaRepository;


    public Collection<Charge> findAll(Long userId) {
        return this.chargesJpaRepositories.findByUserId(userId);
    }

    public Charge createCharge(CreateChargeRequest request) {
        Optional<ChargeCategory> category = this.categoryJpaRepository.findById(request.getCategoryId());
        Optional<User> user = this.usersJpaRepository.findById(request.getUserId());
        Charge charge = ChargeMapper.map(request, category, user);
        return this.chargesJpaRepositories.save(charge);
    }
}
