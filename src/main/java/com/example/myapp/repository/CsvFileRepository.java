package com.example.myapp.repository;
import com.example.myapp.model.Aluno;
import com.example.myapp.model.Emprestimo;
import com.example.myapp.model.Livro;
import com.opencsv.exceptions.CsvValidationException;

public interface CsvFileRepository {
    void save(Livro livro);
    void saveAluno(Aluno aluno);
    void newLivroEmprestimo(Livro livro) throws CsvValidationException;
    void saveEmprestimo(Emprestimo emprestimo) throws CsvValidationException;
    void saveDevolucao(Emprestimo emprestimo) throws CsvValidationException;
}
