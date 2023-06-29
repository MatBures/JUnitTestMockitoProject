package org.firstPF.userService;


import org.firstPF.userModel.User;
import org.firstPF.userServices.UserService;
import org.firstPF.userServices.UserServiceImpl;
import org.junit.jupiter.api.Test;


public class UserServiceTest {

    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {

        //Arrange
        UserService userService = new UserServiceImpl();
        String firstName = "Matyáš";
        String lastName = "Bureš";
        String email = "mat.bures@gmail.com";
        String password = "123PF";
        String repeatPassword = "123PF";

        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        //Insert
    }
}
