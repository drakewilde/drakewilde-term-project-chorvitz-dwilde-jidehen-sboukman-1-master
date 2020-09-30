import React, { Component } from 'react';
import Dot from "./Dot"


class Square extends Component {

    render() {
        return (
            <div 
                style={{backgroundColor: (this.props.containsInfectedPerson || this.props.inInfectedPath) ? '#FFEBEE' : null}}
                onClick = {this.completeMove}
                className={this.props.move && !this.props.placeable ? 'selected-square' : 'square'}
                > 
                {this.props.placeable !== null && this.props.placeable.type === "Person" ? 
                    <Dot 
                        isSelected={this.props.isSelected}
                        color={!this.props.containsInfectedPerson ? '#34A4FF' : '#E53935'}
                        location = {[this.props.col, this.props.row]}
                        initiateMove = {this.props.initiateMove}
                        clickable={this.props.isMovePeriod ? true : false}
                        infected = {this.props.containsInfectedPerson}
                    /> : null}
                    {this.props.placeable !== null && this.props.placeable.type === "Barrier" ? 
                    <div 
                        className="barrier"
                    /> : null}
            </div>
        )
    }

    completeMove = () => {
        if(!this.props.placeable && this.props.move){
            this.props.completeMove([this.props.col, this.props.row]);
        }
    }

}

export default Square;