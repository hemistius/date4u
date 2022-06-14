import React from "react"
import ReactDom from "react-dom"
import 'mdb-react-ui-kit/dist/css/mdb.min.css';
import App from "./App"
import Profile from "./Profile";
import Search from "./Search";


if (document.getElementById('app') != null) {
    ReactDom.render(<App/>, document.getElementById('app'))
} else if (document.getElementById('profile') != null) {
    ReactDom.render(<Profile/>, document.getElementById('profile'))
} else if (document.getElementById('search') != null) {
    ReactDom.render(<Search/>, document.getElementById('search'))
}

