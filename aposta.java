
package jogodeapostas;
//back boo
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class JogoDeApostas {
    private double saldo;

    public JogoDeApostas(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void creditarSaldo(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Saldo creditado com sucesso. Novo saldo: " + formatarDinheiro(saldo));
        } else {
            System.out.println("Valor inválido para crédito.");
        }
    }

    public void apostar(double valorAposta, String corEscolhida) {
        if (valorAposta <= 0) {
            System.out.println("Valor de aposta inválido.");
            return;
        }

        if (valorAposta > saldo) {
            System.out.println("Saldo insuficiente para fazer a aposta.");
            return;
        }

        Random random = new Random();
        int dadoVermelho = random.nextInt(6) + 1; // Gera um número aleatório entre 1 e 6 (dado vermelho)
        int dadoAzul = random.nextInt(6) + 1; // Gera um número aleatório entre 1 e 6 (dado azul)

        System.out.println("Resultado do dado vermelho: " + dadoVermelho);
        System.out.println("Resultado do dado azul: " + dadoAzul);

        if (dadoVermelho == dadoAzul) {
            saldo += valorAposta * 15; // Aposta empatada
            System.out.println("Empate! Você ganhou 15x o valor apostado. Seu saldo atual é: " + formatarDinheiro(saldo));
        } else if (corEscolhida.equalsIgnoreCase("vermelho") && dadoVermelho > dadoAzul) {
            saldo += valorAposta * 2; // Aposta vencedora em vermelho
            System.out.println("Parabéns! Você acertou a cor vermelha. Seu saldo atual é: " + formatarDinheiro(saldo));
        } else if (corEscolhida.equalsIgnoreCase("azul") && dadoAzul > dadoVermelho) {
            saldo += valorAposta * 2; // Aposta vencedora em azul
            System.out.println("Parabéns! Você acertou a cor azul. Seu saldo atual é: " + formatarDinheiro(saldo));
        } else {
            saldo -= valorAposta; // Aposta perdida
            System.out.println("Que pena! Você errou a cor. Seu saldo atual é: " + formatarDinheiro(saldo));
        }
    }

    public static void main(String[] args) {
        JogoDeApostas jogo = new JogoDeApostas(0);
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Creditar saldo");
            System.out.println("2 - Apostar");
            System.out.println("0 - Sair");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o valor a ser creditado:");
                    double valorCredito = scanner.nextDouble();
                    jogo.creditarSaldo(valorCredito);
                    break;
                case 2:
                    System.out.println("Digite o valor da aposta:");
                    double valorAposta = scanner.nextDouble();
                    scanner.nextLine(); // Consumir a quebra de linha
                    System.out.println("Escolha a cor (vermelho, azul ou empate):");
                    String corEscolhida = scanner.nextLine().toLowerCase();
                    if (corEscolhida.equals("vermelho") || corEscolhida.equals("azul") || corEscolhida.equals("empate")) {
                        jogo.apostar(valorAposta, corEscolhida);
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do jogo.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    // Método auxiliar para formatar um valor double como dinheiro
    private static String formatarDinheiro(double valor) {
        Locale brasil = new Locale("pt", "BR");
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(brasil);
        return formatoMoeda.format(valor);
    }
}
