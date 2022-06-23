import React from "react";
import {MDBBtn, MDBCard, MDBCardBody, MDBCardTitle, MDBCarousel, MDBCarouselElement, MDBCarouselInner, MDBCarouselItem, MDBCol, MDBInput, MDBRow,} from 'mdb-react-ui-kit';
import "../css/login.css"
import "../css/background.css"

function Login(props) {

    const redirectRegister = (e) => {
        e.stopPropagation()
        e.preventDefault()
        window.location.href = "/register";
    }

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
                                    <MDBBtn rounded color="success" onClick={redirectRegister}>
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