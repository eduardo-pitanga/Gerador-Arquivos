import com.github.javafaker.Faker;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorDeAlunos {
    public static void main(String[] args) {
        Faker faker = new Faker();
        Random random = new Random();
        BufferedWriter buffwrite = null;
        String registro;

        int quantidade_alunos = 10;
        List<Aluno> alunos = new ArrayList<>();

        for (int j = 0; j < quantidade_alunos; j++) {
            String nome = faker.name().fullName();
            String matricula = faker.idNumber().valid();
            int nota = random.nextInt(101); // Nota entre 0 e 100
            int frequencia = random.nextInt(101); // Frequência entre 0 e 100

            alunos.add(new Aluno(nome, matricula, nota, frequencia));
        }

        // Escreve os alunos em um arquivo dat
        String nomeArquivo = "alunos.dat";
        try {
            buffwrite = new BufferedWriter(new FileWriter(nomeArquivo));
        } catch (IOException e) {
            System.out.println("erro ao abrir buffer");
        }
        // escrevendo nos buffers
        for (Aluno aluno : alunos) {
            String entrada = aluno.getNome() + "," + aluno.getMatricula() + "," + aluno.getNota() + "," + aluno.getFrequencia() + "\n";
            try {
                assert buffwrite != null;
                buffwrite.write(entrada);
            } catch (IOException e) {
                System.out.println("erro ao escrever pelo buffer");
            }
        }
        // fechando buffer
        try {
            assert buffwrite != null;
            buffwrite.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
