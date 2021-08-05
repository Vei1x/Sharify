package com.sharify.webuser;

//import com.sharify.registration.token.ConfirmationToken;
//import com.sharify.registration.token.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class WebUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final WebUserRepository webUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final ConfirmationTokenService confirmationTokenService;


    public WebUserService(WebUserRepository webUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder
//                          ConfirmationTokenService confirmationTokenService
    ) {
        this.webUserRepository = webUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.confirmationTokenService = confirmationTokenService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return webUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    //Actaully looks for email not username
    public String signUpUser(WebUser webUser) {
        boolean userExists = webUserRepository
                .findByEmail(webUser.getEmail())
                .isPresent();
        if (userExists) {
            throw new IllegalStateException("Email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(webUser.getPassword());
        webUser.setPassword(encodedPassword);

        webUserRepository.save(webUser);
//        String token = UUID.randomUUID().toString();
//        // : SEND confirmation token
//        ConfirmationToken confirmationToken = new ConfirmationToken(
//                token,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(15),
//                webUser
//        );
//        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return "It worked";
        //TODO: SEND EMAIL
    }
}
