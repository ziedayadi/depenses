package com.zay.depensesbe.repositories;

import com.zay.depensesbe.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersJpaRepository extends JpaRepository<User,Long> {


    public Optional<User> findByLogin(String login);

}
