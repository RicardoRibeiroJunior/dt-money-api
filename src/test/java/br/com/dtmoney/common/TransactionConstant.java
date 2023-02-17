package br.com.dtmoney.common;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.dtmoney.domain.Transaction;
import br.com.dtmoney.domain.Type;

public class TransactionConstant {

	public static final Transaction TRANSACTION = new Transaction("description", Type.income, new BigDecimal(10),
			"category", LocalDateTime.now());
	public static final Transaction INVALID_TRANSACTION = new Transaction("description", Type.income,
			new BigDecimal(10), "category", LocalDateTime.now());
	public static final Long ID_TRANSACTION = 1L;
	public static final Long ID_INVALID_TRANSACTION = 0L;
}
