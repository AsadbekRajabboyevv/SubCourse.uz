package uz.asadbek.AdminPanel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.asadbek.AdminPanel.models.Employee;
import uz.asadbek.AdminPanel.repository.EmployeeRepo;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }
    public Employee getOneEmployee(Long id) throws Exception {
        return employeeRepo.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
    @Transactional
    public void delete(Long id){
        employeeRepo.deleteById(id);
    }

    public Employee getEmployeeByName(String findByName) {
        return employeeRepo.findByName(findByName);
    }

}
