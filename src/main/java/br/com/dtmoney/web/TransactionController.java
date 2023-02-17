package br.com.dtmoney.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dtmoney.domain.Transaction;
import br.com.dtmoney.domain.dto.TransactionDto;
import br.com.dtmoney.domain.form.TransactionForm;
import br.com.dtmoney.service.TransactionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	
	@PostMapping
	@Transactional
	@CrossOrigin
	public ResponseEntity<TransactionDto> create(@RequestBody @Valid TransactionForm form) {

		Transaction transaction = form.converter();
		Transaction transactionCreated = transactionService.create(transaction);		
		return ResponseEntity.status(HttpStatus.CREATED).body(new TransactionDto(transactionCreated));
		
	}
	
	@GetMapping("/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<TransactionDto> getById(@PathVariable Long id){
		
		return transactionService.getById(id).map(transaction -> ResponseEntity.ok(new TransactionDto(transaction)))
				.orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/description/{description}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<List<TransactionDto>> getByDescription(@PathVariable String description){
		
		List<Transaction> transactions = transactionService.getByDescription(description);
		
		if(transactions.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(TransactionDto.converter(transactions));
		}
		
		
	}
	
	
	@GetMapping
	@Transactional
	@CrossOrigin
	public ResponseEntity<List<TransactionDto>> list(){
		List<Transaction> transactions = transactionService.list();
		return ResponseEntity.ok(TransactionDto.converter(transactions));
	}
	

	
	@DeleteMapping("/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<Void> remove(@PathVariable("id") Long id) {
		transactionService.remove(id);
		return ResponseEntity.noContent().build();
	}

}
