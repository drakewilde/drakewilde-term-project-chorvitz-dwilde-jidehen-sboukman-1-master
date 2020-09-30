import React, { Component } from 'react'
const text = ["Look at these nodes.", "Doing as nodes do.", "Talking, living, loving...", 
"Until one day...", "a node got sick.", "And then another.","All of a sudden there was a...."]

class IntroSequence extends Component {
    constructor(){
        super();
        this.state={
            frame:0,
        }
    }

    render() {
        return(
            <div className="intro-container">
            <div className="intro-wrap" style={{height: this.state.frame < text.length ? '18em' : 'auto'}}>
                <div className="intro-text">
                    {this.state.frame > text.length-1 ? (
                    <div className="home-screen">
                        <h1>PANDEMIC</h1>       
                        <p>Save your people from an incoming virus!</p>
                        <div className="menu-node-wrap">
                            <div className="intro-node-infected"></div>
                            <div className="intro-node-infected" style={{top:30}}/>
                            <div className="intro-node-infected" style={{left:30, top: 0}} ></div>
                            <div className="intro-node-infected" style={{left:30, top: 30}}  />
                            <div className="intro-node-infected" style={{left:60}}  ></div>
                            <div className="intro-node-infected" style={{left:60, top:30}} ></div>
                            <div className="intro-node-infected" style={{left:90, top: 30}} ></div>
                            <div className="intro-node-infected" style={{left:90}} ></div>
                        </div>
                        <button className="start" onClick={this.props.startGame}>START</button>
                    </div>
                    ) :
                    <p>{text[this.state.frame]}</p>
                    }
               </div>
                <div className="intro-nodes-wrap">

                    {this.state.frame === 0 ? (

                        <div>
                            <div className="intro-node-uninfected"/>
                            <div className="intro-node-uninfected" style={{top:30}}/>
                            <div className="intro-node-uninfected" style={{left:30}}><p></p></div>
                            <div className="intro-node-uninfected" style={{left:30, top: 30}}/>

                            <div className="intro-node-uninfected" style={{left:230}}/>
                            <div className="intro-node-uninfected" style={{left:200}}/>
                            <div className="intro-node-uninfected" style={{left:230, top:30}}><p></p></div>
                            <div className="intro-node-uninfected" style={{left:200, top: 30}}/>
                        </div>
                    )

                    : this.state.frame === 1 ? (

                        <div>
                            <div className="intro-node-uninfected"/>
                            <div className="intro-node-uninfected" style={{top:30}}/>
                            <div className="intro-node-uninfected" id="node-to-right" style={{left:30}}></div>
                            <div className="intro-node-uninfected" style={{left:30, top: 30}}/>

                            <div className="intro-node-uninfected" style={{left:230}}/>
                            <div className="intro-node-uninfected" style={{left:200}}/>
                            <div className="intro-node-uninfected" style={{left:230, top:30}}></div>
                            <div className="intro-node-uninfected" id="node-to-left" style={{left:200, top: 30}}></div>
                        </div>
                    )

                    : this.state.frame === 2 ? (

                        <div>
                            <div className="intro-node-uninfected"><p id="hey-node-2">hey</p></div>
                            <div className="intro-node-uninfected" style={{top:30}}/>
                            <div className="intro-node-uninfected" style={{left:200, top: 30}}><p id="hi-node">hi friends</p></div>
                            <div className="intro-node-uninfected" style={{left:30, top: 30}}/>

                            <div className="intro-node-uninfected" style={{left:230}}></div>
                            <div className="intro-node-uninfected" style={{left:200}}/>
                            <div className="intro-node-uninfected" style={{left:230, top:30}}><p id="nodeiced-node">oh hey didn't <span style={{fontStyle:'italic'}}>nodeice</span> you there</p></div>
                            <div className="intro-node-uninfected" style={{left:30, top: 0}}><p id="hey-node">hey guys!</p></div>
                        </div>
                    )


                    : this.state.frame === 3 ? (

                        <div>
                            <div className="intro-node-uninfected"></div>
                            <div className="intro-node-uninfected" style={{top:30}}/>
                            <div className="intro-node-uninfected" style={{left:200, top: 30}}></div>
                            <div className="intro-node-uninfected" style={{left:30, top: 30}}/>

                            <div className="intro-node-uninfected" style={{left:230}}></div>
                            <div className="intro-node-uninfected" style={{left:200}}/>
                            <div className="intro-node-uninfected" style={{left:230, top:30}}></div>
                            <div className="intro-node-uninfected" style={{left:30, top: 0}}></div>
                        </div>
                    )

                    : this.state.frame === 4 ? (

                        <div>
                            <div className="intro-node-uninfected"></div>
                            <div className="intro-node-uninfected" style={{top:30}}/>
                            <div className="intro-node-uninfected" style={{left:200, top: 30}}></div>
                            <div className="intro-node-uninfected" style={{left:30, top: 30}}/>

                            <div className="intro-node-uninfected" style={{left:230}}></div>
                            <div className="intro-node-uninfected" style={{left:200}} id="cough-node-1"><p id="cough">*cough*</p></div>
                            <div className="intro-node-uninfected" style={{left:230, top:30}}></div>
                            <div className="intro-node-uninfected" style={{left:30, top: 0}}></div>
                        </div>
                    )
                    : this.state.frame === 5 ? (

                        <div>
                            <div className="intro-node-uninfected"></div>
                            <div className="intro-node-uninfected" style={{top:30}}/>
                            <div className="intro-node-uninfected" style={{left:30, top: 0}} ></div>
                            <div className="intro-node-uninfected" style={{left:30, top: 30}}  />
                            <div id="sick-wrap">
                                <div className="intro-node-uninfected" style={{left:230}}  id="sick-node-4"></div>
                                <div className="intro-node-uninfected" style={{left:200}} id="sick-node-1"></div>
                                <div className="intro-node-uninfected" style={{left:230, top:30}} id="sick-node-2"></div>
                                <div className="intro-node-uninfected" style={{left:200, top: 30}} id="sick-node-3"></div>
                            </div>
                        </div>
                    )

                    : this.state.frame === 6 ? (

                        <div>
                            <div id="sick-wrap-3">
                                <div className="intro-node-uninfected"></div>
                                <div className="intro-node-uninfected" style={{top:30}}/>
                            </div>
                            <div id="sick-wrap-4">
                                <div className="intro-node-uninfected" style={{left:30, top: 0}} ></div>
                                <div className="intro-node-uninfected" style={{left:30, top: 30}}  />
                            </div>
                            <div id="sick-wrap-1">
                                <div className="intro-node-uninfected" style={{left:230}}  ></div>
                                <div className="intro-node-uninfected" style={{left:230, top:30}} ></div>
                            </div>
                            <div id="sick-wrap-2">
                                <div className="intro-node-uninfected" style={{left:200, top: 30}} ></div>
                                <div className="intro-node-uninfected" style={{left:200}} ></div>
                            </div>
                        </div>
                    )

                    : null}
                </div>
            </div>
            {this.state.frame < text.length ? <p className="skip-link" onClick={this.skipFrames}>skip</p> : null}
            </div>

        );
    }

    componentDidMount = () => {
        setInterval( () => {
            this.setState({frame:this.state.frame+1})
        }, 3000)
    }

    skipFrames = () => {
        this.setState({
            frame: text.length,
        })
    }
}

export default IntroSequence;