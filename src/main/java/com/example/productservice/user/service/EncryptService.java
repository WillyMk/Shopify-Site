package com.example.productservice.user.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

//In summary, this EncryptService class is responsible for securely hashing and verifying passwords using the Bcrypt algorithm.
// The saltRounds and salt are used to enhance the security of the hashing process.
// The @PostConstruct annotated method ensures that the salt is generated during the initialization of the service.
// The service can be injected into other components (such as controllers or other services) to handle password encryption
// and verification in a Spring application.
@Service
public class EncryptService {
    @Value("${encryption_round_salts}")
    private int saltRounds;
    private String salt;

    @PostConstruct
    public void postConstruct(){
        salt = BCrypt.gensalt(saltRounds);
    }

    public String encryptPassword(String password){

        return BCrypt.hashpw(password, salt);
    }

    public boolean verifyPassword(String password, String hashedPass){
        return BCrypt.checkpw(password, hashedPass);
    }

    //Would modernely use but I want to implement the above to understand the underlying implementation
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    public String encryptPassword(String password){
//        return passwordEncoder.encode(password);
//    }
//
//    public boolean verifyPassword(String password, String hash){
//        return passwordEncoder.matches(password, hash);
//    }
}
