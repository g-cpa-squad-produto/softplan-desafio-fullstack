import React, {Component} from "react";

class Footer extends Component {
    render() {
        return (
            <div className="bg-light fixed-bottom border p-3 text-center">
                <p className="mb-1" style={{fontSize: 13}}>
                    <span className="border-right pr-2 mr-2">Antonio Rafael Ortega</span>
                    <a className="mr-2" target="_blank" rel="noopener noreferrer" href="https://www.linkedin.com/in/antonio-rafael-ortega/">LinkedIn</a>
                    <a className="ml-2" target="_blank" rel="noopener noreferrer" href="https://github.com/antrafa/antonio-rafael-ortega">GitHub</a>
                </p>
            </div>
        )
    }
}

export default Footer;
