import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.time.Period;


public class Principal {

    public static void main(String[] args) {
        new Principal();
    }

    ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
    DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
    public Principal() {
        Funcionario funcionario1 = new Funcionario("Maria", LocalDate.parse("18/10/2000", formatoBrasileiro), new BigDecimal("2009.44"), "Operador");
        funcionarios.add(funcionario1);

        Funcionario funcionario2 = new Funcionario("João", LocalDate.parse("12/05/1990", formatoBrasileiro), new BigDecimal("2284.38"), "Operador");
        funcionarios.add(funcionario2);

        Funcionario funcionario3 = new Funcionario("Caio", LocalDate.parse("02/05/1961", formatoBrasileiro), new BigDecimal("9836.14"), "Coordenador");
        funcionarios.add(funcionario3);

        Funcionario funcionario4 = new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatoBrasileiro), new BigDecimal("19119.88"), "Diretor");
        funcionarios.add(funcionario4);

        Funcionario funcionario5 = new Funcionario("Alice", LocalDate.parse("05/01/1995", formatoBrasileiro), new BigDecimal("2234.69"), "Recepcionista");
        funcionarios.add(funcionario5);

        Funcionario funcionario6 = new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatoBrasileiro), new BigDecimal("1582.72"), "Operador");
        funcionarios.add(funcionario6);

        Funcionario funcionario7 = new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatoBrasileiro), new BigDecimal("4071.84"), "Contador");
        funcionarios.add(funcionario7);

        Funcionario funcionario8 = new Funcionario("Laura", LocalDate.parse("08/07/1994", formatoBrasileiro), new BigDecimal("3017.45"), "Gerente");
        funcionarios.add(funcionario8);

        Funcionario funcionario9 = new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatoBrasileiro), new BigDecimal("1606.85"), "Eletricista");
        funcionarios.add(funcionario9);

        Funcionario funcionario10 = new Funcionario("Helena", LocalDate.parse("02/09/1996", formatoBrasileiro), new BigDecimal("2799.93"), "Gerente");
        funcionarios.add(funcionario10);

        //3.2 - Remover o funcionário João
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        //3.4 - Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        for (Funcionario funcionario : funcionarios){
            BigDecimal aumento = new BigDecimal("1.10");
            BigDecimal novoSalario = funcionario.getSalario().multiply(aumento);

            funcionario.setSalario(novoSalario);
        }

        //3.3 - Imprimir a lista de funcionários
        for (Funcionario funcionario : funcionarios){
            NumberFormat formatoBrasileiroNumerico = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
            formatoBrasileiroNumerico.setMinimumFractionDigits(2);
            String salarioFormatado = formatoBrasileiroNumerico.format(funcionario.getSalario());

        System.out.println("Nome: " + funcionario.getNome() + " | Data de Nascimento: " + funcionario.getDataNascimento().format(formatoBrasileiro) + " | Salário: " + salarioFormatado + " | Função: " + funcionario.getFuncao());
        }

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        //3.6 - Funcionários por função
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Funcionários da Função: " + funcao);
            listaFuncionarios.forEach(funcionario -> System.out.println("Nome: " + funcionario.getNome()));
            System.out.println("<------------------------------------->");
        });

        //3.7 - Mês 10 e 12
        System.out.println("Funcionários que nasceram nos meses 10 e 12");
        funcionarios.stream()
                .filter(funcionario -> {
                    int mes = funcionario.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(funcionario -> {
                    System.out.println("Nome: " + funcionario.getNome() + " | Data de Nascimento: " + funcionario.getDataNascimento().format(formatoBrasileiro));
                });
        System.out.println("<------------------------------------->");

        //3.8 Maior idade
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .max(Comparator.comparing(funcionario -> Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears()))
                .orElse(null);

        if (funcionarioMaisVelho != null) {
            System.out.println("Funcionário mais velho");
            int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("Nome: " + funcionarioMaisVelho.getNome() + " | Idade: " + idade);
            System.out.println("<------------------------------------->");
        }

        //3.9 - Ordenar por ordem alfabética
        System.out.println("Funcionários ordenados por ordem alfabética");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(funcionario -> {
                    System.out.println("Nome: " + funcionario.getNome() + " | Data de Nascimento: " + funcionario.getDataNascimento().format(formatoBrasileiro) + " | Salário: " + funcionario.getSalario() + " | Função: " + funcionario.getFuncao());
                });
        System.out.println("<------------------------------------->");

        //3.10 - Total sálarios
        BigDecimal totalSalario = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        NumberFormat formatoBrasileiro = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        formatoBrasileiro.setMinimumFractionDigits(2);
        String totalSalarioFormatado = formatoBrasileiro.format(totalSalario);

        System.out.println("Total de todos os salários: " + totalSalarioFormatado);
    }

    //3.12 infelizmente não consegui fazer
}
