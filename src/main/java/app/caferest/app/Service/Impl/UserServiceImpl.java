package app.caferest.app.Service.Impl;

import app.caferest.app.Service.UserService;
import app.caferest.app.Utility.CafeUtils;
import app.caferest.app.Utility.Constant.CafeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j // for development purpose
public class UserServiceImpl implements UserService {
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Sign-Up Form {}", requestMap);
        if(validateSignUpMap(requestMap)) {

        } else {
            return CafeUtils.getResponseEntity(CafeConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if(requestMap.containsKey("name") && requestMap.containsKey("phone")
                && requestMap.containsKey("email") && requestMap.containsKey("password"))
        {
            return true;
        }
        return false;
    }
}
