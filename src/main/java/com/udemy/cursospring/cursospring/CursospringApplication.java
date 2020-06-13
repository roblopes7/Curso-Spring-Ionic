package com.udemy.cursospring.cursospring;

import com.udemy.cursospring.cursospring.model.*;
import com.udemy.cursospring.cursospring.model.enums.EstadoPagamento;
import com.udemy.cursospring.cursospring.model.enums.TipoCliente;
import com.udemy.cursospring.cursospring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
