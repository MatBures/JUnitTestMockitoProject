package org.firstPF.userService;

import org.firstPF.dataOfUsers.UsersRepository;
import org.firstPF.userModel.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User createUser(String firstName,
                           String lastName,
                           String email,
                           String password,
                           String repeatPassword) {

        if(firstName == null || firstName.trim().length() == 0)
            throw new IllegalArgumentException("User first name is empty");

       User user = new User(firstName,lastName,email, UUID.randomUUID().toString());

        boolean isUserCreated;

       try{
           isUserCreated = usersRepository.save(user);
       }catch(RuntimeException e) {
           throw new UserServiceException(e.getMessage());
       }

        return user;
    }
}

