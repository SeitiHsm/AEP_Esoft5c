package com.AEP2.demo;

import com.AEP2.demo.DTO.AtualizarStatusDTO;
import com.AEP2.demo.DTO.SolicitacaoDTO;
import com.AEP2.demo.enums.EnumPrioridade;
import com.AEP2.demo.enums.EnumStatus;
import com.AEP2.demo.enums.UserRole;
import com.AEP2.demo.models.Solicitacao;
import com.AEP2.demo.models.User;
import com.AEP2.demo.repositories.UserRepository;
import com.AEP2.demo.services.ServicoSolicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicoSolicitacao servicoSolicitacao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        criarUsuarios();
        criarSolicitacoes();
    }

    private void criarUsuarios() {
        userRepository.save(new User(
                "cidadao@teste.com",
                passwordEncoder.encode("123456"),
                UserRole.CIDADAO,
                "João Cidadão"
        ));
        userRepository.save(new User(
                "prestador@teste.com",
                passwordEncoder.encode("123456"),
                UserRole.PRESTADOR,
                "Maria Prestadora"
        ));
    }

    private void criarSolicitacoes() {
        // 1 - ABERTO (1 historico)
        criar("Iluminação pública", "Poste apagado há 3 dias na esquina da rua principal", "Centro", false, "Carlos Silva", EnumPrioridade.ALTA);

        // 2 - ABERTO (1 historico)
        criar("Limpeza urbana", "Lixo acumulado no canteiro central sem coleta há uma semana", "Jardim América", false, "Ana Souza", EnumPrioridade.BAIXA);

        // 3 - TRIAGEM (2 historicos)
        Solicitacao s3 = criar("Buraco na via", "Buraco grande na pista que prejudica o tráfego e causa acidentes", "Vila Nova", false, "Pedro Alves", EnumPrioridade.ALTA);
        atualizar(s3.getProtocolo(), EnumStatus.TRIAGEM, "Solicitação recebida e encaminhada para triagem", "Atendente 01");

        // 4 - TRIAGEM (2 historicos)
        Solicitacao s4 = criar("Saneamento", "Vazamento de esgoto na calçada com mau cheiro intenso", "Bela Vista", false, "Fernanda Lima", EnumPrioridade.ALTA);
        atualizar(s4.getProtocolo(), EnumStatus.TRIAGEM, "Em análise pela equipe técnica", "Atendente 02");

        // 5 - EM_EXECUCAO (3 historicos)
        Solicitacao s5 = criar("Iluminação pública", "Fio elétrico exposto em poste danificado representa risco à população", "Centro Sul", false, "Roberto Costa", EnumPrioridade.ALTA);
        atualizar(s5.getProtocolo(), EnumStatus.TRIAGEM, "Encaminhado para equipe elétrica", "Atendente 01");
        atualizar(s5.getProtocolo(), EnumStatus.EM_EXECUCAO, "Equipe em campo realizando reparo no poste", "Técnico Eletricista");

        // 6 - EM_EXECUCAO (3 historicos)
        Solicitacao s6 = criar("Buraco na via", "Cratera formada após chuva forte dificulta passagem de veículos", "Norte", false, "Juliana Mendes", EnumPrioridade.MEDIA);
        atualizar(s6.getProtocolo(), EnumStatus.TRIAGEM, "Vistoria agendada para o local", "Atendente 03");
        atualizar(s6.getProtocolo(), EnumStatus.EM_EXECUCAO, "Tapa-buraco em execução no momento", "Equipe de Pavimentação");

        // 7 - RESOLVIDO (4 historicos)
        Solicitacao s7 = criar("Limpeza urbana", "Terreno baldio com acúmulo de entulho e mato alto", "Leste", true, null, EnumPrioridade.MEDIA);
        atualizar(s7.getProtocolo(), EnumStatus.TRIAGEM, "Solicitação validada e encaminhada", "Atendente 02");
        atualizar(s7.getProtocolo(), EnumStatus.EM_EXECUCAO, "Equipe de limpeza alocada para o local", "Supervisor de Limpeza");
        atualizar(s7.getProtocolo(), EnumStatus.RESOLVIDO, "Limpeza concluída e área totalmente saneada", "Supervisor de Limpeza");

        // 8 - RESOLVIDO (4 historicos)
        Solicitacao s8 = criar("Outros", "Placa de sinalização caída na via representa risco de acidente", "Oeste", false, "Mariana Torres", EnumPrioridade.BAIXA);
        atualizar(s8.getProtocolo(), EnumStatus.TRIAGEM, "Encaminhado para equipe de sinalização", "Atendente 01");
        atualizar(s8.getProtocolo(), EnumStatus.EM_EXECUCAO, "Substituição da placa em andamento", "Equipe de Sinalização");
        atualizar(s8.getProtocolo(), EnumStatus.RESOLVIDO, "Nova placa instalada corretamente", "Equipe de Sinalização");

        // 9 - ENCERRADO (5 historicos)
        Solicitacao s9 = criar("Saneamento", "Cano de água rompido alagando a rua e calçada do bairro", "Centro Norte", false, "Lucas Ferreira", EnumPrioridade.ALTA);
        atualizar(s9.getProtocolo(), EnumStatus.TRIAGEM, "Emergência registrada, prioridade máxima", "Atendente 03");
        atualizar(s9.getProtocolo(), EnumStatus.EM_EXECUCAO, "Equipe de encanamento no local", "Técnico Hidráulico");
        atualizar(s9.getProtocolo(), EnumStatus.RESOLVIDO, "Cano reparado e vazamento contido com sucesso", "Técnico Hidráulico");
        atualizar(s9.getProtocolo(), EnumStatus.ENCERRADO, "Serviço verificado pelo supervisor e encerrado", "Supervisor Geral");

        // 10 - ENCERRADO (5 historicos)
        Solicitacao s10 = criar("Iluminação pública", "Semáforo com defeito causando transtorno no cruzamento", "Centro", false, "Beatriz Ramos", EnumPrioridade.MEDIA);
        atualizar(s10.getProtocolo(), EnumStatus.TRIAGEM, "Encaminhado para SEMOSP", "Atendente 02");
        atualizar(s10.getProtocolo(), EnumStatus.EM_EXECUCAO, "Manutenção do semáforo iniciada pela equipe", "Técnico SEMOSP");
        atualizar(s10.getProtocolo(), EnumStatus.RESOLVIDO, "Semáforo reparado e funcionando normalmente", "Técnico SEMOSP");
        atualizar(s10.getProtocolo(), EnumStatus.ENCERRADO, "Verificação final concluída com sucesso", "Supervisor Geral");
    }

    private Solicitacao criar(String categoria, String descricao, String bairro,
                              boolean anonimo, String nome, EnumPrioridade prioridade) {
        SolicitacaoDTO dto = new SolicitacaoDTO();
        dto.setCategoria(categoria);
        dto.setDescricao(descricao);
        dto.setBairro(bairro);
        dto.setAnonimo(anonimo);
        dto.setNome(nome);
        dto.setPrioridade(prioridade);
        return servicoSolicitacao.criar(dto);
    }

    private void atualizar(Integer protocolo, EnumStatus status, String comentario, String responsavel) {
        AtualizarStatusDTO dto = new AtualizarStatusDTO();
        dto.setNovoStatus(status);
        dto.setComentario(comentario);
        dto.setResponsavel(responsavel);
        servicoSolicitacao.atualizar(protocolo, dto);
    }
}
