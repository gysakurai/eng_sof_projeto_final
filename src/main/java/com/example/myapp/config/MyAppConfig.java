package com.example.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.myapp.repository.CsvFileRepository;
import com.example.myapp.repository.CsvFileRepositoryImpl;

@Configuration
public class MyAppConfig {
    @Bean
    public CsvFileRepository csvFileRepositoryLivro() {
        return new CsvFileRepositoryImpl("livro.csv");
    }
    
    @Bean
     public CsvFileRepository csvFileRepositoryAluno() {
        return new CsvFileRepositoryImpl("aluno.csv");
    }

    @Bean
     public CsvFileRepository csvFileRepositoryEmprestimo() {
        return new CsvFileRepositoryImpl("emprestimo.csv");
    }
}