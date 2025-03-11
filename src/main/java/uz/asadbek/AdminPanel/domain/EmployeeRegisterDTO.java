package uz.asadbek.AdminPanel.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import uz.asadbek.AdminPanel.models.Coordinates;

import java.util.Date;
@Getter
@Setter
public class EmployeeRegisterDTO {

    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String phoneNumber;
    @NotNull
    private EmployeeState speciality;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NotNull
    private Date dateOfBirth;

    private Coordinates coordinates;

    @NotNull
    private Long user;
}
