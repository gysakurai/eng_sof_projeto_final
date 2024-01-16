package com.example.myapp.controller;

import com.example.myapp.model.Aluno;
import com.example.myapp.model.Emprestimo;
import com.example.myapp.model.Item;
import com.example.myapp.model.Livro;
import com.example.myapp.repository.*;
import com.opencsv.exceptions.CsvValidationException;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//import java.util.List;

@Controller
public class TaskController {
    //@Autowired
    private final CsvFileRepository csvFileRepositoryLivro;
    private final CsvFileRepository csvFileRepositoryAluno;
    private final CsvFileRepository csvFileRepositoryEmprestimo;

    @Autowired // add here
    public TaskController(
            @Qualifier("csvFileRepositoryLivro") CsvFileRepository csvFileRepositoryLivro,
            @Qualifier("csvFileRepositoryAluno") CsvFileRepository csvFileRepositoryAluno,
            @Qualifier("csvFileRepositoryEmprestimo") CsvFileRepository csvFileRepositoryEmprestimo) {
        this.csvFileRepositoryLivro = csvFileRepositoryLivro;
        this.csvFileRepositoryAluno = csvFileRepositoryAluno;
        this.csvFileRepositoryEmprestimo = csvFileRepositoryEmprestimo;
    }

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST})
    public String getMenu() {
        return "menu.html";
    }

    @RequestMapping(value = "/livro", method = { RequestMethod.GET, RequestMethod.POST})
    public String getIndexLivro() {
        return "index.html";
    }
    
    @RequestMapping(value = "/aluno", method = { RequestMethod.GET, RequestMethod.POST})
    public String getIndexAluno() {
        return "index_aluno.html";
    }
    
    @RequestMapping(value = "/emprestimo", method = { RequestMethod.GET, RequestMethod.POST})
    public String getIndexEmprestimo() {
        return "index_emprestimo.html";
    }

    @RequestMapping(value = "/devolucao", method = { RequestMethod.GET, RequestMethod.POST})
    public String getIndexDevolucao() {
        return "index_devolucao.html";
    }

    @RequestMapping(value = "/cadastrarLivro",  method = { RequestMethod.GET, RequestMethod.POST })
    public String addLivro(@RequestBody Map<String, String> requestBody) throws CsvValidationException {
        String codigo = requestBody.get("codigo");
        int codigoValue = Integer.parseInt(codigo);
        Livro livro = new Livro(codigoValue);
        System.out.println("Received titulo: " + codigo);
        csvFileRepositoryLivro.save(livro);
        csvFileRepositoryEmprestimo.newLivroEmprestimo(livro);
        return "redirect:/";
    }

    @RequestMapping(value = "/cadastrarAluno",  method = { RequestMethod.GET, RequestMethod.POST })
    public String addAluno(@RequestBody Map<String, String> requestBody) {
        String nome = requestBody.get("nome");
        String ra = requestBody.get("ra");
        Aluno aluno = new Aluno(nome, ra);
        System.out.println("Received Nome: " + nome + ra);
        csvFileRepositoryAluno.saveAluno(aluno);
        return "redirect:/";
    }

    @RequestMapping(value = "/realizarEmprestimo",  method = { RequestMethod.GET, RequestMethod.POST })
    public String addEmprestimo(@RequestBody Map<String, String> requestBody) throws CsvValidationException {
        String nome = requestBody.get("nome");
        String livro = requestBody.get("livro");
        int codigoValue = Integer.parseInt(livro);
        Item item = new Item(new Livro(codigoValue));
        Aluno aluno = new Aluno(null, nome);
        Emprestimo emprestimo = new Emprestimo(item,aluno);
        csvFileRepositoryEmprestimo.saveEmprestimo(emprestimo);
        return "redirect:/";
    }

    @RequestMapping(value = "/realizarDevolucao",  method = { RequestMethod.GET, RequestMethod.POST })
    public String addDevolucao(@RequestBody Map<String, String> requestBody) throws CsvValidationException {
        String nome = requestBody.get("nome");
        String livro = requestBody.get("livro");
        int codigoValue = Integer.parseInt(livro);
        Item item = new Item(new Livro(codigoValue));
        Aluno aluno = new Aluno(null, nome);
        Emprestimo emprestimo = new Emprestimo(item,aluno);
        csvFileRepositoryEmprestimo.saveDevolucao(emprestimo);
        return "redirect:/";
    }

    @GetMapping("/error")
    public void error(){
        System.out.println("DEU ERRO...");
    }
}