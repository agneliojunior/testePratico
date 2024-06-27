package TestePratico;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
// Criação da Classe Main
public class Main {
    public static void main(String[] args) {
        ArrayList <Funcionario> Funcionarios = new ArrayList<>();
        // Inserir todos os funcionários
        Funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        Funcionarios.add(new Funcionario("Joao", LocalDate.of(1990, 5 ,12), new BigDecimal("2284.38"), "Operador" ));
        Funcionarios.add(new Funcionario("Caio", LocalDate.of(1961,5,2), new BigDecimal("9836.14"),"Coordenador"));
        Funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"),"Diretor"));
        Funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"),"Recepcionista"));
        Funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999,11,19), new BigDecimal("1582.72"),"Operador"));
        Funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993,3,31), new BigDecimal("4071.84"),"Contador"));
        Funcionarios.add(new Funcionario("Laura", LocalDate.of(1994,7,8), new BigDecimal("3017.45"),"Gerente"));
        Funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003,5,24), new BigDecimal("1606.85"),"Eletricista"));
        Funcionarios.add(new Funcionario("Helena", LocalDate.of(1996,9,2), new BigDecimal("2799.93"),"Gerente"));

        // Remoção do funcionário "João"
        String nomeParaRemocao = "Joao";
        Funcionario funcionarioParaRemocao = null;

        for (Funcionario funcionario : Funcionarios){
            if (funcionario.getNome().equalsIgnoreCase(nomeParaRemocao)){
                funcionarioParaRemocao = funcionario;
                break;
            }
        }

        if (funcionarioParaRemocao != null){
            Funcionarios.remove(funcionarioParaRemocao);
        }
        //Impressão do funcionários (A impressão foi realizada sem as formatações de data e salários que foi exigida no teste)
        for (Funcionario funcionario : Funcionarios){
            System.out.println(funcionario);
        }
        // Atualização do salário dos funcionários
        for (Funcionario funcionario : Funcionarios){
            BigDecimal aumento = new BigDecimal("1.1");

            BigDecimal salarioAtualizado = aumento.multiply(funcionario.getSalario());
            funcionario.setSalario(salarioAtualizado);
        }
        //Agrupamento dos funcionário utilizando MAP
        Map<String, List<Funcionario>> funcionariosOrdenadosFuncao = new HashMap<>();
        for (Funcionario funcionario : Funcionarios){
            String funcao = funcionario.getFuncao();
            if (!funcionariosOrdenadosFuncao.containsKey(funcao)) {
                funcionariosOrdenadosFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosOrdenadosFuncao.get(funcao).add(funcionario);
        }
        // Impressão da lista de funcionários agrupados por função
        funcionariosOrdenadosFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("\nFunção: " + funcao);
            for (Funcionario funcionario : listaFuncionarios) {
                System.out.println(funcionario);
            }
        });
        // Impressão dos funcionários que fazem aniversário em outubro e dezembro
        for (Funcionario funcionario : Funcionarios) {
            Month mesNascimento = funcionario.getDataNascimento().getMonth();
            if (mesNascimento == Month.OCTOBER || mesNascimento == Month.DECEMBER) {
                System.out.println(funcionario);
            }
        }
        // Impressão do funcionário mais velho
        Funcionario funcionarioMaisVelho = null;
        int maxIdade = 0;
        LocalDate hoje = LocalDate.now();
        for (Funcionario funcionario : Funcionarios) {
            int idade = Period.between(funcionario.getDataNascimento(), hoje).getYears();
            if (idade > maxIdade) {
                maxIdade = idade;
                funcionarioMaisVelho = funcionario;
            }
        }

        System.out.println("\nNome: " + funcionarioMaisVelho.getNome() + ", Idade: " + maxIdade + " anos\n");

        // Impressão dos funcionários em ordem alfabética
        Collections.sort(Funcionarios, Comparator.comparing(Funcionario::getNome));
        for (Funcionario funcionario : Funcionarios) {
            System.out.println(funcionario);
        }

        // Impressão da folha salarial
        BigDecimal folhaSalarial = BigDecimal.ZERO;
        for (Funcionario funcionario : Funcionarios) {
            folhaSalarial = folhaSalarial.add(funcionario.getSalario());
        }

        System.out.println("\nA folha salarial da empresa é: " + folhaSalarial.toString()+"\n");

        // Impressão do cálculo de proporção dos salários dos funcionários em relação ao salário mínimo
        for (Funcionario funcionario : Funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(BigDecimal.valueOf(1212.00), 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
        }

    }
}