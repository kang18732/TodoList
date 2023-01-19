package todoList.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import todoList.backend.common.CommonResponse;
import todoList.backend.config.security.JwtTokenProvider;
import todoList.backend.data.dto.SignInResultDto;
import todoList.backend.data.dto.SignUpResultDto;
import todoList.backend.data.entity.User;
import todoList.backend.repository.UserRepository;

import java.util.Collections;

@Service
public class SignService {

    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public SignUpResultDto signUp(String id, String password, String name, String role) {
        User user;
        if(role.equalsIgnoreCase("admin")) {
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        } else {
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignInResultDto();

        if(!savedUser.getName().isEmpty()) {
            setSuccessResult(signUpResultDto);
        } else {
            setFailResult(signUpResultDto);
        }

        return signUpResultDto;
    }

    public SignInResultDto signIn(String id, String password) throws RuntimeException {
        User user = userRepository.getByUid(id);

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }

        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles()))
                .build();

        setSuccessResult(signInResultDto);

        return signInResultDto;
    }

    private void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
