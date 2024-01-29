import {useState} from "react";

export function Reservation() {
  const [haveBreakfast, setHaveBreakfast] = useState(true);
  const [numberOfGuest, setNumberOfGuest] = useState(2);

  const handleSubmit = (e) => {
    alert(` ${haveBreakfast}, ${numberOfGuest}`)
    e.preventDefault();
  };


  return (
      <div>
        <form action="" onSubmit={handleSubmit}>
          <label htmlFor="">
            아침식사 여부:
            <input type="checkbox"
              checked={haveBreakfast}
                   onChange={(e) => {
                     setHaveBreakfast(e.target.checked);
                   }}
            />
          </label>
          <label htmlFor="">
            방문객 수:
            <input type="number"
              value={numberOfGuest}
                   onChange={(e) => {
                     setNumberOfGuest(e.target.value);
                   }}
            />
          </label>
          <button type={"submit"}>제출</button>
        </form>
      </div>
  )
}