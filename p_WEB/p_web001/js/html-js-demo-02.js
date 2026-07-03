"use strict";

const colors = ["blue", "orange", "red"];
let i = 0;

const element = document.querySelector("div.box");

function changeColor() {
  // element.innerHTML = `<h3>Centrato</h3>`;
  element.classList.remove(colors[i]);
  i = ++i % colors.length;
  element.classList.add(colors[i]);
}
