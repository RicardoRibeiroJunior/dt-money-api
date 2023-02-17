package br.com.dtmoney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dtmoney.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByDescription(String description);

}
