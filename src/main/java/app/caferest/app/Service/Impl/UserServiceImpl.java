package app.caferest.app.Service.Impl;

import app.caferest.app.DAO.UserRepo;
import app.caferest.app.Domain.User;
import app.caferest.app.Service.UserService;
import app.caferest.app.Utility.CafeUtils;
import app.caferest.app.Utility.Constant.CafeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j // for development purpose
public class UserServiceImpl implements UserService {

    UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Sign-Up Form {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userRepo.findByEmail(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userRepo.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity("User has been registered in the system", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Email Already exist in the system.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstant.CHECK_CREDENTIALS, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if(requestMap.containsKey("name") && requestMap.containsKey("phone")
                && requestMap.containsKey("email") && requestMap.containsKey("password"))
        {
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setPhone(requestMap.get("phone"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus(requestMap.get("false"));
        user.setRole(requestMap.get("user"));
        return user;
    }
}
