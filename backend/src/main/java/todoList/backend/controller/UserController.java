package todoList.backend.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoList.backend.data.dto.SignInDto;
import todoList.backend.data.dto.SignInResultDto;
import todoList.backend.data.dto.SignUpDto;
import todoList.backend.data.dto.SignUpResultDto;
import todoList.backend.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping(value = "/sign-in")
//    public SignInResultDto signIn(
//            @ApiParam(value = "ID", required = true) @RequestParam String id,
//            @ApiParam(value = "Password", required = true) @RequestParam String password)
//        throws RuntimeException {
//        SignInResultDto signInResultDto = userService.signIn(id, password);
//
//        return signInResultDto;
//    }
    @PostMapping(value = "/sign-in")
    public Boolean signIn(@RequestBody SignInDto signInDto) {
        SignInResultDto signInResultDto = userService.signIn(signInDto.getUserID(), signInDto.getPassword());

        return signInResultDto.isSuccess();
    }

//    @PostMapping(value = "/sign-up")
//    public SignUpResultDto signUp(
//            @ApiParam(value = "ID", required = true) @RequestParam String id,
//            @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
//            @ApiParam(value = "이름", required = true) @RequestParam String name,
//            @ApiParam(value = "이메일", required = true) @RequestParam String email,
//            @ApiParam(value = "권한", required = true) @RequestParam String role) {
//        SignUpResultDto signUpResultDto = userService.signUp(id, password, name, email, role);
//
//        return signUpResultDto;
//    }

    @PostMapping(value = "/sign-up")
    public Boolean signUp(@RequestBody SignUpDto signUpDto) {
        SignUpResultDto signUpResultDto = userService.signUp(
                signUpDto.getUserID(),
                signUpDto.getPassword(),
                signUpDto.getName(),
                signUpDto.getEmail(),
                "user"
        );

        return signUpResultDto.isSuccess();
    }

    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
