package com.supportportal.resource;

import com.supportportal.domain.UserPrincipal;
import com.supportportal.domain.Users;
import com.supportportal.exception.domain.EmailExistException;
import com.supportportal.exception.domain.ExceptionHandling;
import com.supportportal.exception.domain.UsernameExistException;
import com.supportportal.service.UserService;
import com.supportportal.utility.JWTTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

import static com.supportportal.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping(path = {"/", "/user"})
public class UserResource extends ExceptionHandling {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody Users user) throws EmailExistException, UsernameExistException { //
       authenticate(user.getUsername(), user.getPassword());
       Users loginUser = userService.findUserByUsername(user.getUsername());
       UserPrincipal userPrincipal = new UserPrincipal(loginUser);
       HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
       return new ResponseEntity<>(loginUser, jwtHeader, OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user) throws EmailExistException, UsernameExistException, MessagingException { //
        Users newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(newUser, OK);
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, tokenProvider.generateJwtToken(userPrincipal));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
