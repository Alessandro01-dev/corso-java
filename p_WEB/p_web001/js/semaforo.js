"use strict";

const redLight = document.querySelector("#r");
const yellowLight = document.querySelector("#y");
const greenLight = document.querySelector("#g");

const trafficLightButton = document.querySelector("#i001");

let timeOut;

const handleLightsChange = () => {
  if (redLight.classList.contains("red")) {
    redLight.classList.remove("red");
    yellowLight.classList.remove("yellow");
    greenLight.classList.add("green");
  } else if (
    greenLight.classList.contains("green") &&
    !yellowLight.classList.contains("yellow")
  ) {
    yellowLight.classList.add("yellow");
  } else if (
    greenLight.classList.contains("green") &&
    yellowLight.classList.contains("yellow")
  ) {
    redLight.classList.add("red");
    yellowLight.classList.remove("yellow");
    greenLight.classList.remove("green");
  }

  timeOut = setTimeout(() => {
    handleLightsChange();
  }, 1000);
};

const handleButtonTextChange = () => {
  if (trafficLightButton.value === "start") {
    trafficLightButton.value = "stop";
  } else {
    trafficLightButton.value = "start";
  }
};

trafficLightButton.addEventListener("click", () => {
  if (trafficLightButton.value === "stop") {
    handleButtonTextChange();
    clearTimeout(timeOut);
    trafficLightButton.classList.replace("red", "green");
  } else if (trafficLightButton.value === "start") {
    handleLightsChange();
    handleButtonTextChange();
    trafficLightButton.classList.replace("green", "red");
  }
});
