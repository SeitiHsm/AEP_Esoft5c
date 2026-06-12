const params = new URLSearchParams(window.location.search);
const protocolo = params.get("protocolo");

async function carregarHistorico() {
    if (!protocolo) return;

    const resposta = await fetch(`http://localhost:8080/solicitacoes/${protocolo}`);
    if (!resposta.ok) return;

    const solicitacao = await resposta.json();
    const tbody = document.querySelector("#historico-tbody");
    if (!tbody) return;

    tbody.innerHTML = "";

    const historico = solicitacao.historico || [];

    if (historico.length === 0) {
        tbody.innerHTML = '<tr><td colspan="3">Nenhum histórico registrado</td></tr>';
        return;
    }

    for (const item of historico) {
        const data = item.data
            ? new Date(item.data).toLocaleDateString("pt-BR")
            : "-";
        const tr = document.createElement("tr");
        tr.innerHTML = `<td>${data}</td><td>${item.status}</td><td>${item.responsavel || "-"}</td>`;
        tbody.appendChild(tr);
    }
}

carregarHistorico();

// Atualiza o link "Atualizar status" com o protocolo atual (só existe na página do prestador)
const linkAtualizar = document.querySelector('a[href*="atualizar-status-solicitacao-prestador.html"]');
if (linkAtualizar && protocolo) {
    linkAtualizar.href = `atualizar-status-solicitacao-prestador.html?protocolo=${protocolo}`;
}
