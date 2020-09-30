import React, { Component } from 'react'

class NextLevelScreen extends Component {
    render() {
        return(
            <div className="next-level-wrap">
                <div>
                    <p>Congrats! You have completed the level. </p>
                    <p>Score: {this.getAsPercent(this.props.fraction)}</p> 
                    <div className="button-holder">   
                        <button className="next" onClick={this.props.hideLevelScreen}>
                            NEXT 
                        </button><br></br>
                        <button className="reset" onClick={this.props.resetLevel}>
                            RESET
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

export default NextLevelScreen;