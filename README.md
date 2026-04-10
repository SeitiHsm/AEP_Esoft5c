# 🚀 Projeto ObservaAção - GovTech

> **Desafio Profissional - 5º Semestre | Engenharia de Software - Unicesumar**

O **ObservaAção** é uma solução digital desenvolvida para aproximar o cidadão da gestão pública, permitindo o registro e acompanhamento de solicitações de serviços urbanos de forma transparente e acessível.

---

## 👥 Integrantes do Grupo
*   **Acadêmico 1:** Daniel Morotti Vieira - RA: `24191444-2`
*   **Acadêmico 2:** Enzo Seiti Hashimoto - RA: `24190357-2`

---

## 📺 Demonstração em Vídeo
Confira a apresentação do sistema e a explicação técnica das práticas aplicadas:
👉 **[CLIQUE AQUI PARA ASSISTIR AO VÍDEO](https://youtu.be/U8Gby1vnIEI?si=SRv5K0mg-izYeaHQ)**

---

## 📄 Documentação Complementar (PDF)
Os documentos detalhados exigidos para a entrega (Perfis, Personas e Relatório de Manutenção) encontram-se na raiz deste repositório:
*   [Visualizar PDF de Perfis e Personas](./Perfis_Personas.pdf)
*   [Visualizar PDF do Relatório Clean Code](./Relatorio_Clean_Code.pdf)

---

## 🌍 Contexto e Impacto Social (ODS)
O projeto foi concebido sob a ótica dos **Objetivos de Desenvolvimento Sustentável da ONU**:
*   **ODS 10 (Redução das Desigualdades):** Democratização do acesso aos serviços públicos para todos os cidadãos.
*   **ODS 11 (Cidades e Comunidades Sustentáveis):** Melhoria da infraestrutura urbana e zeladoria.
*   **ODS 16 (Paz, Justiça e Instituições Eficazes):** Fomento à transparência e combate ao esquecimento de demandas públicas.

---

## 👤 Perfis de Usuário e Personas (IHC)
O sistema foi projetado considerando as necessidades de acessibilidade dos seguintes perfis:

### Perfil 1: Baixa Familiaridade Digital
*   **Personas:** Sara Castro, Marilda Ribeiro, Murilo Sampaio.
*   **Foco:** Interfaces simples, linguagem clara e fluxo direto sem complicações técnicas.

### Perfil 2: Vulnerabilidade e Receio de Retaliação
*   **Personas:** Luana Beneguetti, Denise Amari, Miguel Figueredo.
*   **Foco:** Garantia de anonimato funcional e proteção de identidade para denúncias seguras.

### Perfil 3: Servidor Público / Gestor
*   **Personas:** Matheus Watanabe, Lucas Lima, João Nascimento.
*   **Foco:** Organização de demandas por prioridade, histórico detalhado e agilidade processual.

---

## 🛠️ Relatório de Clean Code Aplicado

### 1. DRY (Don't Repeat Yourself)
*   **Local:** `Solicitacao.java` (Método `podeMudarPara`).
*   **Melhoria:** Centralizamos a lógica de transição de status em um único ponto, evitando que regras de negócio fiquem duplicadas e facilitando atualizações futuras.

### 2. Guard Clauses (Fail Fast)
*   **Local:** `ServicoSolicitacoes.java` (Método `criar`).
*   **Melhoria:** Validação de erros no início do método, interrompendo a execução imediatamente se algo estiver errado. Isso elimina o excesso de `if/else` aninhados.

### 3. Princípio da Responsabilidade Única (SRP)
*   **Local:** `Solicitacao.java` (Método `exibir`).
*   **Melhoria:** A classe Solicitacao é a única responsável por saber como formatar seus dados para o usuário, garantindo consistência visual em todo o sistema.

---

## 💻 Como Executar o Projeto

Este é um projeto Java funcional via terminal (CLI).

1.  **Pré-requisitos:** Ter o Java JDK 17 ou superior instalado.
2.  **Clonar o repositório:**
    ```bash
    git clone https://github.com/SeitiHsm/AEP_Esoft5c.git
    ```
3.  **Compilar e Rodar:**
    *   Abra o projeto no IntelliJ IDEA ou sua IDE de preferência.
    *   Aguarde a indexação dos arquivos.
    *   Execute a classe `Main.java` (localizada na pasta `src`).
4.  **Interação:** Utilize os números do teclado para navegar entre as opções de cadastro, listagem e atualização de status.

---

## 🛠️ Tecnologias Utilizadas
*   **Linguagem:** Java 17
*   **Arquitetura:** Orientação a Objetos (POO) com separação em camadas (`models` e `Services`).
*   **Interface:** Console / Terminal (CLI).
