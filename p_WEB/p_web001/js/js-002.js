"use strict";

// Array

const arr = [123, 56, 78, 98];

for (let i = 0; i < arr.length; i++) {
  const element = arr[i];
  console.log(element);
}
console.log();

for (let i in arr) {
  const element = arr[i];
  console.log(element);
}
console.log();

for (const element of arr) {
  console.log(element);
}
console.log();

// esercizio
// applicare le tecniche di scorrimento degli array in avanti e indietro apprese con java.

const arr2 = [10, 20, 30, 40, 50];

for (let i = 0; i < arr2.length; i++) {
  const e = arr2[i];
  console.log(e);
}
console.log();

for (let i = arr2.length - 1; i >= 0; i--) {
  const e = arr2[i];
  console.log(e);
}

// metodi degli array

arr2.push(60);
arr2.push(70);
arr2.push(80);

console.log(arr2);

arr2.unshift(0);
arr2.unshift(-10);

console.log(arr2);

const removedElement = arr2.pop();

console.log(arr2, removedElement);

arr2.shift();

console.log(arr2);

const arr3 = ["aaa", "zxc", "zyc", "bbb"];
console.log(arr3);

arr3.sort();
console.log(arr3);
