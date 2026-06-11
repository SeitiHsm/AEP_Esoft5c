const updateButton = document.getElementById('update-button');
const previewText = document.getElementById('preview-text');
const statusSelect = document.getElementById('status-atual');
const responsavelInput = document.getElementById('responsavel-tecnico');
const comentarioInput = document.getElementById('comentario');
const requestId = document.getElementById('request-id').value;

function formatDateTime(date) {
  const day = String(date.getDate()).padStart(2, '0');
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const year = date.getFullYear();
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');
  return {
    date: `${day}/${month}/${year}`,
    time: `${hours}:${minutes}:${seconds}`
  };
}

updateButton.addEventListener('click', () => {
  const status = statusSelect.value || 'Sem status';
  const responsavel = responsavelInput.value || 'Não informado';
  const comentario = comentarioInput.value || 'Nenhum comentário.';
  const now = new Date();
  const formatted = formatDateTime(now);

  previewText.value = `Status do protocolo (${requestId}) Atualizado:\nData: ${formatted.date} | ${formatted.time}\nStatus: ${status}\nResponsável: ${responsavel}\nComentário: ${comentario}`;
});

document.getElementById('save-button').addEventListener('click', () => {
  alert('Status salvo com sucesso!');
});

document.getElementById('cancel-button').addEventListener('click', () => {
  document.getElementById('status-form').reset();
  previewText.value = '';
});
