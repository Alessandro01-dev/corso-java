"use strict";

const obj = {}; // oggetto vuoto

obj.saluto1 = "Hello World";
obj["saluto2"] = "Ciao Mondo";
obj.saluto3 = "Hola Mundo";

console.log(obj);

console.log(obj.saluto3);

console.log(obj["saluto1"]);

const obj2 = {
  dato1: "valore1",
  dato2: 200,
  dato3: "altra string",
  toString: function () {
    return this.dato1 + " " + this.dato2 + " " + this.dato3;
  },
};

console.log(obj2.dato2);
console.log("*** " + obj2 + " ***");
console.log(obj2);

const strSerializzata = JSON.stringify(obj2);
console.log(strSerializzata);

const objDeserializzato = JSON.parse(strSerializzata);
console.log(objDeserializzato);
