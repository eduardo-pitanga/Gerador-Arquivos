import com.github.javafaker.Faker;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class GeradorDeAlunos {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("pt-BR"));
        Random random = new Random();
        BufferedWriter buffwrite = null;
        String entrada;

        int quantidade_alunos = 1000000;

        // Escreve os alunos em um arquivo dat
        String nomeArquivo = "alunos.dat";
        try {
            buffwrite = new BufferedWriter(new FileWriter(nomeArquivo));
        } catch (IOException e) {
            System.out.println("erro ao abrir buffer");
        }

        boolean condicao = true;
        for (int j = 0; j < quantidade_alunos; j++) {
            String nome = faker.name().fullName();
            String matricula = faker.idNumber().valid();
            int nota = random.nextInt(101); // Nota entre 0 e 100
            int frequencia = random.nextInt(101); // Frequência entre 0 e 100

            // escrevendo nos buffers
            entrada = nome + "," + matricula + "," + nota + "," + frequencia + "\n";
            try {
                assert buffwrite != null;
                buffwrite.write(entrada);
            } catch (IOException e) {
                condicao = false;
                System.out.println("erro ao escrever pelo buffer");
            }
        }

        if (condicao) System.out.println("Arquivo '" + nomeArquivo + "' gerado com sucesso!");
        // fechando buffer
        try {
            buffwrite.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}