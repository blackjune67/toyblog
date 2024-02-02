const scaleNames = {
  c: "섭씨",
  f: "화씨"
};


export function TemperatureInput({scale, temperature, onTemperatureChange}) {
  const handleChange = (e) => {
    onTemperatureChange(e.target.value)
  };
  return (
      <div>
        <fieldset>
          <legend>온도를 입력해주세요(단위:{scaleNames[scale]})</legend>
          <input value={temperature} onChange={handleChange}/>
        </fieldset>
      </div>
  );
}