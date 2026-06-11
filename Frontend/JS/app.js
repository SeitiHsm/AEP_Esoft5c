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

const anonimatoSelect = document.querySelector("#anonimato");
const nomeSolicitanteInput = document.querySelector("#nome");

function atualizarNomeSolicitante() {
  if (!anonimatoSelect || !nomeSolicitanteInput) {
    return;
  }

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

const backLink = document.querySelector("[data-back-link]");

if (backLink) {
  backLink.addEventListener("click", (event) => {
    if (window.history.length > 1) {
      event.preventDefault();
      window.history.back();
    }
  });
}
