package todoList.backend.data.dto;

import lombok.Data;

@Data
public class SignUpDto {

    private String name;
    private String userID;
    private String email;
    private String password;
}
