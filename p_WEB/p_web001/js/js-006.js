"use strict";

const jsonString = `{
  "regioni": [
    {
      "COD_REG": "01",
      "COD_RIP": "1",
      "DEN_REG": "Piemonte",
      "TIPO_REG": "1",
      "DESC_TIPO_REG": "Regione a Statuto ordinario",
      "COD_REG_FISCALE": "80087670016",
      "COD_NUTS2_2024": "ITC1"
    },
    {
      "COD_REG": "02",
      "COD_RIP": "1",
      "DEN_REG": "Valle d'Aosta\/Vallée d'Aoste",
      "TIPO_REG": "2",
      "DESC_TIPO_REG": "Regione a Statuto speciale",
      "COD_REG_FISCALE": "80002270074",
      "COD_NUTS2_2024": "ITC2"
    },
    {
      "COD_REG": "03",
      "COD_RIP": "1",
      "DEN_REG": "Lombardia",
      "TIPO_REG": "1",
      "DESC_TIPO_REG": "Regione a Statuto ordinario",
      "COD_REG_FISCALE": "80050050154",
      "COD_NUTS2_2024": "ITC4"
    }
  ]
}`;

console.log(jsonString);

const regioni = JSON.parse(jsonString);

console.log(regioni.regioni);

for (const regione of regioni.regioni) {
  console.log(regione.DEN_REG);
}

console.log(JSON.stringify(regioni.regioni));

const mainContainer = document.querySelector("#main-container");
const mainTable = document.createElement("table");
mainContainer.appendChild(mainTable);

const createHeaders = (h1, h2, h3) => {
  const tr = document.createElement("tr");
  mainTable.appendChild(tr);

  const th1 = document.createElement("th");
  th1.innerText = h1;

  const th2 = document.createElement("th");
  th2.innerText = h2;

  const th3 = document.createElement("th");
  th3.innerText = h3;

  tr.append(th1, th2, th3);
};

const createRows = (arrayOfData) => {
  for (const element of arrayOfData) {
    const row = document.createElement("tr");
    mainTable.appendChild(row);

    const codice = document.createElement("td");
    codice.innerText = element.COD_REG;

    const denominazione = document.createElement("td");
    denominazione.innerText = element.DEN_REG;

    const descrizione = document.createElement("td");
    descrizione.innerText = element.DESC_TIPO_REG;

    row.append(codice, denominazione, descrizione);
  }
};

createHeaders("Cod", "Regione", "Tipo Regione");
createRows(regioni.regioni);
