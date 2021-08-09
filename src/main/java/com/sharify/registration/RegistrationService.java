package com.sharify.registration;

//import com.sharify.registration.token.ConfirmationToken;
//import com.sharify.registration.token.ConfirmationTokenRepository;
//import com.sharify.registration.token.ConfirmationTokenService;
import com.sharify.webuser.WebUser;
import com.sharify.webuser.WebUserRole;
import com.sharify.webuser.WebUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
public class RegistrationService {
    private final WebUserService webUserService;
    private final EmailValidator emailValidator;
//    private final ConfirmationToken confirmationToken;
//    private final ConfirmationTokenService confirmationTokenService;
//    private final ConfirmationTokenRepository confirmationTokenRepository;

    public RegistrationService(WebUserService webUserService,
                               EmailValidator emailValidator
//                               ConfirmationTokenService confirmationTokenService,
//                               ConfirmationToken confirmationToken,
//                               ConfirmationTokenRepository confirmationTokenRepository
    ) {
        this.webUserService = webUserService;
        this.emailValidator = emailValidator;
//        this.confirmationToken = confirmationToken;
//        this.confirmationTokenService = confirmationTokenService;
//        this.confirmationTokenRepository = confirmationTokenRepository;
    }

//    @Transactional
//    public String confirmToken(String token) {
//        ConfirmationToken confirmationToken = confirmationTokenRepository.
//                getToken(token).
//                orElseThrow(() ->
//                        new IllegalStateException("token not found"));
//        if (confirmationToken.getConfirmedAt() != null) {
//            throw new IllegalStateException("email already confirmed");
//        }
//        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
//        if (expiredAt.isBefore(LocalDateTime.now())){
//            throw new IllegalStateException("token expired");
//        }
//
//        confirmationTokenService.setConfirmedAt(token);
//        webUserService.enableWebUser()
//    }

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return webUserService.signUpUser(
                new WebUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        WebUserRole.USER));
    }
}
