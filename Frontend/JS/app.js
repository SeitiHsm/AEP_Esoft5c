// ── Dropdown do usuário ───────────────────────────────────────────
const userButton = document.querySelector(".user-button");
const dropdown = document.querySelector(".dropdown");

if (userButton && dropdown) {
  userButton.addEventListener("click", () => {
    const isOpen = dropdown.classList.toggle("open");
    userButton.setAttribute("aria-expanded", String(isOpen));
  });

  document.addEventListener("click", (event) => {
    if (!event.target.closest(".user-menu")) {
      dropdown.classList.remove("open");
      userButton.setAttribute("aria-expanded", "false");
    }
  });
}

// ── Painéis de conteúdo (data-panel) ─────────────────────────────
const actionButtons = document.querySelectorAll("[data-panel]");
const contentPanels = document.querySelectorAll("[data-content-panel]");

actionButtons.forEach((button) => {
  button.addEventListener("click", () => {
    const panelId = button.dataset.panel;

    actionButtons.forEach((item) => item.classList.remove("active"));
    button.classList.add("active");

    contentPanels.forEach((panel) => {
      panel.classList.toggle("hidden", panel.id !== panelId);
    });
  });
});

// ── Anonimato ─────────────────────────────────────────────────────
const anonimatoSelect = document.querySelector("#anonimato");
const nomeSolicitanteInput = document.querySelector("#nome");

function atualizarNomeSolicitante() {
  if (!anonimatoSelect || !nomeSolicitanteInput) return;

  const anonimo = anonimatoSelect.value === "sim";
  nomeSolicitanteInput.disabled = anonimo;

  if (anonimo) {
    nomeSolicitanteInput.value = "";
    nomeSolicitanteInput.placeholder = "Solicitacao anonima";
  } else {
    nomeSolicitanteInput.placeholder = "Digite seu nome";
  }
}

if (anonimatoSelect && nomeSolicitanteInput) {
  anonimatoSelect.addEventListener("change", atualizarNomeSolicitante);
  atualizarNomeSolicitante();
}

// ── Botão Voltar ──────────────────────────────────────────────────
const backLink = document.querySelector("[data-back-link]");

if (backLink) {
  backLink.addEventListener("click", (event) => {
    if (window.history.length > 1) {
      event.preventDefault();
      window.history.back();
    }
  });
}

// ── Navbar: nome e avatar do usuário ─────────────────────────────
const nomeArmazenado = localStorage.getItem("nome");
const userAvatar     = document.querySelector(".user-avatar");
const userNomeSpan   = document.querySelector(".user-button span:not(.user-avatar)");

if (nomeArmazenado) {
  if (userAvatar)   userAvatar.textContent  = nomeArmazenado.charAt(0).toUpperCase();
  if (userNomeSpan) userNomeSpan.textContent = nomeArmazenado;
}

// ── Navbar: links do dropdown ─────────────────────────────────────
// Detecta se a página está dentro de uma subpasta (cidadao/ ou prestador/)
const emSubpasta = /\/(cidadao|prestador)\//.test(window.location.pathname);
const prefixo    = emSubpasta ? "../" : "";

document.querySelectorAll('.dropdown a[role="menuitem"]').forEach(link => {
  const texto = link.textContent.trim();

  if (texto === "Meus dados") {
    link.href = `${prefixo}meus-dados.html`;

  } else if (texto === "Trocar senha") {
    link.remove();

  } else if (texto === "Sair") {
    link.addEventListener("click", e => {
      e.preventDefault();
      localStorage.clear();
      window.location.href = `${prefixo}login.html`;
    });
  }
});
