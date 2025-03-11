package uz.asadbek.AdminPanel.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import uz.asadbek.AdminPanel.models.Coordinates;

import java.util.Date;
@Getter
@Setter
public class EmployeeDTO {

    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotEmpty
    @NotNull
    private String surname;
    @NotNull
    private String phoneNumber;
    private EmployeeState speciality;
    @NotNull
    private Date dateOfBirth;

    private Coordinates coordinates;

    private Long user;
}
