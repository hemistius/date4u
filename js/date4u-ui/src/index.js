import React, {Suspense} from "react"
import ReactDom from "react-dom"
import 'mdb-react-ui-kit/dist/css/mdb.min.css';

if (document.getElementById('app') != null) {
    // ReactDom.render(<App/>, document.getElementById('app'))
} else if (document.getElementById('profile') != null) {
    const Profile = React.lazy(() => import("./Profile"))
    ReactDom.render(<Suspense fallback={<div></div>}><Profile/></Suspense>, document.getElementById('profile'))
} else if (document.getElementById('search') != null) {
    const Search = React.lazy(() => import("./Search"))
    ReactDom.render(<Suspense fallback={<div></div>}><Search/></Suspense>, document.getElementById('search'))
} else if (document.getElementById('login') != null) {
    const Login = React.lazy(() => import("./Login"))
    ReactDom.render(<Suspense fallback={<div></div>}><Login/></Suspense>, document.getElementById('login'))
} else if (document.getElementById('register') != null) {
    const Register = React.lazy(() => import("./Register"))
    ReactDom.render(<Suspense fallback={<div></div>}><Register/></Suspense>, document.getElementById('register'))
} else if (document.getElementById('edit_profile') != null) {
    const EditProfile = React.lazy(() => import("./EditProfile"))
    ReactDom.render(<Suspense fallback={<div></div>}><EditProfile/></Suspense>, document.getElementById('edit_profile'))
}

