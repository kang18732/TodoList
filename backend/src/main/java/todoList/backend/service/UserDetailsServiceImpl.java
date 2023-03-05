package todoList.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import todoList.backend.common.exception.BadRequestException;
import todoList.backend.data.entity.User;
import todoList.backend.data.entity.UserDetailsImpl;
import todoList.backend.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) {
        Optional<User> user = userRepository.findById(Long.valueOf(userId));
        if(user.isEmpty()) {
            throw new BadRequestException("존재하지 않는 유저입니다.");
        }
        return new UserDetailsImpl(user.get());
    }
}