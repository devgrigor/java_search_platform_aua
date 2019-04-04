import React, { Component } from 'react';
import { createStore } from 'redux'
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import './Header.css';

class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
            logo: props.logo
        }
    }

    // example for reducx
    counter(state = 0, action) {
      switch(action.type) {
        case 'INCREMENT':
        return state + 1;

        case 'DECREMENT':
        return state - 1;

        default:
        return state;
      }
    }

    render() {
      // Redux is used for data manipulation asynchronousely and to provide single source of truth
      let store = createStore(this.counter);

      store.subscribe(() => {
        console.log(store.getState());
      });

      store.dispatch({ type: 'INCREMENT' })
      store.dispatch({ type: 'INCREMENT' })
      store.dispatch({ type: 'DECREMENT' })

      return (
        <div>
          <AppBar position="static">
              <Toolbar className="toolbar">
                <img src={this.state.logo} alt="Logo"  />
                <Typography variant="title" color="inherit">
                  GALA Search                
                </Typography>
              </Toolbar>
          </AppBar>
        </div>
      );
    }
}

export default Header;