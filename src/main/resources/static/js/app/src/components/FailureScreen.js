import React, { Component } from 'react'

class FailureScreen extends Component {
    render() {
        return(
            <div className="next-level-wrap">
                <div>
                    <p>You have failed the level. </p>
                    <p>Score: {this.getAsPercent(this.props.fraction)}</p> 
                    <p>Target: {this.getAsPercent(this.props.goal)}</p> 
                    <div className="button-holder">   
                        <button className="try-again" onClick={() => this.props.resetLevel(this.props.currLevelId)}>
                            RESET
                        </button><br></br>
                        <button className="try-again" onClick={this.props.skip}>
                            SKIP
                        </button>
                    </div>
                </div>
            </div>
        )
    }

    getAsPercent = (dec) => {
        let fraction = `${dec}`
        return (
            dec === 0 ? '0%' : dec === 1 ? '100%' :
            fraction.length === 3 ? fraction.substring(2) + '0%' : dec < .1 ?
            fraction.substring(2,3) + '%' : fraction.substring(2,4) + '%');
    }
}

export default FailureScreen;