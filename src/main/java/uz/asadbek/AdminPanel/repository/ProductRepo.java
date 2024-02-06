package uz.asadbek.AdminPanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.asadbek.AdminPanel.models.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

}
