"use strict";

// elements
const paragraph = document.getElementById("p001");
const toUpperCaseButton = document.getElementById("i001");
const toLowerCaseButton = document.getElementById("i002");
const toggleCaseButton = document.getElementById("i003");
const resetButton = document.getElementById("i004");

// states
let toggleCaseState = false;
const originalMessage = paragraph.innerText;

// handlers
function handleToUppercase() {
  let str = paragraph.innerText;
  str = str.toUpperCase();
  paragraph.innerText = str;
}

function handleToLowercase() {
  let str = paragraph.innerText;
  str = str.toLowerCase();
  paragraph.innerText = str;
}

function toggleCase() {
  let str = paragraph.innerText;
  str = toggleCaseState ? str.toLowerCase() : str.toUpperCase();
  paragraph.innerText = str;
  toggleCaseState = !toggleCaseState;
}

function resetMessage() {
  paragraph.innerText = originalMessage;
}

// event listeners
toUpperCaseButton.addEventListener("click", handleToUppercase);
toLowerCaseButton.addEventListener("click", handleToLowercase);
toggleCaseButton.addEventListener("click", toggleCase);
resetButton.addEventListener("click", resetMessage);
