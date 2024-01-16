package com.example.myapp.repository;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Aluno;
import com.example.myapp.model.Emprestimo;
import com.example.myapp.model.Livro;

public class CsvFileRepositoryImpl implements CsvFileRepository {

    private String filePath;

    public CsvFileRepositoryImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(Livro livro) {
        try {
            // Check if the file exists
            Path path = Paths.get(filePath);
            boolean fileExists = Files.exists(path);

            // Create FileWriter with append mode and auto-create file if not exists
            try (Writer writer = new FileWriter(filePath, true);
                 CSVWriter csvWriter = new CSVWriter(writer)) {

                // Write headers if the file is newly created
                if (!fileExists) {
                    // Assuming the headers are stored in the first array of the data list
                    csvWriter.writeNext(new String[]{"Codigo","DataInserido"});
                }

                List<String[]> data = new ArrayList<>();
                data.add(new String[]{String.valueOf(livro.verCodigo()),livro.getdataInserido()});
                csvWriter.writeAll(data);
                System.out.println("CSV file appended successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately based on your application's requirements
        }
    }

    @Override
    public void newLivroEmprestimo(Livro livro) throws CsvValidationException {
        try {
            // Check if the file exists
            Path path = Paths.get(filePath);
            boolean fileExists = Files.exists(path);

            List<String[]> searchData = readCsvFile(filePath);
            int recordToUpdate = livro.verCodigo();
            int indexToUpdate = findRecordIndex(searchData, recordToUpdate);

            if (indexToUpdate != -1) {
            // Create FileWriter with append mode and auto-create file if not exists
                try (Writer writer = new FileWriter(filePath, true);
                    CSVWriter csvWriter = new CSVWriter(writer)) {

                    // Write headers if the file is newly created
                    if (!fileExists) {
                        // Assuming the headers are stored in the first array of the data list
                        csvWriter.writeNext(new String[]{"CodigoLivro","CodigoAluno","DataEmprestimo","DataDevolucao","Status"});
                    }

                    List<String[]> data = new ArrayList<>();
                    data.add(new String[]{String.valueOf(livro.verCodigo()),"","","","DISPONIVEL"});
                    csvWriter.writeAll(data);
                    System.out.println("CSV file appended successfully!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAluno(Aluno aluno) {
        try {
            // Check if the file exists
            Path path = Paths.get(filePath);
            boolean fileExists = Files.exists(path);

            // Create FileWriter with append mode and auto-create file if not exists
            try (Writer writer = new FileWriter(filePath, true);
                 CSVWriter csvWriter = new CSVWriter(writer)) {

                // Write headers if the file is newly created
                if (!fileExists) {
                    // Assuming the headers are stored in the first array of the data list
                    csvWriter.writeNext(new String[]{"RA","Nome","Debito"});
                }

                List<String[]> data = new ArrayList<>();
                data.add(new String[]{String.valueOf(aluno.getRA()),aluno.getNome(),"FALSE"});
                csvWriter.writeAll(data);
                System.out.println("Aluno CSV file appended successfully! - Aluno Cadastrado");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately based on your application's requirements
        }
    }

    @Override
    public void saveEmprestimo(Emprestimo emprestimo) throws CsvValidationException {
        try {
            List<String[]> data = readCsvFile(filePath);
            
            for (String[] row : data) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
            
            Aluno aluno = emprestimo.getAluno();
            Livro livro = emprestimo.getItem().getLivro();
            String dataDevolucao = emprestimo.getItem().getDataDevolucao();
            int recordToUpdate = livro.verCodigo();
            int indexToUpdate = findRecordIndex(data, recordToUpdate);

            List<String[]> dataAluno = readCsvFile("aluno.csv");
            String ra = aluno.getRA();
            int result_find_Aluno = findRecordIndex(dataAluno,ra);

            if (indexToUpdate != -1 && result_find_Aluno != -1) {
                
                try (Writer writer = new FileWriter(filePath, false);
                    CSVWriter csvWriter = new CSVWriter(writer)) {
                        List<String[]> dataNew = new ArrayList<>();
                        csvWriter.writeNext(new String[]{"CodigoLivro","CodigoAluno","DataEmprestimo","DataDevolucao","Status"});
                        for (int i = 1; i < data.size(); i++) {
                        
                            if (i == indexToUpdate){
                                dataNew.add(new String[]{String.valueOf(livro.verCodigo()), emprestimo.getAluno().getRA(), emprestimo.getDataEmprestimo(), dataDevolucao, "EMPRESTADO"});
                            } else{
                                String[] record = data.get(i);
                                dataNew.add(record);
                            }
                        
                        }
                        csvWriter.writeAll(dataNew);
                        System.out.println("Emprestimo CSV file changed successfully! - Emprestimo Realizado");
                 }
            } else {
                System.out.println("Livro not found in CSV file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDevolucao(Emprestimo emprestimo) throws CsvValidationException {
        
        try {
            List<String[]> data = readCsvFile(filePath);
            
            Aluno aluno = emprestimo.getAluno();

            Livro livro = emprestimo.getItem().getLivro();
            int recordToUpdate = livro.verCodigo();
            int indexToUpdate = findRecordIndex(data, recordToUpdate);

            List<String[]> dataAluno = readCsvFile("aluno.csv");
            String ra = aluno.getRA();
            int result_find_Aluno = findRecordIndex(dataAluno,ra);

            if (indexToUpdate != -1 && result_find_Aluno != -1) {
                
                try (Writer writer = new FileWriter(filePath, false);
                    CSVWriter csvWriter = new CSVWriter(writer)) {
                        List<String[]> dataNew = new ArrayList<>();
                        csvWriter.writeNext(new String[]{"CodigoLivro","CodigoAluno","DataEmprestimo","DataDevolucao","Status"});
                        for (int i = 1; i < data.size(); i++) {
                            if (i == indexToUpdate){
                                String dataDevolucao = data.get(indexToUpdate)[3];
                                System.out.println(dataDevolucao);

                                if (checkDebito(dataDevolucao) != -1){
                                    System.out.println("RA com debito " + aluno.getRA());
                                    setDebito(aluno);
                                }

                                dataNew.add(new String[]{String.valueOf(livro.verCodigo()),"","","","DISPONIVEL"});
                            } else{
                                String[] record = data.get(i);
                                dataNew.add(record);
                            }
                        
                        }
                        csvWriter.writeAll(dataNew);
                        System.out.println("Emprestimo CSV file changed successfully! - Devolução Realizado");
                 }
            } else {
                System.out.println("Livro not found in CSV file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> readCsvFile(String filePath) throws IOException, CsvValidationException {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        }
        return data;
    }

    private static int findRecordIndex(List<String[]> data, int recordToFind) {
        String intToString = String.valueOf(recordToFind);
        for (int i = 1; i < data.size(); i++) {
            String[] record = data.get(i);
            if (record[0].equals(intToString)) {
                return i;
            }
        }   
        return -1;
    }

    private static int findRecordIndex(List<String[]> data, String recordToFind) {
        for (int i = 1; i < data.size(); i++) {
            String[] record = data.get(i);
            if (record[0].equals(recordToFind)) {
                return i;
            }
        }   
        return -1;
    }

    private static void setDebito(Aluno aluno) throws CsvValidationException, IOException{
        try {
            List<String[]> data = readCsvFile("aluno.csv");
            
            String ra = aluno.getRA();
            int result_find = findRecordIndex(data,ra);

            if (result_find != -1) {
                
                try (Writer writer = new FileWriter("aluno.csv", false);
                    CSVWriter csvWriter = new CSVWriter(writer)) {
                        List<String[]> dataNew = new ArrayList<>();
                        csvWriter.writeNext(new String[]{"RA","Nome","Debito"});
                        for (int i = 1; i < data.size(); i++) {
                        
                            if (i == result_find){
                                String nome = data.get(i)[1];
                                dataNew.add(new String[]{ra,nome,"TRUE"});
                            } else{
                                String[] record = data.get(i);
                                dataNew.add(record);
                            }
                        
                        }
                        csvWriter.writeAll(dataNew);
                        System.out.println("Aluno CSV file changed successfully! - Debito Realizado");
                 }
            } else {
                System.out.println("Aluno not found in CSV file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int checkDebito(String dataDevolucao){
        LocalDate today = LocalDate.now();
        LocalDate dataDev = LocalDate.parse(dataDevolucao, DateTimeFormatter.ISO_LOCAL_DATE);
        
        System.out.println("Hoje: " + today);
        System.out.println("Devolucao: " + dataDev);

        if (dataDev.isBefore(today)) {
            return 0;
        }
        else{
            return -1;
        }
    }
}

