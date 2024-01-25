import React from 'react';

function Book(props) {
  return  (
      <div>
        <h1>{`이 책의 이름은 ${props.name}`}</h1>
        <h2>{`이 책의 이름은 ${props.numOfPages} 페이지로 이뤄져 있다`}</h2>
      </div>
  );
}

export default Book;