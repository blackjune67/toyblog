import {useState} from "react";
import {BoilingVerdict} from "./BoilingVerdict";
import {TemperatureInput} from "./TemperatureInput";
import templateFactory from "bootstrap/js/src/util/template-factory";

function toCelsius(fahrenheit) {
  return (fahrenheit - 32) * 5 / 9;
}

function toFahrenheit(celsius) {
  return (celsius * 9 / 5) + 32;
}

function tryConvert(temperature, convert) {
  const input = parseFloat(temperature);
  if (Number.isNaN(input)) {
    return '';
  }
  const output = convert(input);
  const rounded = Math.round(output * 1000) / 1000;
  return rounded.toString();
}

export function Calculator() {
  const [temperature, setTemperature] = useState('');
  const [scale, setScale] = useState('');

  const handleCelsiusChange = (temperature) => {
    setTemperature(temperature);
    setScale('c');
  }
  const handleFahrenheitChange = (temperature) => {
    setTemperature(temperature);
    setScale('f');
  }

  const celsius = scale === 'f' ? tryConvert(temperature, toCelsius) : temperature;
  const fahrenheit = scale === 'c' ? tryConvert(temperature, toFahrenheit) : temperature;

  /*const handleChange = (e) => {
    setTemperature(e.target.value);
  }*/

  return (
      <div>
        <TemperatureInput
            scale={"c"}
            temperature={celsius}
            onTemperatureChange={handleCelsiusChange}
        />
        <TemperatureInput
            scale={"f"}
            temperature={fahrenheit}
            onTemperatureChange={handleFahrenheitChange}
        />
        {/*<legend>섭씨 온도를 입력하세요</legend>
        <input type="text"
               value={temperature}
               onChange={handleChange}
        />*/}

        <BoilingVerdict
            celsius={parseFloat(temperature)}
        />

      </div>
  );
}