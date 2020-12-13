package ro.fasttrackit.homework.curs9.budgetapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.fasttrackit.homework.curs9.budgetapplication.model.Transaction;
import ro.fasttrackit.homework.curs9.budgetapplication.model.Type;

import java.util.List;

import static ro.fasttrackit.homework.curs9.budgetapplication.model.Type.*;

@SpringBootApplication
public class Application {

	public static void main(String[] args)  {
		SpringApplication.run(Application.class, args);


	}
}
