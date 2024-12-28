package com.example.userservice.services;

import com.example.userservice.Repo.TokenRepo;
import com.example.userservice.Repo.UserRepo;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepo tokenRepo;

    /* Constructor */
    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder,
                       TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepo = tokenRepo;
    }

    @Override
    public User signUp(String name, String email, String password) {
        /*Check if the user already exists in Repo*/
        Optional<User> userOptional = userRepo.findByEmail(email);

        if(userOptional.isPresent()){
            //Throw expection that "User already exist"
        }
        /*If the user doesn't exist, then create a user*/
        User user = new User();
        user.setEmail(email);
        user.setUserName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        /*return the user to the controller*/
        return userRepo.save(user);
    }

    @Override
    public Token login(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if(userOptional.isEmpty()){
            //Throw an error that "User is not present"
        }

        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            //Throw an error that "Password is wrong"
        };

        Token token = createToken(user);
        return tokenRepo.save(token);
    }

    @Override
    public User validate(String token) {
        Optional<Token> OptionalToken = tokenRepo.findByValue(token);

        if(!OptionalToken.isPresent()){
            //Throw an error "Token not present"
        }

        Token token1 = OptionalToken.get();
        if(token1.getDeleted() == false && token1.getExpiredAt().before(new Date())){
            return token1.getUser();
        }
        return null;
    }

    @Override
    public void logout(String token) {
        Optional<Token> OptionalToken = tokenRepo.findByValue(token);
        if(OptionalToken.isEmpty()){
            //Throw an expection
        }
        Token token1 = OptionalToken.get();
        token1.setDeleted(true);
        tokenRepo.save(token1);
    }

    public Token createToken(User user) {
        Token token = new Token();
        token.setUser(user);
        /* Using Apache common lang library */
        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        Date today = new Date();
        /* Using Calendar library to calculate the new date */
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date dayAfter30 = calendar.getTime();

        token.setExpiredAt(dayAfter30);
        token.setDeleted(false);

        return token;
    }
}
