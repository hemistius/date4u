// <html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
// <head>
//     <title tiles:fragment="title">Messages : Create</title>
// </head>
// <body>
// <div tiles:fragment="content">
//     <form name="f" th:action="@{/perform_login}" method="post">
//         <fieldset>
//             <legend>Please Login</legend>
//             <div th:if="${param.error}" className="alert alert-error">
//                 Invalid username and password.
//             </div>
//             <div th:if="${param.logout}" className="alert alert-success">
//                 You have been logged out.
//             </div>
//             <label htmlFor="username">Username</label>
//             <input type="text" id="username" name="username"/>
//             <label htmlFor="password">Password</label>
//             <input type="password" id="password" name="password"/>
//             <div className="form-actions">
//                 <button type="submit" className="btn">Log in</button>
//             </div>
//         </fieldset>
//     </form>
// </div>
// </body>
// </html>

import React from "react";
import {MDBCard, MDBCardTitle, MDBCardText, MDBCardBody, MDBInput, MDBCol, MDBRow, MDBCheckbox, MDBBtn, MDBCardImage, MDBCarousel, MDBCarouselInner, MDBCarouselItem, MDBCarouselElement, MDBCarouselCaption,} from 'mdb-react-ui-kit';
import "../css/login.css"
import "../css/background.css"

function Login(props) {
    return (<div className="d-flex justify-content-center  flex-wrap" id="mainLoginFlexContainer">

        <div className='flex-row mt-5'>
            <div id="logoContainer" className='mb-5 flex-row d-flex justify-content-center align-items-center align-content-stretch flex-wrap'>
                <img src={'/img/logo/color_nbg.svg'} alt='...' width={300}/>
            </div>
            <MDBCard style={{maxWidth: '640px'}}>
                <MDBRow className='g-0'>
                    <MDBCol md='4'>
                        <MDBCarousel fade interval={4000}>
                            <MDBCarouselInner>
                                <MDBCarouselItem className='active'>
                                    <MDBCarouselElement src={'/img/login/pic11.jpg'} alt='...'/>
                                </MDBCarouselItem>
                                <MDBCarouselItem>
                                    <MDBCarouselElement src={'/img/login/pic2.jpg'} alt='...'/>
                                </MDBCarouselItem>
                            </MDBCarouselInner>
                        </MDBCarousel>
                    </MDBCol>
                    <MDBCol md='8'>
                        <MDBCardBody>
                            <MDBCardTitle>Date4U</MDBCardTitle>
                            <form className='mt-4' method="post" action="/perform_login">
                                <MDBInput className='mb-4' type='email' id='username' name="username" label='Email-Adresse'/>
                                <MDBInput className='mb-4' type='password' id='password' name="password" label='Passwort'/>

                                <div className="d-flex">
                                    <MDBBtn type='submit' className="me-2" rounded>
                                        Einloggen
                                    </MDBBtn>
                                    <MDBBtn rounded color="success">
                                        Registrieren
                                    </MDBBtn>
                                </div>
                            </form>
                        </MDBCardBody>
                    </MDBCol>
                </MDBRow>
            </MDBCard>
        </div>
    </div>)
}

export default Login