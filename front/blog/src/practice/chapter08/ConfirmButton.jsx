import React from 'react';

class ConfirmButton extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      isConfirmed: false,
    }

    // this.handleConfirm = this.handleConfirm.bind(this);
  }

  // * 함수
  /*handleConfirm() {
    this.setState((preState) => ({
      isConfirmed: !preState.isConfirmed,
    }));
  }*/

  // * arrow FN
  handleConfirm = () => {
    this.setState((preState) => ({
          isConfirmed: !preState.isConfirmed,
        }
    ));
  }

  render() {
    return (
        <button onClick={this.handleConfirm} disabled={this.state.isConfirmed}>
          {this.state.isConfirmed ? "확인됨" : "확인하기"}
        </button>
    );
  }
}


export default ConfirmButton;