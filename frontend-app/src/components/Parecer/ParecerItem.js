import React, { Component } from "react";

class ParecerItem extends Component {
  render() {
    const { project } = this.props;

    return (
      <div className="container">
        <div className="card card-body bg-light mb-3">
          <div className="row">
            <div className="col-2">
              <span className="mx-auto">{project.id}</span>
            </div>
            <div className="col-lg-6 col-md-4 col-8">
              <p>{project.texto}</p>
              <p>{project.created_at}</p>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default ParecerItem;
