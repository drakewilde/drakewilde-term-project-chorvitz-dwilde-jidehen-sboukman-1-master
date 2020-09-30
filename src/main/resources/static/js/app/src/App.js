import React, { Component } from 'react';
import './App.css';
import Board from './components/Board'
import InfoPanel from './components/InfoPanel'
import NextLevelScreen from './components/NextLevelScreen'
import FailureScreen from './components/FailureScreen';
import IntroSequence from './components/IntroSequence';
import WinScreen from './components/WinScreen';

//The url to the address the backend is running on.
const ADDRESS= "ws://localhost:4567/game"

class App extends Component {

  /**
   * socket: the websocket to be used
   * socketLoaded: boolean representing whether the socket has loaded
   * game: a game object, sent over the JAVA backend.
   */
  constructor() {
    super();
    this.state = {
      socket: null,
      socketLoaded: false,
      game: null,
      gameRunning: false,
      betweenLevels: false,
      score: 0,
      traversableCount: 1,
      levelFailed: false,
      intro: true,
      mainMenu: false,
      gameOver:false,
    }
  }


 
  componentDidMount = () => {
    //When the component mounts, create a new websocket.
    let socket = new WebSocket(ADDRESS);

    //When the socket opens, set the socket loaded to true.
    socket.onopen = () => {
      this.setState({socketLoaded: true,});
    }

    //When the backend sends a message (the game object) set game.
    socket.onmessage = (msg) => {
      console.log(JSON.parse(msg.data));
      this.setState({game: JSON.parse(msg.data)});
    }

    //If an error occurs in socket unload the socket.
    socket.onerror = () => {
      this.setState({socketLoad:false,})
    } 

    //Finally, set the state of socket
    this.setState({socket})
  }

  getUpdate = () => {
    this.state.socket.send(JSON.stringify({
      type:'update',
    }))
  }
  /**
   * If a position of a node changes, report the value to the backend.
   */
  broadcastChange = (placeableDict) => {
    if (!this.state.betweenLevels){
      this.state.socket.send(JSON.stringify({
        type: 'position',
        placeables: placeableDict,
        })
      );
    }
  }

  broadcastSetLevel = (i) => {
    this.state.socket.send(JSON.stringify({
      type:'set_level',
      level: i,
    }))
    if (this.state.levelFailed){
      this.setState({levelFailed:false,score:0,})
    }
  }

  broadcastNextLevel = () => {
    if(this.state.game.level.id === this.state.game.levels.length - 1) {
      this.setState({gameOver:true,})
    }
    else{
      this.state.socket.send(JSON.stringify({
        type: 'next',
      }))
    }
  }

  broadcastResetLevel = () => {
    this.state.socket.send(JSON.stringify({
      type: 'reset',
    }))
    this.hideLevelScreen();
  }
  


  setBetweenLevels = () => {
    this.setState({betweenLevels:true}, () => {
      this.broadcastNextLevel();
      }
    )
  }
  
  hideLevelScreen = () => {
    this.setState({betweenLevels:false, levelFailed:false,
    score:0});
  }

  skipLevel = () => {
    this.setState({levelFailed:false,score:0,})
    this.broadcastNextLevel();
  }

  incrementScore = () => {
    this.setState({score: this.state.score + 1})
  }
  
  setTraversableCount = (traversableCount) => {
    this.setState({traversableCount});
  }

  setLevelFailed = () => {
    this.setState({levelFailed:true})
  }

  startGame = () => {
    this.setState({
      intro: false,
    }, ()=>{
      this.broadcastSetLevel(0);
    })
  }
  restart = () => {
    this.broadcastSetLevel(0);
    this.setState({gameOver:false, betweenLevels:false, score:0})
  }

  render() {
    if(this.state.intro) {
      return (
        <div className="wrap">
          <div className="panel">
            <IntroSequence startGame={this.startGame} />
          </div>
        </div>
      )
    }
    else{
    return ( 
      <div className="wrap">
        {this.state.game ? 
        <div className="panel">
          {this.state.gameOver ?
            <WinScreen restart={()=>this.restart(0)}/> :
          !this.state.betweenLevels && !this.state.levelFailed ? 
            <Board key={this.state.game.level.id}
                level={this.state.game.level}
                gameRunning = {this.state.gameRunning}
                broadcastChange={this.broadcastChange}
                getUpdate = {this.getUpdate}
                broadcastNewInfected={this.broadcastNewInfected}
                setBetweenLevels={this.setBetweenLevels}
                getBetweenLevels={this.state.betweenLevels}
                incrementScore = {this.incrementScore}
                score = {this.state.score}
                setTraversableCount={this.setTraversableCount}
                fraction={this.state.score/this.state.traversableCount}
                goal={this.state.game.level.percent}
                setLevelFailed={this.setLevelFailed}
              /> : this.state.levelFailed && !this.state.betweenLevels ?
                <FailureScreen 
                  fraction={this.state.score/this.state.traversableCount}
                  goal={this.state.game.level.percent}
                  resetLevel={this.broadcastSetLevel}
                  currLevelId = {this.state.game.level.id}
                  skip={this.skipLevel}
                /> :
              <NextLevelScreen 
                fraction={this.state.score/this.state.traversableCount}
                hideLevelScreen={this.hideLevelScreen}
                resetLevel={this.broadcastResetLevel} 
              />
            }
          <InfoPanel score={this.state.score}
            fraction={this.state.score/this.state.traversableCount}
            goal={this.state.game.level.percent} 
            game={this.state.game}
            betweenLevels={this.state.betweenLevels}
            next={this.broadcastNextLevel} />
        </div>
        : <div>Loading</div>}
      </div>
      );
  }}
}

export default App;
