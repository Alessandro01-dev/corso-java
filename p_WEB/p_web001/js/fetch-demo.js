"use strict";

const url = "http://127.0.0.1:5500/data/regioni.json";

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
