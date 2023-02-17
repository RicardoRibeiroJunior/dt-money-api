package br.com.dtmoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class DtMoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DtMoneyApplication.class, args);
	}

}
