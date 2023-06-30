package org.firstPF.userService;


import org.firstPF.userModel.User;
import org.firstPF.userServices.UserService;
import org.firstPF.userServices.UserServiceImpl;
import org.junit.jupiter.api.*;


public class UserServiceTest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
    private UserService userService;

    @BeforeEach
    void initialize(){
        userService = new UserServiceImpl();
        firstName = "Matyáš";
        lastName = "Bureš";
        email = "mat.bures@gmail.com";
        password = "123PF";
        repeatPassword = "123PF";
    }

    @DisplayName("User object created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {

        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        //Assert
        Assertions.assertNotNull(user, "The createUser() should not returned null");
        Assertions.assertEquals(firstName, user.getFirstName(), "The first name is incorrect");
        Assertions.assertEquals(password, user.getPassword(),"The password is incorrect");
        Assertions.assertEquals(repeatPassword, user.getRepeatPassword(),"The repeated password is incorrent");
    }

    @DisplayName("Empty first name causes correct exception")
    @Test
    void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {

        // Act
        String firstName = "";
        String expectedMessage = "User first name is empty";

        // Assert
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            userService.createUser(firstName,lastName,email,password,repeatPassword);
        }, "Empty first name should have caused an Illegal Argument Exception");

        Assertions.assertEquals(expectedMessage, thrown.getMessage(), "Exception error message is not correct");

    }
}
