package todoList.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import todoList.backend.common.config.security.JwtTokenProvider;
import todoList.backend.common.exception.BadRequestException;
import todoList.backend.data.dto.*;
import todoList.backend.data.entity.User;
import todoList.backend.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public UserTokenDto signIn(SignInDto signInDto) {
        Optional<User> user = userRepository.findByUsername(signInDto.getUsername());
        if(user.isEmpty()) {
            throw new BadRequestException("존재하지 않는 유저입니다.");
        }
        if(!passwordEncoder.matches(signInDto.getPassword(), user.get().getPassword())) {
            throw new BadRequestException("비밀번호가 틀렸습니다.");
        }

        String token = jwtTokenProvider.createToken(String.valueOf(user.get().getId()), user.get().getRoles());
        return new UserTokenDto(user.get().getId(), token);
    }

    public void signUp(SignUpDto signUpDto) {
        if(userRepository.findByUsername(signUpDto.getUsername()).isPresent()) {
            throw new BadRequestException("이미 존재하는 아이디입니다.");
        }

        User user = User.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .email(signUpDto.getEmail())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        userRepository.save(user);
    }
}
