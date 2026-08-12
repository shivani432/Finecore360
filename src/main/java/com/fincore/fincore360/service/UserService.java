package com.fincore.fincore360.service;

import org.springframework.stereotype.Service;

import com.fincore.fincore360.entity.User;
import com.fincore.fincore360.repository.UserRepository;

/*
 * @Service:
 * Login related business logic ठेवण्यासाठी.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /*
     * Constructor Injection:
     * UserRepository Spring automatically provide करतो.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * User registration साठी User database मध्ये save करतो.
     */
    public User registerUser(User user) {

        return userRepository.save(user);
    }

    /*
     * Email वापरून User शोधतो.
     *
     * User सापडला तर User return करतो.
     * User सापडला नाही तर null return करतो.
     */
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElse(null);
    }

    /*
     * Login करण्यासाठी email आणि password check करतो.
     */
    public User loginUser(String email, String password) {

        User user = getUserByEmail(email);

        /*
         * User अस्तित्वात आहे आणि password match झाला
         * तर User return करतो.
         */
        if (user != null &&
            user.getPassword().equals(password)) {

            return user;
        }

        /*
         * Email किंवा password चुकीचा असेल
         * तर null return करतो.
         */
        return null;
    }
}
