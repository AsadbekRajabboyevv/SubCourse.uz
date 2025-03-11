package uz.asadbek.AdminPanel.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
public class UserRegisterDTO {
    private Long id;

    @NotNull
    private String role;

    @NotNull
    @Size(max = 64)
    private String username;

    @NotNull
    @Size(max = 512)
    private String password;

    @NotNull
    private Long employee;
}
