import React, {useRef, useState} from "react";
import {MDBBtn, MDBCard, MDBCardBody, MDBCardHeader, MDBCol, MDBContainer, MDBFile, MDBIcon, MDBInput, MDBRadio, MDBRow} from 'mdb-react-ui-kit';
import "../css/background.css"
import "../css/register.css"
import {Profile} from "./dto/Profile";
import {Unicorn} from "./dto/Unicorn";
import axios from "axios";
import {RegisterRequest} from "./dto/RegisterRequest";

function Register() {

    const nicknameInput = useRef(null)
    const emailInput = useRef(null)
    const passwordInput = useRef(null)
    const birthdateInput = useRef(null)
    const hornlengthInput = useRef(null)
    const imageFileInput = useRef(null)
    const [selectedGender, setSelectedGender] = useState(null)
    const [selectedPreference, setSelectedPreference] = useState(null)

    const redirectLogin = (e) => {
        e.stopPropagation()
        e.preventDefault()
        window.location.href = "/login";
    }

    const setSelectedGenderOnChecked = (e) => {
        if (e.target.checked) {
            setSelectedGender(e.target.value)
        }
    }

    const setSelectedPreferenceOnChecked = (e) => {
        if (e.target.checked) {
            setSelectedPreference(e.target.value)
        }
    }

    const toBase64 = file => new Promise((resolve, reject) => {
        if (file === undefined) {
            return null;
        }
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result.replace(/^.*,/, ''));
        reader.onerror = error => reject(error);
    });


    const sendRegistration = async (e) => {
        e.stopPropagation()
        e.preventDefault()
        const profile = new Profile(
            nicknameInput.current.value,
            birthdateInput.current.value,
            hornlengthInput.current.value,
            selectedGender,
            selectedPreference,
            '',
            null,
            []
        )
        const unicorn = new Unicorn(
            emailInput.current.value,
            passwordInput.current.value,
            profile
        )
        const imageFile = imageFileInput.current.files[0]
        const encodedImage = await toBase64(imageFile);
        const registerRequest = new RegisterRequest(unicorn, encodedImage);
        const body = JSON.stringify(registerRequest);
        const response = await axios.post('/rest/unicorn/register', body, {
                headers: {
                    'Content-Type': 'application/json',
                }
            }
        )
        if (response.status === 201) {
            redirectLogin(e)
        }
    }

    return (<div className="d-flex justify-content-center flex-wrap ">
        <div className="flex-row mt-5">
            <div id="logoContainer" className='mb-5 flex-row d-flex justify-content-center align-items-center align-content-stretch flex-wrap'>
                <img src={'/img/logo/color_nbg.svg'} alt='...' width={300}/>
            </div>
            <MDBCard alignment='center' id="registerCard">
                <MDBCardHeader>Registrierung</MDBCardHeader>
                <MDBCardBody>
                    <MDBContainer>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={nicknameInput} className='mb-4 ' type='text' label='Nickname'/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={emailInput} className='mb-4 ' type='email' label='E-Mail'/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={passwordInput} className='mb-4 ' type='password' label='Passwort'/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={birthdateInput} className='mb-4 ' type='date' label='Geburtsdatum'/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={hornlengthInput} className='mb-4 ' type='number' label='Hornlänge (cm)'/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol id="genderCol" className="mb-4">
                                <label className="fs-6">Geschlecht:</label><br/>
                                <MDBRadio onChange={setSelectedGenderOnChecked} name='genderRadio' id='genderRadioMale' value='0' label={<MDBIcon fas icon="mars"/>} inline/>
                                <MDBRadio onChange={setSelectedGenderOnChecked} name='genderRadio' id='genderRadioFemale' value='1' label={<MDBIcon fas icon="venus"/>} inline/>
                                <MDBRadio onChange={setSelectedGenderOnChecked} name='genderRadio' id='genderRadioDiverse' value='2' label={<MDBIcon fas icon="transgender-alt"/>} inline/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow className="mb-4">
                            <MDBCol id="preferenceCol">
                                <label className="fs-6">Präferenz:</label><br/>
                                <MDBRadio onChange={setSelectedPreferenceOnChecked} name='preferenceRadio' id='preferenceRadioMale' value='0' label={<MDBIcon fas icon="mars"/>} inline/>
                                <MDBRadio onChange={setSelectedPreferenceOnChecked} name='preferenceRadio' id='preferenceRadioFemale' value='1' label={<MDBIcon fas icon="venus"/>} inline/>
                                <MDBRadio onChange={setSelectedPreferenceOnChecked} name='preferenceRadio' id='preferenceRadioDiverse' value='2' label={<MDBIcon fas icon="transgender-alt"/>} inline/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow className="mb-4">
                            <MDBCol id="profilePicCol">
                                <MDBFile inputRef={imageFileInput} label='Profilbild:' id='profilePicImg'/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow className="justify-content-start">
                            <MDBCol size={4}>
                                <MDBBtn onClick={sendRegistration}>Registrieren</MDBBtn>
                            </MDBCol>
                            <MDBCol size={1}>
                                <MDBBtn color="danger" onClick={redirectLogin}>Abbrechen</MDBBtn>
                            </MDBCol>
                        </MDBRow>
                    </MDBContainer>
                </MDBCardBody>
            </MDBCard>
            <form>
            </form>
        </div>
    </div>)
}

export default Register