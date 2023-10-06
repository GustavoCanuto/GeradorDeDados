package residuos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GeradorDeDados {

    private static final String[] nomes = {"João", "Maria", "José", "Ana", "Pedro", "Mariana", "Carlos", "Luciana", "Fernando", "Isabela", "Rafael", "Camila"};
    private static final String[] sobrenomes = {"Silva", "Santos", "Oliveira", "Pereira", "Costa", "Carvalho", "Ribeiro", "Almeida", "Ferreira", "Gomes"};
    private static String[] dominios = {"@gmail.com", "@hotmail.com", "@hotmail.com.br", "@outlook.com", "@empresa.com"};
    private static String[] inicios = {"Rua", "Avenida", "Rodovia"};
    private static final Random random = new Random();
    private static final Set<String>  placasGeradas = new HashSet<>();
    private static final Set<String> cpfGerados = new HashSet<>();
    private static final Set<String> cnpjGerados = new HashSet<>();
    private static final Set<String> emailGerados = new HashSet<>();
    
    
  //gerando unico 
    
    public static String gerarPlacaAleatoriaUnica() {
        while (true) {
        	 String placa = gerarCpfAleatorio();
            if (!placasGeradas.contains(placa)) {
            	 placasGeradas.add(placa);
                return placa;
            }
        }
    }
    
    public static String gerarCnpjUnico() {
        while (true) {
        	 String cnpjAleatorio = gerarCnpjAleatorio();
            if (!cnpjGerados.contains(cnpjAleatorio)) {
            	cnpjGerados.add(cnpjAleatorio);
                return cnpjAleatorio;
            }
        }
    }
    
    
    public static String gerarCpfUnico() {
        while (true) {
        	 String cpfAleatorio = gerarCpfAleatorio();
            if (!cpfGerados.contains(cpfAleatorio)) {
            	cpfGerados.add(cpfAleatorio);
                return cpfAleatorio;
            }
        }
    }
    
    public static String gerarEmailUnicoFuncionario() {
        while (true) {
            String email =	gerarEmailFuncionarioAleatorio();
            if (!emailGerados.contains(email)) {
            	emailGerados.add(email);
                return email;
            }
        }
    }
    
    
    public static String gerarEmailUnicoEmpresa() {
        while (true) {
            String email = gerarEmailEmpresaAleatorio();
            if (!emailGerados.contains(email)) {
            	emailGerados.add(email);
                return email;
            }
        }
    }

    
    // geradores sem verificação
    
    private static String gerarEmailFuncionarioAleatorio() {
        String nomeSobrenome = gerarNomeAleatorio();
        String dominio = dominios[new Random().nextInt(dominios.length)];
       
        if (new Random().nextBoolean()) {
        	nomeSobrenome = nomeSobrenome.replace(" ", "_");
        } else if (new Random().nextBoolean()) {
        	nomeSobrenome = nomeSobrenome.replace(" ", ".");
        }else {
        	nomeSobrenome = nomeSobrenome.replace(" ", "");
        }
        if (new Random().nextBoolean()) {
        	nomeSobrenome += gerarNumeroAleatorio(1,2);
        }
        
        String email = nomeSobrenome + dominio;
        return email;
    }

    private static String gerarEmailEmpresaAleatorio() {
        String dominio = dominios[new Random().nextInt(dominios.length)];
        String codigoEmpresa = "empresa" + gerarNumeroAleatorio(1,2);
        String funcoes = gerarCombinacaoAleatoria();
        String email = codigoEmpresa + funcoes + dominio;
        return email;
    }
    
    public static String gerarLogradouroAleatorio() {
        String inicio = inicios[new Random().nextInt(inicios.length)];
        String nomeRua = gerarCombinacaoAleatoria();
        String logradouro = inicio + " " + nomeRua;
        return logradouro;
    }
    
    private static String gerarPlacaFormato() {
        StringBuilder placa = new StringBuilder();

        // Gere 3 letras aleatórias
        for (int i = 0; i < 3; i++) {
            char letra = (char) (random.nextInt(26) + 'A');
            placa.append(letra);
        }

        // Adicione o hífen
        placa.append('-');

        // Gere 4 dígitos aleatórios
        for (int i = 0; i < 4; i++) {
            int digito = random.nextInt(10);
            placa.append(digito);
        }

        return placa.toString();
    }
    

    
    public static String gerarCpfAleatorio() {
        Random random = new Random();

        StringBuilder cpf = new StringBuilder();
        
        // Gere os primeiros 9 dígitos do CPF
        for (int i = 0; i < 9; i++) {
            cpf.append(random.nextInt(10));
        }

        // Gere os dois dígitos de verificação (10º e 11º dígitos)
        String cpfParcial = cpf.toString();
        cpf.append(gerarDigitoVerificador(cpfParcial));
        cpf.append(gerarDigitoVerificador(cpf.toString()));

        // Formate o CPF no padrão "000.000.000-00"
        String cpfFormatado = String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9));
        
        return cpfFormatado;
    }

    public static int gerarDigitoVerificador(String cpfParcial) {
        int soma = 0;
        int peso = cpfParcial.length() + 1;

        for (int i = 0; i < cpfParcial.length(); i++) {
            int digito = Character.getNumericValue(cpfParcial.charAt(i));
            soma += digito * peso;
            peso--;
        }

        int resto = soma % 11;
        if (resto < 2) {
            return 0;
        } else {
            return 11 - resto;
        }
    }
    
    public static String gerarCnpjAleatorio() {
        Random random = new Random();

        StringBuilder cnpj = new StringBuilder();

        // Gere os 12 primeiros dígitos do CNPJ
        for (int i = 0; i < 12; i++) {
            cnpj.append(random.nextInt(10));
        }

        // Gere os três dígitos de verificação (13º, 14º e 15º dígitos)
        String cnpjParcial = cnpj.toString();
        cnpj.append(gerarDigitoVerificadorCnpj(cnpjParcial));
        cnpj.append(gerarDigitoVerificadorCnpj(cnpj.toString()));
        cnpj.append(gerarDigitoVerificadorCnpj(cnpj.toString()));

        // Formate o CNPJ manualmente (00.000.000/0000-00)
        String cnpjFormatado = String.format("%02d.%03d.%03d/%04d-%02d",
                Integer.parseInt(cnpj.substring(0, 2)),
                Integer.parseInt(cnpj.substring(2, 5)),
                Integer.parseInt(cnpj.substring(5, 8)),
                Integer.parseInt(cnpj.substring(8, 12)),
                Integer.parseInt(cnpj.substring(12, 14)));
        
        return cnpjFormatado;
    }

    public static int gerarDigitoVerificadorCnpj(String cnpjParcial) {
        int[] multiplicadoresPrimeiroDigito = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicadoresSegundoDigito = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int somaPrimeiroDigito = 0;
        int somaSegundoDigito = 0;

        for (int i = 0; i < 12; i++) {
            int digito = Character.getNumericValue(cnpjParcial.charAt(i));
            somaPrimeiroDigito += digito * multiplicadoresPrimeiroDigito[i];
            somaSegundoDigito += digito * multiplicadoresSegundoDigito[i];
        }

        int primeiroDigito = calcularDigitoVerificador(somaPrimeiroDigito);
        int segundoDigito = calcularDigitoVerificador(somaSegundoDigito);

        return (primeiroDigito * 10) + segundoDigito;
    }

    public static int calcularDigitoVerificador(int soma) {
        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }
    
    public static String gerarDddAleatorio() {
        Random random = new Random();
        // Gere um DDD aleatório (exemplo: DDD entre 11 e 99)
        int ddd = random.nextInt(89) + 11;
        return String.valueOf(ddd);
    }

    public static String gerarNumeroTelefoneAleatorio() {
        Random random = new Random();

        // Gere 7 dígitos aleatórios para o número de telefone (excluindo o primeiro dígito)
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            numero.append(random.nextInt(10));
        }

        // Formate o número de telefone no formato "0000-0000"
        return String.format("%s%s%s%s-%s%s%s%s",
                random.nextInt(10), // Primeiro dígito é entre 0 e 9
                numero.charAt(0),
                numero.charAt(1),
                numero.charAt(2),
                numero.charAt(3),
                numero.charAt(4),
                numero.charAt(5),
                numero.charAt(6));
    }
    
    
    //metodos que podem ter duplicadas
    public static String gerarNomeAleatorio() {
        Random random = new Random();
        String nome = nomes[random.nextInt(nomes.length)];
        String sobrenome = sobrenomes[random.nextInt(sobrenomes.length)];
        return nome + " " + sobrenome;
    }
    
    public static String gerarCombinacaoAleatoria() {
        // Defina os caracteres de consoantes e vogais
        String consoantes = "bcdfghjklmnpqrstvwxyz";
        String vogais = "aeiou";

        // Crie um objeto Random para gerar números aleatórios
        Random random = new Random();

        // Crie uma string para armazenar a combinação aleatória
        StringBuilder combinacao = new StringBuilder();

        // Gere a primeira consoante
        char primeiraConsoante = consoantes.charAt(random.nextInt(consoantes.length()));
        combinacao.append(primeiraConsoante);

        // Gere a segunda vogal
        char segundaVogal = vogais.charAt(random.nextInt(vogais.length()));
        combinacao.append(segundaVogal);

        // Gere as duas últimas letras aleatórias
        for (int i = 0; i < 2; i++) {
            char caractereAleatorio = (char) ('a' + random.nextInt(26));
            combinacao.append(caractereAleatorio);
        }

        // Retorne a combinação gerada
        return combinacao.toString();
    }
    
    public static String gerarDataNascimentoAleatoria() {
        Random random = new Random();

        // Gere um ano aleatório entre 1940 e 2005 (para representar uma faixa razoável de idades)
        int ano = random.nextInt(66) + 1940;

        // Gere um mês aleatório entre 1 e 12
        int mes = random.nextInt(12) + 1;

        // Gere um dia aleatório com base no mês (levando em consideração fevereiro e meses com 30 ou 31 dias)
        int dia;
        if (mes == 2) {
            // Fevereiro tem 28 ou 29 dias
            if (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) {
                dia = random.nextInt(29) + 1;
            } else {
                dia = random.nextInt(28) + 1;
            }
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            // Abril, junho, setembro e novembro têm 30 dias
            dia = random.nextInt(30) + 1;
        } else {
            // Meses com 31 dias
            dia = random.nextInt(31) + 1;
        }

        // Formate a data de nascimento no formato "yyyy-MM-dd"
        return String.format("%04d-%02d-%02d", ano, mes, dia);
    }

    public static int gerarNumeroAleatorio(int minDigitos, int maxDigitos) {
        Random rand = new Random();
        int numDigitos = minDigitos + rand.nextInt(maxDigitos - minDigitos + 1); // Gera um número de dígitos entre minDigitos e maxDigitos

        int numero = 0;
        for (int i = 0; i < numDigitos; i++) {
            int digito;
            if (i == 0) {
                // O primeiro dígito não pode ser zero, então geramos um dígito de 1 a 9
                digito = 1 + rand.nextInt(9);
            } else {
                // Para os dígitos seguintes, pode ser qualquer dígito de 0 a 9
                digito = rand.nextInt(10);
            }
            
            numero = numero * 10 + digito; // Adiciona o dígito ao número gerado
        }

        return numero;
    }
    
    public static String gerarCepAleatorio() {
        Random random = new Random();
        Set<String> cepsGerados = new HashSet<>();

        while (true) {
            int parte1 = random.nextInt(10000);
            int parte2 = random.nextInt(1000);

            // Formate o CEP no formato "0000-000"
            String cep = String.format("%04d-%03d", parte1, parte2);

            // Verifique se o CEP gerado é único
            if (!cepsGerados.contains(cep)) {
                cepsGerados.add(cep);
                return cep;
            }
        }
    }
    
    public static void main(String[] args) {
    	
    	String combinacao = gerarCombinacaoAleatoria();
        System.out.println("Combinação Aleatória: " + combinacao);
        
        String nomeAleatorio = gerarNomeAleatorio();
        System.out.println("Nome Aleatório: " + nomeAleatorio);
        
        String dataNascimentoAleatoria = gerarDataNascimentoAleatoria();
        System.out.println("Data de Nascimento Aleatória: " + dataNascimentoAleatoria);
        
        String placaAleatoria = gerarPlacaAleatoriaUnica();
        System.out.println("Placa Aleatória: " + placaAleatoria);
        
        Random random = new Random();
     

        String cepAleatorio = gerarCepAleatorio();
        System.out.println("CEP Aleatório: " + cepAleatorio);
        
        String cpfAleatorio = gerarCpfUnico();
        System.out.println("CPF Aleatório: " + cpfAleatorio);
        
        String cnpjAleatorio = gerarCnpjUnico();
        System.out.println("CNPJ Aleatório: " + cnpjAleatorio);
        
        String ddd = gerarDddAleatorio();
        
        String numeroTelefone = gerarNumeroTelefoneAleatorio();
        System.out.println("DDD: " + ddd);
        System.out.println("Número de Telefone: " + numeroTelefone);
        
        String emailFuncionario = gerarEmailFuncionarioAleatorio();
        String emailEmpresa = gerarEmailEmpresaAleatorio();
        System.out.println("Email do Funcionário: " + emailFuncionario);
        System.out.println("Email da Empresa: " + emailEmpresa);
        
        String logradouro = gerarLogradouroAleatorio();
        System.out.println( logradouro);
        
        
        int numeroAleatorio = gerarNumeroAleatorio(1,4);
        System.out.println("Número Aleatório: de 1 a 4 digitos " + numeroAleatorio);
        
        int numeroAleatorio2 = gerarNumeroAleatorio(3,4);
        System.out.println("Número Aleatório: de 3 a 4 digitos " + numeroAleatorio2);
        
    }
}