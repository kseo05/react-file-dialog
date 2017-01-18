'use strict';

const OFF = 0;
const WARNING = 1;
const ERROR = 2;

module.exports = {
  "extends" : "eslint:recommended",
  "env" : {
    "browser" : true,
    "es6" : true,
    "node" : true,
    "jquery" : true
  },
  "parserOptions" : {
    "ecmaVersion" : 6,
    "sourceType" : "module",
    "ecmaFeatures" : {
      "jsx" : true
    }
  },
  "rules" : {
    "no-console" : OFF,
    "no-mixed-spaces-and-tabs" : ERROR,
    "quotes" : [ERROR, "double", "avoid-escape"],
    "react/jsx-uses-vars" : ERROR
  },
  "globals": {
    "console" : true
  },
  "plugins": [
    "react"
  ]
};
