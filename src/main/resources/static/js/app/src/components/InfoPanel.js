import React, { Component } from 'react';
import Dot from "./Dot"
import { BsFillCaretRightFill } from 'react-icons/bs'
class InfoPanel extends Component {

    render() {
        return (
            <div className="info-panel">
                <h1>PANDEMIC</h1>
                <h2>Level {this.props.game.level.id + 1} <BsFillCaretRightFill style={{height:20,width:20,}} onClick={this.props.next}/> </h2>
                <div className="info-text">
                    <p className="info-dot-label"> Uninfected:
                    </p>
                    <div className="info-dot-holder">
                        <Dot color="#34A4FF" clickable={false}/>
                    </div><br></br>
                    <p className="info-dot-label">Infected:
                    </p>
                    <div className="info-dot-holder">
                        <Dot color="#E53935" clickable={false} />
                    </div><br></br>
                    <p className="distance-label">SOCIAL DISTANCE</p>
                    <div className="progress-bar-wrap">
                        <div className="progress-bar" style={{width: !this.props.betweenLevels ? `${this.props.fraction*100}%` : 0}}></div>
                        <div className="marker"style={{marginLeft:  `${this.props.goal*100}%`, display: this.props.betweenLevels ? 'none' : 'block'}}></div>
                        <p style={{marginLeft:this.getAsPercent(this.props.goal), display: this.props.betweenLevels ? 'none' : 'block'}}>{this.getAsPercent(this.props.goal)}</p>
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

export default InfoPanel;