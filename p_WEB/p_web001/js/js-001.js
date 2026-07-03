/* prime prove con javascript */
// anche questo è un commento

console.log("Welcome by Javascript");

let a = "Hello World";
console.log(a);

a = 500;
console.log(a);

{
  var x = 100;
}

console.log(x);

let conta = 0;
for (let i = 0; i < 100; i++) {
  if (i % 2 === 0) {
    conta++;
  }
}
console.log("i numeri pari nei primi 100 interi sono " + conta);

let variabile = 100;
console.log(typeof variabile);

variabile = "sono una string";
console.log(typeof variabile);

let k = 0;
while (k < 100) {
  if (k % 2 === 0) {
    conta++;
  }
  k++;
}

let aa = 100;
let saa = "100";

// type coercion
if (aa === saa) {
  console.log("aa === saa");
} else {
  console.log("aa !== saa");
}
