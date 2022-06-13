import React from "react";
import { MDBBtn} from 'mdb-react-ui-kit';

function App() {
  return (<div>
    <h2>Welcome to React sdsas App</h2>
    <h3>Date : {new Date().toDateString()}</h3>
    <MDBBtn rounded>Primary</MDBBtn>
  </div>)
}

export default App