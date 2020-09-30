import React, { Component } from 'react';



class Dot extends Component {
    render () {
        const styles = {
            backgroundColor: this.props.color, 
            border: (this.props.isSelected ? '2px solid #FFB74D' : 'none'),
            cursor: (!this.props.clickable || this.props.infected ? null : 'pointer'),
        }
        return (
            <div
                onClick={this.selectDot}
                className="dot"
                style={styles}
            />
        )
    }

    selectDot = () => {
        if (this.props.clickable && !this.props.infected) {
            this.props.initiateMove(this.props.location);
        }
    }
}

export default Dot;
