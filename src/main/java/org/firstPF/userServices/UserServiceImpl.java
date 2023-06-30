package org.firstPF.userServices;

import org.firstPF.userModel.User;

public class UserServiceImpl implements UserService {
    @Override
    public User createUser(String firstName,
                           String lastName,
                           String email,
                           String password,
                           String repeatPassword) {

        if(firstName == null  || firstName.trim().length()== 0) {
            throw new IllegalArgumentException("User first name is empty");
        }

        return new User(firstName,lastName,email,password,repeatPassword);
    }
}
