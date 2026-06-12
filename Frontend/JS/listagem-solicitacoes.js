const API = "http://localhost:8080";

const tbody    = document.querySelector("#solicitacoes-tbody");
const infoUrl  = tbody.dataset.infoUrl;
const form     = document.querySelector("#form-filtro");

let todas = [];

// ── Carrega do backend ────────────────────────────────────────────
async function carregar() {
    try {
        const resp = await fetch(`${API}/solicitacoes`);
        if (!resp.ok) throw new Error();
        todas = await resp.json();
        renderizar(todas);
    } catch {
        tbody.innerHTML = '<tr><td colspan="6">Erro ao carregar solicitações.</td></tr>';
    }
}

// ── Renderiza linhas na tabela ────────────────────────────────────
function renderizar(lista) {
    if (lista.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6">Nenhuma solicitação encontrada.</td></tr>';
        return;
    }

    tbody.innerHTML = lista.map(s => `
        <tr>
            <td>${s.protocolo}</td>
            <td>${s.categoria}</td>
            <td>${s.bairro}</td>
            <td>${s.nome || "Anônimo"}</td>
            <td>${formatarPrioridade(s.prioridade)}</td>
            <td><a class="access-button primary-button"
                   href="${infoUrl}?protocolo=${s.protocolo}">Acessar</a></td>
        </tr>
    `).join("");
}

function formatarPrioridade(p) {
    return { ALTA: "Alta", MEDIA: "Média", BAIXA: "Baixa" }[p] ?? p;
}

// ── Filtragem client-side ─────────────────────────────────────────
function filtrar() {
    const id        = document.querySelector("#search-id").value.trim();
    const bairro    = document.querySelector("#search-bairro").value.trim().toLowerCase();
    const nome      = document.querySelector("#search-nome").value.trim().toLowerCase();
    const categoria = document.querySelector("#filtro-categoria").value.toLowerCase();

    const priosMarcadas = Array.from(
        document.querySelectorAll('input[name="prio"]:checked')
    ).map(cb => cb.value.toUpperCase());

    // mapa para bater o valor do select com o texto do banco
    const mapaCategoria = {
        iluminacao: "ilumina",
        buraco:     "buraco",
        limpeza:    "limpeza",
        saneamento: "saneamento",
        outros:     "outros"
    };

    const resultado = todas.filter(s => {
        if (id && !String(s.protocolo).includes(id)) return false;

        if (bairro && !s.bairro.toLowerCase().includes(bairro)) return false;

        if (nome && !(s.nome || "").toLowerCase().includes(nome)) return false;

        if (categoria) {
            const termo = mapaCategoria[categoria] ?? categoria;
            if (!s.categoria.toLowerCase().includes(termo)) return false;
        }

        if (priosMarcadas.length > 0 && !priosMarcadas.includes(s.prioridade)) return false;

        return true;
    });

    renderizar(resultado);
}

// ── Eventos ───────────────────────────────────────────────────────
form.addEventListener("submit", e => {
    e.preventDefault();
    filtrar();
});

// Limpar / Cancelar Pesquisa (ambos type="reset") restauram a lista completa
form.addEventListener("reset", () => {
    // setTimeout 0 garante que os campos já foram limpos antes de renderizar
    setTimeout(() => renderizar(todas), 0);
});

carregar();
