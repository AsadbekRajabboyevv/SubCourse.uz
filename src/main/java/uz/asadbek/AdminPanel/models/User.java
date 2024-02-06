package uz.asadbek.AdminPanel.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Column(name = "date_of_birth")
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date dateOfBirth;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	@Column(name = "roll")
	private String role;
	@Column(name = "email", unique = true)
	private String email;
	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "owner")
	private List<Product> productList;

	public User(int id, String name, String surname,
	            Date dateOfBirth, LocalDateTime createdAt,
	            LocalDateTime updatedAt, String role,
	            String email, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
		this.email = email;
		this.password = password;
	}

	public User(String name, String surname,
	            Date dateOfBirth, LocalDateTime createdAt,
	            LocalDateTime updatedAt, String role,
	            String email, String password) {
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
		this.email = email;
		this.password = password;
	}

	public User() {
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}