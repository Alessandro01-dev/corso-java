"use strict";
// console.log(sottrazione(2, 5)); questa dà errore

const somma = (a, b) => {
  return a + b;
};

const sottrazione = (a, b) => {
  return a - b;
};

const moltiplicazione = (a, b) => {
  return a * b;
};

const divisione = (a, b) => {
  return a / b;
};

console.log(somma(2, 5));
console.log(sottrazione(2, 5));
console.log(divisione);

let risultato = calcolatrice(somma, 10, 20);
console.log(risultato);
risultato = calcolatrice(sottrazione, 10, 20);
console.log(risultato);
risultato = calcolatrice(moltiplicazione, 10, 20);
console.log(risultato);
risultato = calcolatrice(divisione, 10, 20);
console.log(risultato);

function calcolatrice(f, op1, op2) {
  return f(op1, op2);
}

// esercizio
// realizzare un convertitore di misura

console.log("Convertitore unità di misura");

const metersToKilometers = (m) => {
  const km = m / 1000;
  return km;
};

const litersToHectoliters = (lt) => {
  const hl = lt / 100;
  return hl;
};

const feetToCentimeters = (ft) => {
  const cm = ft * 30.48;
  return cm;
};

const converter = (foo, x) => {
  const result = foo(x);
  return x + " => " + result;
};

console.log(converter(metersToKilometers, 4000));
console.log(converter(litersToHectoliters, 50));
console.log(converter(feetToCentimeters, 1));

// higher-order functions
function randomInt(min, max) {
  return Math.floor(Math.random() * (max - min)) + min;
}

for (let i = 0; i < 10; i++) {
  console.log(randomInt(0, 100));
}

function getRandomFunction() {
  switch (randomInt(0, 4)) {
    case 0:
      return somma;
      break;
    case 1:
      return sottrazione;
      break;
    case 2:
      return moltiplicazione;
      break;
    default:
      return divisione;
  }
}

for (let i = 0; i < 10; i++) {
  let a = randomInt(0, 100);
  let b = randomInt(0, 100);
  const f = getRandomFunction();
  console.log("funzione applicata: ", f.toString());
  console.log("a=" + a + "b=" + b + "risultato:" + f(a, b));
}
