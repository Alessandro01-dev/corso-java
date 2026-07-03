"use strict";

console.log(sommaTriplette(1, 4, 89));
console.log(sommaTriplette(4, 6));

// function definition
function somma(a, b) {
  return a + b;
}

function moltiplica(a, b) {
  return a * b;
}

function sommaTriplette(a, b, c = 0) {
  return a + b + c;
}

console.log(somma(4, 6));

console.log(somma("Ciao ", 6));

console.log(somma("Ciao ", "Mondo"));

console.log(moltiplica("Ciao ", "Mondo"));

function concatenaPersonalizzata(str1, str2) {
  if (typeof str1 === "string" && typeof str2 === "string") {
    return str1.concat(str2).toUpperCase();
  } else {
    return "";
  }
}

console.log(concatenaPersonalizzata("Ciao ", "Mondo"));
console.log(concatenaPersonalizzata("Ciao ", 5));
