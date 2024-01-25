import ReactDOM from 'react-dom/client';

function tick() {
  const element = (
      <div>
        <h2>현재 시간 : {new Date().toLocaleTimeString()}</h2>
      </div>
  );

  const root = ReactDOM.createRoot(document.getElementById('root'));
  root.render(element);
}

setInterval(tick, 1000);

export default tick;