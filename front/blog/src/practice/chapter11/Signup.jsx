import {useState} from "react";

export function Signup() {
  const [name, setSighup] = useState('');
  const [gender, setGender] = useState('');

  const handleChangeName = (event) => {
    setSighup(event.target.value);
  }

  const handleChangeGender = (e) => {
    setGender(e.target.value);
  }

  const handleSubmit = (event) => {
    // * ES LINT + 쓰지말아라
    alert(`Name : ${name} Gender : ${gender}`)
    event.preventDefault();
  }

  return (
      <div>
        <form action="" onSubmit={handleSubmit}>
          로그인:
          <label htmlFor="">
            <input type="text"
                   value={name}
                   onChange={handleChangeName}
            />
          </label>

          <label htmlFor="">
            성별:
            {/*
              제어할 때는 value를 사용하고
              단순히 읽을 때는 defaultValue를 사용한다
            */}
            <select name="" id="" value={gender} onChange={handleChangeGender}>
              <option value="남자">남자</option>
              <option value="여자">여자</option>
            </select>
          </label>

          <button
              type={"submit"}
          >제출
          </button>
        </form>
      </div>
  );
}