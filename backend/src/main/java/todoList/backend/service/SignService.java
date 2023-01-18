package todoList.backend.service;

import todoList.backend.data.dto.SignInResultDto;
import todoList.backend.data.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp(String id, String password, String name, String role);

    SignInResultDto signIn(String id, String password) throws RuntimeException;
}
