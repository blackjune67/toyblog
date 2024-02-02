import {useState} from "react";
import {BoilingVerdict} from "./BoilingVerdict";
import {TemperatureInput} from "./TemperatureInput";

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

  // * 값이 변환됐을 때 업데이트하기 위한 함수
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
            scale={"c"} /*단위값*/
            temperature={celsius} /*온도값*/
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