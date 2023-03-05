package todoList.backend.data.dto;

import lombok.Getter;

@Getter
public class UserTokenDto {

    private Long userId;
    private String token;

    public UserTokenDto(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
