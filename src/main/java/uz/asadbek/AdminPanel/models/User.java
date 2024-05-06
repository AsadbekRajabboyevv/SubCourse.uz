package uz.asadbek.AdminPanel.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	@Column(name = "roll")
	private String role;
	@Column(name = "username", unique = true)
	private String username;
	@Column(name = "password")
	private String password;


	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private Employee employee;


}