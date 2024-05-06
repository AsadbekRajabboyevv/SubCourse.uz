package uz.asadbek.AdminPanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.asadbek.AdminPanel.models.Employee;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByName(String findByName);
}
