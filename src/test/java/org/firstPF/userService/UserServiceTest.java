package org.firstPF.userService;


import org.firstPF.dataOfUsers.UsersRepository;
import org.firstPF.userModel.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UsersRepository usersRepository;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;

    @BeforeEach
    void initialize(){

        firstName = "Matyáš";
        lastName = "Bureš";
        email = "mat.bures@gmail.com";
        password = "123PF";
        repeatPassword = "123PF";
    }

    @DisplayName("User object created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {

        // Arrange
        Mockito.when(usersRepository.save(Mockito.any(User.class))).thenReturn(true);

        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        //Assert
        Assertions.assertNotNull(user, "The createUser() should not returned null");
        Assertions.assertEquals(firstName, user.getFirstName(), "The first name is incorrect");
        Assertions.assertEquals(email, user.getEmail(),"The email is incorrect");
        Assertions.assertNotNull(user.getId(), "The id is not null");
        Mockito.verify(usersRepository, Mockito.times(1)).save(Mockito.any(User.class));
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
