package com.zay.depensesbe.services.users;


import com.zay.depensesbe.data.User;
import com.zay.depensesbe.repositories.UsersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {

    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String WRONG_PASSWORD = "WRONG_PASSWORD";
    private static final String SUCCESS = "SUCCESS";
    private static final String RESULT = "result";
    private static final String USER = "user";
    @Autowired
    private UsersJpaRepository usersRepository;

    public Collection<User> findAll() {
        return this.usersRepository.findAll();
    }

    public Map<String, Object> login(String login, String password) {

        Map<String, Object> result = new HashMap();
        Optional<User> user = this.usersRepository.findByLogin(login);

        if (!user.isPresent()) {
            result.put(RESULT, USER_NOT_FOUND);
            return result;
        } else {
            if (!password.equals(user.get().getPassword())) {
                result.put(RESULT, WRONG_PASSWORD);
                return result;
            } else {
                result.put(RESULT, SUCCESS);
                result.put(USER, user.get());
                return result;
            }


        }
    }
}
