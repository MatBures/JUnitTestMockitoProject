package org.firstPF.userService;

import org.firstPF.userModel.User;

public interface EmailVerificationService {
    void scheduleEmailConfirmation(User user);

}
