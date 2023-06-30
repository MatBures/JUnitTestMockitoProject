package org.firstPF.userService;

import org.firstPF.dataOfUsers.UsersRepository;
import org.firstPF.userModel.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;
    EmailVerificationService emailVerificationService;

    public UserServiceImpl(UsersRepository usersRepository,
                           EmailVerificationService emailVerificationService) {
        this.usersRepository = usersRepository;
        this.emailVerificationService = emailVerificationService;
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

       if(!isUserCreated) throw new UserServiceException("Could not create user");


        try {
            emailVerificationService.scheduleEmailConfirmation(user);
        }catch (RuntimeException ex){
            throw new UserServiceException (ex.getMessage());
        }

        return user;
    }
}

