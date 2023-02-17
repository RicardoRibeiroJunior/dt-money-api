package br.com.dtmoney.service;

import static br.com.dtmoney.common.TransactionConstant.ID_INVALID_TRANSACTION;
import static br.com.dtmoney.common.TransactionConstant.ID_TRANSACTION;
import static br.com.dtmoney.common.TransactionConstant.INVALID_TRANSACTION;
import static br.com.dtmoney.common.TransactionConstant.TRANSACTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.dtmoney.domain.Transaction;
import br.com.dtmoney.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

	@InjectMocks
	private TransactionService transactionService;

	@Mock
	private TransactionRepository transactionRepository;

	@Test
	public void creatTransaction_WithValidData_ReturnsTransaction() {

		when(transactionRepository.save(TRANSACTION)).thenReturn(TRANSACTION);
		Transaction sut = transactionService.create(TRANSACTION);
		assertThat(sut).isEqualTo(TRANSACTION);

	}

	@Test
	public void createTransaction_WithInvalidData_ThrowsException() {
		when(transactionRepository.save(INVALID_TRANSACTION)).thenThrow(RuntimeException.class);
		assertThatThrownBy(() -> transactionService.create(INVALID_TRANSACTION)).isInstanceOf(RuntimeException.class);
	}
	
	@Test
	public void getTransaction_ByExistingDescription_ReturnsTransaction() {
		
		List<Transaction> transactions = new ArrayList<>() {
			{
				add(TRANSACTION);
			}
		};

		when(transactionRepository.findByDescription(TRANSACTION.getDescription())).thenReturn(transactions);

		List<Transaction> sut = transactionService.getByDescription(TRANSACTION.getDescription());

		assertThat(sut).isNotEmpty();
		assertThat(sut.get(0)).isEqualTo(TRANSACTION);

	}
	
	@Test
	public void getTransaction_ByUnexistingDescription_ReturnsEmpty() {

		final String description = "Unexisting name";

		when(transactionRepository.findByDescription(description)).thenReturn(Collections.emptyList());

		List<Transaction> sut = transactionService.getByDescription(description);

		assertThat(sut).isEmpty();

	}
	
	@Test
	public void listTransactions_ReturnsAllTransactions() {

		List<Transaction> transactions = new ArrayList<>() {
			{
				add(TRANSACTION);
			}
		};

		when(transactionRepository.findAll()).thenReturn(transactions);

		List<Transaction> sut = transactionService.list();

		assertThat(sut).isNotEmpty();
		assertThat(sut).hasSize(1);
		assertThat(sut.get(0)).isEqualTo(TRANSACTION);

	}
	
	@Test
	public void listTransactions_ReturnNoTransactions() {
		
		List<Transaction> transactions = new ArrayList<>() {
			{
				add(INVALID_TRANSACTION);
			}
		};


		when(transactionRepository.findAll()).thenReturn(Collections.emptyList());
		List<Transaction> sut = transactionService.list();
		assertThat(sut).isEmpty();
	}
	
	@Test
	public void getTransaction_ByExistingId_ReturnsTransaction() {

		when(transactionRepository.findById(ID_TRANSACTION)).thenReturn(Optional.of(TRANSACTION));

		Optional<Transaction> sut = transactionService.getById(ID_TRANSACTION);

		assertThat(sut).isNotEmpty();
		assertThat(sut.get()).isEqualTo(TRANSACTION);

	}

	@Test
	public void getPlanet_ByUnexistingId_ReturnsEmpty() {

		when(transactionRepository.findById(ID_INVALID_TRANSACTION)).thenReturn(Optional.empty());

		Optional<Transaction> sut = transactionService.getById(ID_INVALID_TRANSACTION);

		assertThat(sut).isEmpty();

	}
	
	@Test
	public void deleteTransaction_WithExistingId_doesNotThrowAnyException() {
		assertThatCode(() -> transactionService.remove(1L)).doesNotThrowAnyException();
	}
	
	@Test
	public void removeTransaction_WithUnexistingId_ThrowException() {
		doThrow(new RuntimeException()).when(transactionRepository).deleteById(99L);
		
		assertThatThrownBy(() -> transactionService.remove(99L)).isInstanceOf(RuntimeException.class);
	}

}
