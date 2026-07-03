"use strict";
/*

problema:igenializzare e generalizzare la costruzione di una tabella in js
cosa ci serve?
dati = array dei dati   
campi = array dei nomi dei campi del json
intestazioni = array delle intestazioni delle colonne
placeholder = id placeholder
idTable = id table

*/

function generateTable(
  intestazioni,
  dati,
  campi,
  placeholder = null,
  idTable = null,
) {
  //controllo di campi.length == intestazioni.length

  if (campi.length != intestazioni.length) {
    throw new Error("Campi ed Intestazioni hanno lunghezze differenti");
  }

  const table = document.createElement("table");
  let tr = document.createElement("tr");
  table.appendChild(tr);
  for (const intestazione of intestazioni) {
    const th = document.createElement("th");
    th.innerText = intestazione;
    tr.appendChild(th);
  }

  for (const dato of dati) {
    tr = document.createElement("tr");
    for (const campo of campi) {
      let td = document.createElement("td");
      td.innerText = dato[campo];
      tr.appendChild(td);
    }
    table.appendChild(tr);
  }

  if (idTable != null) {
    table.id = idTable;
  }
  if (placeholder != null) {
    placeholder.appendChild(table);
  }
  return table;
}
