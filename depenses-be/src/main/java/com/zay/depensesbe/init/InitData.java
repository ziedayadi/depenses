package com.zay.depensesbe.init;

import com.zay.depensesbe.data.User;
import com.zay.depensesbe.data.charge.Charge;
import com.zay.depensesbe.data.charge.PeriodicCharge;
import com.zay.depensesbe.data.period.PeriodTime;
import com.zay.depensesbe.data.ref.ChargeCategory;
import com.zay.depensesbe.repositories.charge.ChargesCategoriesJpaRepository;
import com.zay.depensesbe.repositories.UsersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;


@Component
public class InitData {

    @Autowired
    private UsersJpaRepository usersJpaRepository;
    @Autowired
    private ChargesCategoriesJpaRepository chargesCategoriesJpaRepository;

    //@Bean
    public void initDataForTests() {
        this.initUsers();
        this.initChargesCategories();
    }

    private void initUsers() {
        User z = new User("Zied",
                "AYADI",
                "zied.ayedi@gmail.com",
                "zayadi",
                "zzz");
        User k = new User("Khadija",
                "ABDERRAHIM",
                "khadija.abderrahim@gmail.com",
                "kabderrahim",
                "kkk");

        this.usersJpaRepository.save(z);
        this.usersJpaRepository.save(k);
    }

    public void initChargesCategories() {
        this.chargesCategoriesJpaRepository.save(new ChargeCategory("LOY", "Loyer"));
        this.chargesCategoriesJpaRepository.save(new ChargeCategory("TRN", "Transport"));
        this.chargesCategoriesJpaRepository.save(new ChargeCategory("CRD", "Crédit banquaire"));
        this.chargesCategoriesJpaRepository.save(new ChargeCategory("COM", "Communication"));
        this.chargesCategoriesJpaRepository.save(new ChargeCategory("AUT", "Auther"));
        this.chargesCategoriesJpaRepository.save(new ChargeCategory("FAM", "Famille"));
        this.chargesCategoriesJpaRepository.save(new ChargeCategory("WBE", "Bien être"));
    }


}
