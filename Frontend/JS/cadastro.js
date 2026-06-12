const formCadastro = document.querySelector("#form-cadastro");
const errorMsg = document.querySelector("#error-msg");
const successMsg = document.querySelector("#success-msg");

formCadastro.addEventListener("submit", async (e) => {
    e.preventDefault();

    const login = document.querySelector("#email").value.trim();
    const nome = document.querySelector("#nome").value.trim();
    const password = document.querySelector("#senha").value;
    const confirmaSenha = document.querySelector("#confirma-senha").value;
    const role = document.querySelector("#perfil").value;

    errorMsg.style.display = "none";
    successMsg.style.display = "none";

    if (!login || !nome || !password || !role) {
        errorMsg.textContent = "Preencha todos os campos.";
        errorMsg.style.display = "block";
        return;
    }

    if (password !== confirmaSenha) {
        errorMsg.textContent = "As senhas não coincidem.";
        errorMsg.style.display = "block";
        return;
    }

    try {
        const resposta = await fetch("http://localhost:8080/auth/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ login, password, role, nome })
        });

        if (!resposta.ok) {
            errorMsg.textContent = "E-mail já cadastrado.";
            errorMsg.style.display = "block";
            return;
        }

        successMsg.style.display = "block";

        setTimeout(() => {
            window.location.href = "login.html";
        }, 1500);
    } catch {
        errorMsg.textContent = "Erro ao conectar com o servidor.";
        errorMsg.style.display = "block";
    }
});
