import {useState} from "react";

export function NameForm() {
  const [value, setValue] = useState('');

  const handelChange = (event) => {
    setValue(event.target.value);
  }

  const handelSubmit = (event) => {
    alert('insert name : ' + value);
    event.preventDefault();
  }

  return (
      <div>
        <form onSubmit={handelSubmit}>
          {/*<label>
            이름:
            <input type="text" value={value} onChange={handelChange}/>
          </label>*/}
          <select name="" id="" multiple={true} value={['B', 'C']} onChange={handelChange}>
            {/*<option value="apple">사과</option>
            <option value="banana">바나나</option>
            <option value="grape">포도</option>
            <option value="watermelon">수박</option>*/}
          </select>
          <button type="submit">제출</button>
        </form>
      </div>
  )
}