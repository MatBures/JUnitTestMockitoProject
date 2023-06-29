package org.firstPF.userServices;

import org.firstPF.userModel.User;

public interface UserService {
    User createUser(String firstName,
                    String lastName,
                    String email,
                    String password,
                    String repeatPassword);
}
