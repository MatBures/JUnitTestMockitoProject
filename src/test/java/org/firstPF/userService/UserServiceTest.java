package org.firstPF.userService;


import org.firstPF.dataOfUsers.UsersRepository;
import org.firstPF.userModel.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private EmailVerificationService emailVerificationService;

    @Mock
    private UsersRepository usersRepository;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;

    @BeforeEach
    void initialize() {

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
        when(usersRepository.save(any(User.class))).thenReturn(true);

        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        //Assert
        Assertions.assertNotNull(user, "The createUser() should not returned null");
        Assertions.assertEquals(firstName, user.getFirstName(), "The first name is incorrect");
        Assertions.assertEquals(email, user.getEmail(), "The email is incorrect");
        Assertions.assertNotNull(user.getId(), "The id is not null");
        Mockito.verify(usersRepository, times(1)).save(any(User.class));
    }

    @DisplayName("Empty first name causes correct exception")
    @Test
    void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {

        // Act
        String firstName = "";
        String expectedMessage = "User first name is empty";

        // Assert
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        }, "Empty first name should have caused an Illegal Argument Exception");

        Assertions.assertEquals(expectedMessage, thrown.getMessage(), "Exception error message is not correct");

    }

    @DisplayName("Empty first name causes correct exception")
    @Test
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException(){

        // Arrange
        when(usersRepository.save(any(User.class))).thenThrow(RuntimeException.class);

        // Act & Assert
        Assertions.assertThrows(UserServiceException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        }, "Should have thrown UserServiceException instead");
    }

    @DisplayName("EmailNotificationException is handled")
    @Test
    void testCreateUser_whenEmailNotificationExceptionThrown_throwsUserServiceException(){

        // Arrange
        when(usersRepository.save(any(User.class))).thenReturn(true);

        doThrow(EmailVerificationServiceException.class)
                .when(emailVerificationService).scheduleEmailConfirmation(any(User.class));

        // Act & Assert
        Assertions.assertThrows(UserServiceException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        }, "Should have thrown UserServiceException instead");

        // Assert
        verify(emailVerificationService, times(1))
                .scheduleEmailConfirmation(any(User.class));
    }
}



