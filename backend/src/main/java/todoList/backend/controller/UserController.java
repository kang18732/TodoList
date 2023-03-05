package todoList.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoList.backend.data.dto.*;
import todoList.backend.service.UserService;

@Api(tags = {"1. User"})
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "로그인")
    @PostMapping(value = "/sign-in")
    public ResponseDto<UserTokenDto> signIn(@RequestBody SignInDto signInDto) {
        UserTokenDto userTokenDto = userService.signIn(signInDto);
        return new ResponseDto<>(userTokenDto);
    }

    @ApiOperation(value = "회원가입")
    @PostMapping(value = "/sign-up")
    public ResponseEntity<ResponseDto<String>> signUp(@RequestBody SignUpDto signUpDto) {
        userService.signUp(signUpDto);

        ResponseDto<String> responseDto = new ResponseDto<>("회원가입이 완료되었습니다.");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
