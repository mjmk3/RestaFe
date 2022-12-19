package app.caferest.app.Rest.Impl;

import app.caferest.app.Rest.UserRest;
import app.caferest.app.Service.UserService;
import app.caferest.app.Utility.CafeUtils;
import app.caferest.app.Utility.Constant.CafeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    UserService userService;

    @Autowired
    public UserRestImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstant.CHECK_CREDENTIALS, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
