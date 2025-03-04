package com.bango.bank;

import static com.bango.bank.util.CommonObjects.getTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bango.bank.config.TransactionCallback;
import com.bango.bank.entities.Transaction;

@SpringBootTest
class BangoBankApplicationTests {

	@Test
	void main() {
		BangoBankApplication.main(new String[] {});
	}

	@Test
	void onBeforeConvert() {
		TransactionCallback transactionCallback = new TransactionCallback();

		Transaction transaction = transactionCallback.onBeforeConvert(getTransaction(), "");

		Assertions.assertEquals("retiro", transaction.getType());
	}
}
