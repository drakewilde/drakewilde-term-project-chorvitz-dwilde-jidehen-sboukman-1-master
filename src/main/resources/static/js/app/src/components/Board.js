import React, { Component } from 'react';
import Square from './Square'
import NextLevelScreen from './NextLevelScreen'
class Board extends Component {

    /**
     * movePeriod: whether or not the game is in the 'move' phase
     * move: whether or not a move has been initiated
     * selectedDotLocation: the location of the currently selected dot
     * placeables: a list of objects to place on the board
     * infectedLocation: a array listing the coordinates of infected nodes
     * infectedPath: the shortest-path to the nearest (once kdtree is working) node in form of array of coordinates
     * infectedSquares: a 2d array representing the board and the squares that are infected
     */
    constructor(props){
        super(props);
        this.state = {
            movePeriod: true,
            move: false, 
            moves: this.props.level.numMoves,
            selectedDotLocation: Array,
            infectedPaths: this.props.level.allPathsFromBoard,
            infectedSquares: this.initiateInfected2dArray(),
            interval: null,
            timer: 250,
            levelEnded: false,
        }
    }

    render() {
        return(
            <div className="board-and-subtext">
                {this.state.moves !== 0 ? <p>Moves left: {this.state.moves}</p> : <p>Score: {this.getScoreAsString()}</p>}
                <div className="board-wrap">
                    <div className="board" style={{gridTemplateColumns:  this.getGridTemplateStyle(), gridTemplateRows: this.getGridTemplateStyle()}}>
                        {this.getGridSqaures()}
                    </div>
                </div>
            </div>
        )
    }

    componentDidMount = () => {
        this.countTraversables();
        this.initiateInfected2dArray();
        console.log('hi')
        let interval = setInterval(() => {
            this.infect();
        }, this.state.timer)
        this.setState({interval});
    }

    getScoreAsString = () => {
        let fraction = `${this.props.fraction}`
        return this.props.fraction === 0 ? '0%' : this.props.fraction === 1 ? '100%' :
             fraction.length === 3 ? fraction.substring(2) + '0%' : this.props.fraction < .1 ?
             fraction.substring(2,3) + '%' : fraction.substring(2,4) + '%';
    }

    initiateInfected2dArray = () => {
        var arr = [...Array(this.props.level.dimension)].map(e => Array(this.props.level.dimension));
        for(let i = 0; i < this.props.level.dimension; i++) {
            for (let j = 0; j < this.props.level.dimension; j++) {
                arr[i][j] = false;
            }
        }
        return arr;
    }

    getGridTemplateStyle = () => {
        let output = "";
        for (let i = 0; i < parseInt(this.props.level.dimension); i++) {
            output = output + "1fr ";
        }
        return output;
    }

    infect = () => {

        if (!this.state.movePeriod) {
            let pathsDict = this.props.level.allPathsFromBoard;
            let infectedSquaresCopy = this.state.infectedSquares;
            Object.keys(pathsDict).forEach( key => {
            if (pathsDict[key] && pathsDict[key].length > 0) {
                let currCol = pathsDict[key][0].xVal;
                let currRow = pathsDict[key][0].yVal;
                if (infectedSquaresCopy[currCol][currRow] === false){
                    this.props.incrementScore();
                    infectedSquaresCopy[currCol][currRow] = true;
                }
            }
            })
            this.setState({infectedSquares: infectedSquaresCopy});
            this.props.getUpdate();
            if (this.state.levelEnded){
                clearInterval(this.state.interval);
                if (this.props.fraction < this.props.goal) {
                    this.props.setLevelFailed();
                }
                else {
                    this.props.setBetweenLevels();
                }
            }
            if (this.props.level.uninfected.length === 0){
                this.setState({levelEnded:true});
            }
        }
    }

    getPlaceableIdByCoords = (col, row) => {
        for (let placeable of this.props.level.placeables){
            if (placeable.col === col && placeable.row === row) {
                return placeable.id;
            }
        }
        return "";
    }

    initiateMove = (dotCoords) => {
        let move;
        if (this.state.selectedDotLocation[0] === dotCoords[0] && this.state.selectedDotLocation[1] === dotCoords[1]){
            move = false;
            this.setState( {
                move,
                selectedDotLocation: [],
            });
        } else {
            move = true;
            this.setState( { 
                move,
                selectedDotLocation: dotCoords,
            })
        }
    }

    completeMove = (newCoords) => {
        let placeablesCopy = this.props.level.placeables;
        let positions = {};
        for (let placeable of placeablesCopy){
            if (this.state.selectedDotLocation[0] === placeable.col && this.state.selectedDotLocation[1] === placeable.row) {
                placeable.col = newCoords[0];
                placeable.row = newCoords[1];
            }
            positions[placeable.id] = {
                id: placeable.id,
                col: placeable.col,
                row: placeable.row,
            }
        };
        this.setState({
            // placaeables: placeablesCopy,
            move: false,
            selectedDotLocation: [],
            moves:this.state.moves - 1,
        }, () => {
            if (this.state.moves === 0 ) {
                this.setState({movePeriod:false,}, () => {
                    this.props.broadcastChange(positions);
                })
            }
        })
    }

    checkIfSquareContainsInfected = (col, row) => {
        for (let infected of this.props.level.infected){
            if (infected.col === col && infected.row === row){
                return true;
            } 
        }
        return false;
    }

    getGridSqaures = () => {
        let squares = [];
        let key = 0;
        for (let row = 0; row < parseInt(this.props.level.dimension); row++) {
            for (let col = 0; col < parseInt(this.props.level.dimension); col++) {
                let item = this.checkForPlaceable(col, row);
                let isSelected = false;
                let containsInfected = this.checkIfSquareContainsInfected(col, row);
                let inInfectedPath = false;
                if (this.state.selectedDotLocation[0] === col && this.state.selectedDotLocation[1] === row){
                    isSelected = true;
                } 
                if (this.state.infectedSquares[col][row] === true){
                    inInfectedPath = true;
                }
                squares.push(
                <Square 
                    key={key}
                    row={row}
                    col={col}
                    move={this.state.move}
                    isMovePeriod={this.state.movePeriod}
                    initiateMove={this.initiateMove}
                    completeMove={this.completeMove}
                    isSelected = {isSelected}
                    inInfectedPath = {inInfectedPath}
                    containsInfectedPerson = {containsInfected}
                    getSize = {this.getSquareSize}
                    placeable={item}>
                </Square>);
                key++;
            }
        }
        return squares;
    }

    countTraversables = () => {
        let barrierCount = 0;
        for (let row = 0; row < parseInt(this.props.level.dimension); row++) {
            for (let col = 0; col < parseInt(this.props.level.dimension); col++) {
                let item = this.checkForPlaceable(col, row);
                if (item && item.type === "Barrier") {
                    barrierCount++;
                }
            }
        }
        let traversableCount = Math.pow(this.props.level.dimension,2) - barrierCount;
        this.props.setTraversableCount(traversableCount);
    }

    checkForPlaceable = (col, row) => {
        for (let placeable of this.props.level.placeables){
            if (col === placeable.col && row === placeable.row){
                return placeable;
            }
        }
        return null;
    }

}

export default Board;