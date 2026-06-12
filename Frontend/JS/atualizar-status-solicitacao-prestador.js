const API = "http://localhost:8080";

const params    = new URLSearchParams(window.location.search);
const protocolo = params.get("protocolo");

const requestIdInput  = document.getElementById("request-id");
const statusSelect    = document.getElementById("status-atual");
const responsavelInput = document.getElementById("responsavel-tecnico");
const comentarioInput = document.getElementById("comentario");
const previewText     = document.getElementById("preview-text");
const updateButton    = document.getElementById("update-button");
const saveButton      = document.getElementById("save-button");
const cancelButton    = document.getElementById("cancel-button");

// Preenche o campo ID com o protocolo da URL
if (protocolo) {
    requestIdInput.value = protocolo;
}

// ── Botão "Atualizar": gera o preview ──────────────────────────────
updateButton.addEventListener("click", () => {
    const status     = statusSelect.options[statusSelect.selectedIndex]?.text || "Sem status";
    const responsavel = responsavelInput.value.trim() || "Não informado";
    const comentario = comentarioInput.value.trim() || "Nenhum comentário.";
    const agora      = new Date();
    const data       = agora.toLocaleDateString("pt-BR");
    const hora       = agora.toLocaleTimeString("pt-BR");

    previewText.value =
        `Protocolo: ${protocolo || "?"}\n` +
        `Data: ${data} ${hora}\n` +
        `Status: ${status}\n` +
        `Responsável: ${responsavel}\n` +
        `Comentário: ${comentario}`;
});

// ── Botão "Salvar": chama a API e redireciona ──────────────────────
saveButton.addEventListener("click", async () => {
    if (!protocolo) {
        alert("Protocolo não encontrado na URL.");
        return;
    }

    const novoStatus = statusSelect.value;
    if (!novoStatus) {
        alert("Selecione um status antes de salvar.");
        return;
    }

    const responsavel = responsavelInput.value.trim();
    if (!responsavel) {
        alert("Informe o responsável técnico.");
        return;
    }

    try {
        const resp = await fetch(`${API}/solicitacoes/${protocolo}/status`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                novoStatus,
                comentario: comentarioInput.value.trim(),
                responsavel
            })
        });

        if (!resp.ok) {
            alert("Não foi possível atualizar o status. Verifique se a transição é válida.");
            return;
        }

        window.location.href = `informacao-solicitacao-prestador.html?protocolo=${protocolo}`;
    } catch {
        alert("Erro ao conectar com o servidor.");
    }
});

// ── Botão "Cancelar": limpa o formulário ──────────────────────────
cancelButton.addEventListener("click", () => {
    document.getElementById("status-form").reset();
    previewText.value = "";
});
