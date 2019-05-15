import React from 'react';
import PropTypes from 'prop-types';
import deburr from 'lodash/deburr';
import Downshift from 'downshift';
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Popper from '@material-ui/core/Popper';
import Paper from '@material-ui/core/Paper';
import MenuItem from '@material-ui/core/MenuItem';
import Button from '@material-ui/core/Button';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import './MainSearch.css';

let suggestions = [
];

function updateSuggestions() {
  suggestions = [];
  fetch('http://localhost:8080/search-records', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      // 'Content-Type': 'application/x-www-form-urlencoded',
    }
  }).then(res => {
    return res.json();
  }).then(res => {
    res.forEach(element => {
      suggestions.push({
        label: element.keyword
      })
    });

  });
}

updateSuggestions();


function renderInput(inputProps) {
    const { InputProps, classes, ref, ...other } = inputProps;
    // TODO: fix the icon issue with material design
    return (
      <div>
        <h2>Search query</h2>
        <TextField
            InputProps={{
                inputRef: ref,
                classes: {
                root: classes.inputRoot,
                input: classes.inputInput,
                },
                ...InputProps,
            }}
            {...other}
            />
      </div>
    );
}

function renderSuggestion({ suggestion, index, itemProps, highlightedIndex, selectedItem }) {
  const isHighlighted = highlightedIndex === index;
  const isSelected = (selectedItem || '').indexOf(suggestion.label) > -1;

  return (
    <MenuItem
      {...itemProps}
      key={suggestion.label}
      selected={isHighlighted}
      component="div"
      style={{
        fontWeight: isSelected ? 500 : 400,
      }}
    >
      {suggestion.label}
    </MenuItem>
  );
}
renderSuggestion.propTypes = {
  highlightedIndex: PropTypes.number,
  index: PropTypes.number,
  itemProps: PropTypes.object,
  selectedItem: PropTypes.string,
  suggestion: PropTypes.shape({ label: PropTypes.string }).isRequired,
};

let inputVal = '';
function getSuggestions(value) {
  console.log(value);
  if(value && suggestions.findIndex(a => a.label === value) === -1) {
    inputVal = value;
  }
  const inputValue = deburr(value.trim()).toLowerCase();
  const inputLength = inputValue.length;
  let count = 0;

  return inputLength === 0
    ? []
    : suggestions.filter(suggestion => {
        const keep =
          count < 5 && suggestion.label.slice(0, inputLength).toLowerCase() === inputValue;

        if (keep) {
          count += 1;
        }

        return keep;
      });
}

const styles = theme => ({
  root: {
    flexGrow: 1,
    height: 250,
    padding: 24
  },
  container: {
    flexGrow: 1,
    position: 'relative',
  },
  paper: {
    position: 'absolute',
    zIndex: 1,
    marginTop: theme.spacing.unit,
    left: 0,
    right: 0,
  },
  chip: {
    margin: `${theme.spacing.unit / 2}px ${theme.spacing.unit / 4}px`,
  },
  inputRoot: {
    flexWrap: 'wrap',
  },
  inputInput: {
    width: 'auto',
    flexGrow: 1,
  },
  divider: {
    height: theme.spacing.unit * 2,
  },
  searchWrapper: {
    'text-align': 'left'
  }
});

let popperNode;

let lastInput = '';


function keyPressed(event) {
  console.log(event);
}
function IntegrationDownshift(props) {
  const { classes } = props;
  function getSearchRecords() {
    console.log(inputVal);
    fetch('http://localhost:8080/search-records', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify({
        keyword: inputVal
      })
    }).then(res => {
      return res.json();
    }).then(res => {
      props.search(res);
      updateSuggestions();
    })
  }
  return (
    <div className={classes.root}>
      <div className={classes.divider} />
      
      <div className={classes.divider} />
      <Downshift id="downshift-popper"
        
      >
        {({
          getInputProps,
          getItemProps,
          getMenuProps,
          highlightedIndex,
          inputValue,
          isOpen,
          selectedItem,
        }) => (
          <div className={classes.container}>
            {renderInput({
              fullWidth: true,
              classes,
              InputProps: getInputProps({
                placeholder: 'With Popper',
              }),
              InputLabelProps: {
                shrink: true,
              },
              ref: node => {
                popperNode = node;
              },
            })}
            <Popper open={isOpen} anchorEl={popperNode}>
              <div {...(isOpen ? getMenuProps({}, { suppressRefError: true }) : {})}>
                <Paper
                  square
                  style={{ marginTop: 8, width: popperNode ? popperNode.clientWidth : null }}
                >
                  {getSuggestions(inputValue).map((suggestion, index) =>
                    renderSuggestion({
                      suggestion,
                      index,
                      itemProps: getItemProps({ item: suggestion.label }),
                      highlightedIndex,
                      selectedItem,
                    }),
                  )}
                </Paper>
              </div>
            </Popper>
          </div>
        )}
        
      </Downshift>
      <br></br>
      <Button variant="contained" color="primary" onClick={getSearchRecords} className={classes.button}>
        Search
      </Button>

      <br></br>

      <div className={classes.searchWrapper}>
       {props.records.map((record, i) => {     
           console.log("Entered");                 
           // Return the element. Also pass key     
           return (<Card>
             <CardContent >
             <h3>{record.title}</h3>
             <a target="blank" href={record.url}>{record.url}</a>
             <p>{record.description}</p>
             </CardContent>
             
             </Card>) 
        })}
      </div>
    </div>
  );
}

IntegrationDownshift.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(IntegrationDownshift);