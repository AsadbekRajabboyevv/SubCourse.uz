package uz.asadbek.AdminPanel.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import uz.asadbek.AdminPanel.models.Employee;

@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;

        // Validate name
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.employee.name");

        // Validate surname
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty.employee.surname");

        // Validate phoneNumber
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "NotEmpty.employee.phoneNumber");
        if (!employee.getPhoneNumber().matches("\\+998\\d{9}")) {
            errors.rejectValue("phoneNumber", "Pattern.employee.phoneNumber");
        }

        // Validate speciality
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "speciality", "NotEmpty.employee.speciality");

        // Validate date of birth
        if (employee.getDateOfBirth() == null) {
            errors.rejectValue("dateOfBirth", "NotNull.employee.dateOfBirth");
        }
    }
}
