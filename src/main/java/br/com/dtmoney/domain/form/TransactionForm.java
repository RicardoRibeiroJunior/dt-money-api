package br.com.dtmoney.domain.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import br.com.dtmoney.domain.Transaction;
import br.com.dtmoney.domain.Type;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TransactionForm {
	
	private Long id;
	@NotNull @NotEmpty @Length(min=1)
	private String description;
	@NotNull
	private Type type;
	@NotNull
	private BigDecimal price;
	@NotNull @NotEmpty
	private String category;
	private LocalDateTime createdAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public Transaction converter() {
		return new Transaction(description, type, price, category, createdAt);
	}

}
