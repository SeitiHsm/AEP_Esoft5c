import Services.ServicoSolicitacoes;
import models.EnumStatus;
import models.EnumPrioridade;
import models.Solicitacao;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoSolicitacoes servico = new ServicoSolicitacoes();
        int opcao;

        do {
            System.out.println("\n=== SISTEMA GOVTECH: OBSERVAÇÃO ===");
            System.out.println("1- Registrar Nova Solicitação");
            System.out.println("2- Listar Todas as Demandas");
            System.out.println("3- Consultar Protocolo (Histórico)");
            System.out.println("4- Atualizar Status (Área Técnica)");
            System.out.println("5- Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Categoria do problema (ex: iluminação, buraco, limpeza, saúde, segurança escolar, etc.): "); String cat = scanner.nextLine();
                    System.out.print("Descrição do Problema: "); String desc = scanner.nextLine();
                    System.out.print("Bairro/Localização: "); String bairro = scanner.nextLine();
                    System.out.print("Deseja Anonimato? (1-Sim / 0-Não): ");
                    boolean anon = scanner.nextInt() == 1; scanner.nextLine();

                    String nome = "";
                    if (!anon) { System.out.print("Seu Nome: "); nome = scanner.nextLine(); }

                    System.out.print("Prioridade (1-Alta, 2-Média, 3-Baixa): ");
                    EnumPrioridade prio = EnumPrioridade.deInt(scanner.nextInt()); scanner.nextLine();

                    servico.criar(cat, desc, bairro, anon, nome, prio);
                    pausar(scanner);
                    break;

                case 2:
                    servico.listarTodas();
                    pausar(scanner);
                    break;

                case 3:
                    System.out.print("Digite o Protocolo: ");
                    int pBusca = scanner.nextInt(); scanner.nextLine();
                    Solicitacao s = servico.buscarPorProtocolo(pBusca);
                    if (s != null) { s.exibir(); s.mostrarHistorico(); }
                    else System.out.println("Protocolo não localizado.");
                    pausar(scanner);
                    break;

                case 4:
                    System.out.print("Protocolo para moderar: ");
                    int pMod = scanner.nextInt(); scanner.nextLine();
                    Solicitacao sol = servico.buscarPorProtocolo(pMod);

                    if (sol == null) {
                        System.out.println("Erro: Protocolo inexistente.");
                        pausar(scanner);
                        break;
                    }

                    System.out.println("Status Atual: " + sol.getStatus());
                    System.out.print("Mudar para (1-TRIAGEM, 2-EM_EXECUCAO, 3-RESOLVIDO, 4-ENCERRADO): ");
                    EnumStatus novoStatus = EnumStatus.deInt(scanner.nextInt()); scanner.nextLine();

                    if (sol.podeMudarPara(novoStatus)) {
                        System.out.print("Responsável técnico: "); String resp = scanner.nextLine();
                        System.out.print("Parecer/Comentário: "); String com = scanner.nextLine();
                        servico.atualizarStatus(pMod, novoStatus, com, resp);
                        System.out.println("Status atualizado para: " + novoStatus);
                    } else {
                        System.out.println("Erro: Transição não permitida de " + sol.getStatus() + " para " + novoStatus);
                    }
                    pausar(scanner);
                    break;
            }
        } while (opcao != 5);
        System.out.println("Sistema encerrado.");
    }

    public static void pausar(Scanner scanner) {
        System.out.println("\n[Pressione ENTER para continuar]");
        scanner.nextLine();
        for (int i = 0; i < 15; i++) System.out.println();
    }
}