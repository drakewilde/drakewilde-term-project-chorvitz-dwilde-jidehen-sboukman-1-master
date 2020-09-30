import React, { Component } from 'react'

class WinScreen extends Component {
    render() {
        return (
            <div className="next-level-wrap">
                <div>
                    <h2>Congrats! The pandemic is over.</h2>
                    <div className="button-holder">   
                        <button className="next" onClick={this.props.restart}>
                            RESTART 
                        </button><br></br>
                    </div>
                </div>
 
            </div>
        )
    }
}

export default WinScreen;