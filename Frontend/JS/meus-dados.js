const API = "http://localhost:8080";

const loginArmazenado = localStorage.getItem("login");
const role            = localStorage.getItem("role");

const menuLink  = document.querySelector("#link-menu");
const voltarLink = document.querySelector("#link-voltar");

// Aponta o "Inicio" e o "Voltar" para o menu correto do perfil
const menuDestino = role === "PRESTADOR"
    ? "prestador/menu-prestador.html"
    : "cidadao/menu-cidadao.html";

if (menuLink)  menuLink.href  = menuDestino;
if (voltarLink) voltarLink.href = menuDestino;

const emailInput    = document.querySelector("#email");
const perfilInput   = document.querySelector("#perfil");
const nomeInput     = document.querySelector("#nome");
const novaSenhaInput     = document.querySelector("#nova-senha");
const confirmaSenhaInput = document.querySelector("#confirma-nova-senha");
const errorMsg   = document.querySelector("#error-msg");
const successMsg = document.querySelector("#success-msg");

function mostrarErro(msg) {
    errorMsg.textContent = msg;
    errorMsg.style.display = "block";
    successMsg.style.display = "none";
}

function mostrarSucesso(msg) {
    successMsg.textContent = msg;
    successMsg.style.display = "block";
    errorMsg.style.display = "none";
}

// ── Carrega dados atuais ──────────────────────────────────────────
async function carregarDados() {
    if (!loginArmazenado) {
        window.location.href = "login.html";
        return;
    }

    emailInput.value  = loginArmazenado;
    perfilInput.value = role === "PRESTADOR" ? "Prestador de serviço" : "Cidadão";

    const nomeLocal = localStorage.getItem("nome");
    if (nomeLocal) nomeInput.value = nomeLocal;
}

// ── Salvar alterações ─────────────────────────────────────────────
document.querySelector("#form-dados").addEventListener("submit", async e => {
    e.preventDefault();

    const nome       = nomeInput.value.trim();
    const novaSenha  = novaSenhaInput.value;
    const confirma   = confirmaSenhaInput.value;

    errorMsg.style.display = "none";
    successMsg.style.display = "none";

    if (!nome) {
        mostrarErro("O nome não pode ficar em branco.");
        return;
    }

    if (novaSenha && novaSenha !== confirma) {
        mostrarErro("As senhas não coincidem.");
        return;
    }

    if (!confirm("Confirma a atualização dos seus dados?")) return;

    try {
        const resp = await fetch(`${API}/auth/dados/${encodeURIComponent(loginArmazenado)}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                nome,
                novaSenha: novaSenha || null
            })
        });

        if (!resp.ok) {
            mostrarErro("Erro ao salvar os dados.");
            return;
        }

        localStorage.setItem("nome", nome);
        novaSenhaInput.value     = "";
        confirmaSenhaInput.value = "";
        mostrarSucesso("Dados atualizados com sucesso!");
    } catch {
        mostrarErro("Erro ao conectar com o servidor.");
    }
});

// ── Excluir conta ─────────────────────────────────────────────────
document.querySelector("#btn-excluir").addEventListener("click", async () => {
    if (!confirm("Tem certeza que deseja excluir sua conta?\nEsta ação não pode ser desfeita.")) return;

    if (!confirm("Confirme novamente: excluir permanentemente a conta?")) return;

    try {
        const resp = await fetch(`${API}/auth/dados/${encodeURIComponent(loginArmazenado)}`, {
            method: "DELETE"
        });

        if (!resp.ok) {
            mostrarErro("Erro ao excluir a conta.");
            return;
        }

        localStorage.clear();
        window.location.href = "login.html";
    } catch {
        mostrarErro("Erro ao conectar com o servidor.");
    }
});

carregarDados();
