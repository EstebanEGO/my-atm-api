package com.bango.bank;

import static com.bango.bank.util.CommonObjects.getTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bango.bank.config.TransactionCallback;
import com.bango.bank.entities.Transaction;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.ALWAYS)
class BangoBankApplicationTests {

	@Test
    void contextLoads() {
		//Is empty because is only test
    }

	@Test
	void onBeforeConvert() {
		TransactionCallback transactionCallback = new TransactionCallback();

		Transaction transaction = transactionCallback.onBeforeConvert(getTransaction(), "");

		Assertions.assertEquals("retiro", transaction.getType());
	}
}
