import React, { Component } from 'react';
import logo from './logo.png';
import './App.css';
import Header from './page-parts/Header';
import MainSearch from './page-parts/MainSearch';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Header logo={logo}></Header>
        <MainSearch></MainSearch>
      </div>
    );
  }
}

export default App;
