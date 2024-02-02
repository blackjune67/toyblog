export const Clock = () => {
  return (
      <div>
        <h1>안녕, 리액트!</h1>
        <h2>현재 시간 : {new Date().toLocaleTimeString()}</h2>
      </div>
  )
}