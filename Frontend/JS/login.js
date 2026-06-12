const formLogin = document.querySelector("#form-login");
const errorMsg = document.querySelector("#error-msg");

formLogin.addEventListener("submit", async (e) => {
    e.preventDefault();

    const login = document.querySelector("#email").value.trim();
    const password = document.querySelector("#senha").value;

    errorMsg.style.display = "none";

    try {
        const resposta = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ login, password })
        });

        if (!resposta.ok) {
            errorMsg.textContent = "E-mail ou senha incorretos.";
            errorMsg.style.display = "block";
            return;
        }

        const dados = await resposta.json();

        localStorage.setItem("role", dados.role);
        localStorage.setItem("nome", dados.nome);
        localStorage.setItem("login", login);

        if (dados.role === "CIDADAO") {
            window.location.href = "cidadao/menu-cidadao.html";
        } else if (dados.role === "PRESTADOR") {
            window.location.href = "prestador/menu-prestador.html";
        }
    } catch {
        errorMsg.textContent = "Erro ao conectar com o servidor.";
        errorMsg.style.display = "block";
    }
});
