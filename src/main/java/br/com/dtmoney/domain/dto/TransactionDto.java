package br.com.dtmoney.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.dtmoney.domain.Transaction;
import br.com.dtmoney.domain.Type;

public class TransactionDto {

	private Long id;
	private String description;
	private Type type;
	private BigDecimal price;
	private String category;
	private LocalDateTime createdAt;

	public TransactionDto(Transaction transaction) {
		this.id = transaction.getId();
		this.description = transaction.getDescription();
		this.type = transaction.getType();
		this.price = transaction.getPrice();
		this.category = transaction.getCategory();
		this.createdAt = transaction.getCreatedAt();
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Type getType() {
		return type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public static List<TransactionDto> converter(List<Transaction> transactions) {
		return transactions.stream().map(TransactionDto::new).collect(Collectors.toList());
	}

}
