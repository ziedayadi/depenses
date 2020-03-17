package com.zay.depensesbe.init;

import com.zay.depensesbe.data.User;
import com.zay.depensesbe.repositories.UsersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InitData {

    @Autowired
    private UsersJpaRepository usersJpaRepository;

    @Bean
    public void initDataForTests(){
        this.initUsers();
    }

    private void initUsers(){
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
}
