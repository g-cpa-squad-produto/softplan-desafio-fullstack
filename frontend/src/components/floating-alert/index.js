import React, { Component } from 'react';
import Alert from 'react-s-alert';

// CSS
import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/stackslide.css';

class FloatingAlert extends Component {
  render() {
    return (
      <div className="FloatingAlert">
        <Alert stack={true} effect={'stackslide'} position={'bottom-right'} timeout={3000} />
      </div>
    );
  }
}

export default FloatingAlert;