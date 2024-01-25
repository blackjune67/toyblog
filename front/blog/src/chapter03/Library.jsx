import React from "react";
import Book from "./Book";
function Library(props) {
  return (
      <div>
        <Book name="처음 만난 리액트" numOfPages={500}/>
      </div>
  );
}

export default Library;