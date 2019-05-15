import React, { Component } from 'react';
import logo from './logo.png';
import './App.css';
import Header from './page-parts/Header';
import MainSearch from './page-parts/MainSearch';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      records: []
    }
  }
  search(res) {
    console.log(this);
    this.setState({records: res});
    console.log(res);
    // this.setState({records:res});
  }

  render() {
    
    // this.setState({records:[]});
    return (
      <div className="App">
        <Header logo={logo}></Header>
        <MainSearch records={this.state.records} search={this.search.bind(this)}></MainSearch>
      </div>
    );
  }
}

export default App;
