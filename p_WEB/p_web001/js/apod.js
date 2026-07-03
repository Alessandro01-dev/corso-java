"use strict";

const placeholder = document.querySelector("#placeholder");
const dateInput = document.querySelector("#date");
const startDateInput = document.querySelector("#start-date");
const endDateInput = document.querySelector("#end-date");
const countInput = document.querySelector("#count");
const submitBtn = document.querySelector("#submit-btn");
const submitBtn2 = document.querySelector("#submit-btn2");
const submitBtn3 = document.querySelector("#submit-btn3");

const NASA_BASE_URL = `https://api.nasa.gov/planetary/apod?api_key=${NASA_API_KEY}`;

const getQueryData = (e) => {
  e.preventDefault();

  placeholder.innerHTML = "";

  const selectedDate = dateInput.value;
  const selectedStartDate = startDateInput.value;
  const selectedEndDate = endDateInput.value;
  const selectedCount = countInput.value;

  let QUERY_URL = NASA_BASE_URL;

  if (selectedDate) {
    QUERY_URL += `&date=${selectedDate}`;
  }

  if (selectedStartDate && endDateInput) {
    QUERY_URL += `&start_date=${selectedStartDate}&end_date=${selectedEndDate}`;
  }

  if (selectedCount) {
    QUERY_URL += `&count=${selectedCount}`;
  }

  getData(QUERY_URL).then((data) => {
    if (data.length > 0) {
      data.forEach((obj) => {
        createImage(obj);
        return;
      });
    } else {
      createImage(data);
    }
    console.log(data);
  });

  dateInput.value = null;
  startDateInput.value = null;
  endDateInput.value = null;
  countInput.value = null;
};

submitBtn.addEventListener("click", getQueryData);
submitBtn2.addEventListener("click", getQueryData);
submitBtn3.addEventListener("click", getQueryData);

const getData = async (url, options) => {
  try {
    const response = await fetch(url, options);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    const data = await response.json();

    return data;
  } catch (e) {
    console.log(e.message);
  }
};

const createImage = (data) => {
  const figure = document.createElement("figure");

  const img = document.createElement("img");
  img.setAttribute("src", data.hdurl);
  img.setAttribute("alt", data.title);

  const figCaption = document.createElement("figcaption");
  figCaption.innerText = data.title;

  placeholder.appendChild(figure);
  figure.append(img, figCaption);
};

getData(NASA_BASE_URL).then((data) => {
  createImage(data);
  console.log(data);
});
