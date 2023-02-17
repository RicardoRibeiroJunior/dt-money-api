package br.com.dtmoney.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.dtmoney.domain.Transaction;
import br.com.dtmoney.repository.TransactionRepository;

@Service
public class TransactionService {

	private TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	public Transaction create(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public Optional<Transaction> getById(Long id) {
		return transactionRepository.findById(id);
	}

	public List<Transaction> getByDescription(String description) {
		return transactionRepository.findByDescription(description);
	}

	public List<Transaction> list() {
		return transactionRepository.findAll();
	}

	public void remove(Long id) {
		transactionRepository.deleteById(id);
	}

}
