import React from 'react';

const styles = {
  wrapper: {
    margin: 8,
    padding: 8,
    display: "flex",
    flexDirection: "row",
    border: "1px solid grey",
    borderRadius: 16
  },
  messageText: {
    color: "black",
    fontSize: 16
  }
};

class Notification extends React.Component {

  // * 1
  componentDidMount() {
    console.log('==> componentDidMount : ', `${this.props.id}`)
  }

  // * 3
  componentDidUpdate(prevProps, prevState, snapshot) {
    console.log('==> componentDidUpdate : ', `${this.props.id}`)
  }

  // * 2
  componentWillUnmount() {
    console.log('==> componentWillUnmount : ', `${this.props.id}`)
  }

  componentDidCatch(error, errorInfo) {
    console.log('==> componentDidCatch : ', `${this.props.id}`)
  }


  constructor(props) {
    super(props);
    this.state = {};
  }
  render() {
    return (
        <div style={styles.wrapper}>
          <span style={styles.messageText}>
            {this.props.message}
          </span>
        </div>
    );
  }
}

export default Notification;