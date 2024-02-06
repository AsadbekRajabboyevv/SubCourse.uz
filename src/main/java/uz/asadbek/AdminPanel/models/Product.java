package uz.asadbek.AdminPanel.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String productName;
	@Column(name = "count")
	private int productCount;
	@Column(name = "date_recieved")
	private LocalDateTime dateReceived;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User owner;

	public Product(String productName, int productCount, LocalDateTime dateReceived, User owner) {
		this.productName = productName;
		this.productCount = productCount;
		this.dateReceived = dateReceived;
		this.owner = owner;
	}

	public Product() {
	}

	public Product(int id, String productName, int productCount, LocalDateTime dateReceived, User owner) {
		this.id = id;
		this.productName = productName;
		this.productCount = productCount;
		this.dateReceived = dateReceived;
		this.owner = owner;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public LocalDateTime getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(LocalDateTime dateReceived) {
		this.dateReceived = dateReceived;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
