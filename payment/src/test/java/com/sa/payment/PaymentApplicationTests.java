package com.sa.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PaymentApplicationTests {
	@Autowired
	private PaymentService service;

	@Test
	void contextLoads() {
	}
	@Test
	public void payment(){
		assertThat("true"==service.make()).isTrue();
	}
	@Test
	public void sendEmailNotification(){
		assertThat(service.sendnotification("bhbbereket5@gmail.com")).isTrue();
	}
	@Test
	public void makepayment(){
		 String id=null;
		 String methodes="Bereket";
		 double amount=2154.0;
		 String email="bhbbereket5@gmail.com";
		 String FirstName="bereket";
		 String lastName="nothing";
		 PaymentRequest request= new PaymentRequest(id,null,amount,email,FirstName,lastName);
		assertThat(null==service.makepayment(request)).isTrue();
	}

}
