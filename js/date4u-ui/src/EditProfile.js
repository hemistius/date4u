import React, {useEffect, useRef, useState} from "react";
import {MDBCard, MDBCardHeader, MDBCardBody, MDBCardTitle, MDBCardText, MDBBtn, MDBIcon, MDBFile, MDBRow, MDBCol, MDBInput, MDBRadio, MDBContainer, MDBTextArea} from 'mdb-react-ui-kit';
import "../css/background.css"
import Navbar from "./Navbar";
import "../css/edit_profile.css"
import axios from "axios";


function EditProfile() {

    const [nicknameValue, setNicknameValue] = useState("")
    const [emailValue, setEmailValue] = useState("")
    const [passwordValue, setPasswordValue] = useState("")
    const [birthdateValue, setBirthdateValue] = useState("")
    const [hornlengthValue, setHornlengthValue] = useState("")
    const [descriptionValue, setDescriptionValue] = useState("")
    const nicknameInput = useRef(null)
    const emailInput = useRef(null)
    const passwordInput = useRef(null)
    const birthdateInput = useRef(null)
    const hornlengthInput = useRef(null)
    const imageFileInput = useRef(null)
    const [selectedGender, setSelectedGender] = useState(null)
    const [selectedPreference, setSelectedPreference] = useState(null)

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

    const setGenderRadioChecked = (t, name) => {
        document.querySelectorAll(`input[name="${name}"]`).forEach(check => {
            check.checked = Number.parseInt(check.value) === t;
        })
    }

    const sendRegistration = () => {
    }
    const redirectProfile = (e) => {
        e.stopPropagation()
        e.preventDefault()
        window.location.href = "/profile";
    }

    const uploadPhoto = async () => {

    }

    const loadProfile = async () => {
        const response = await axios.get("/rest/user/userdetails")
        const data = response.data
        setNicknameValue(data.profile.nickname)
        setEmailValue(data.email)
        setBirthdateValue(data.profile.birthdate)
        setHornlengthValue(data.profile.hornlength.toString())
        setDescriptionValue(data.profile.description)
        setGenderRadioChecked(data.profile.gender, "genderRadio")
        setGenderRadioChecked(data.profile.attractedToGender, "preferenceRadio")
    }

    useEffect(() => {
        loadProfile()
    }, [])

    return (<div>
        <Navbar></Navbar>
        <div className="d-flex flex-column align-items-center flex-wrap mt-5">
            <MDBCard className="w-50 mb-4">
                <MDBCardHeader>Upload Foto</MDBCardHeader>
                <MDBCardBody>
                    <MDBFile className="mb-3"/>
                    <MDBBtn color="success"><MDBIcon className="me-2" fas icon="upload"/> Upload</MDBBtn>
                </MDBCardBody>
            </MDBCard>
            <MDBCard className="w-50">
                <MDBCardHeader>Profil bearbeiten</MDBCardHeader>
                <MDBCardBody>
                    <MDBContainer>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={nicknameInput} className='mb-4 ' type='text' label='Nickname' value={nicknameValue}/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={emailInput} className='mb-4 ' type='email' label='E-Mail' value={emailValue}/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={passwordInput} className='mb-4 ' type='password' label='Passwort' value={passwordValue}/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={birthdateInput} className='mb-4 ' type='date' label='Geburtsdatum' value={birthdateValue}/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={hornlengthInput} className='mb-4 ' type='number' label='Hornlänge (cm)' value={hornlengthValue}/>
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
                            <MDBCol>
                                <MDBTextArea label='Beschreibung' rows={3} value={descriptionValue}></MDBTextArea>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow className="justify-content-start">
                            <div className="d-flex">
                                <MDBBtn className="me-2" onClick={sendRegistration} color="success"><MDBIcon fas icon="save"/> Speichern</MDBBtn>
                                <MDBBtn color="danger" onClick={redirectProfile}><MDBIcon far icon="times-circle"/> Abbrechen</MDBBtn>
                            </div>
                        </MDBRow>
                    </MDBContainer>
                </MDBCardBody>
            </MDBCard>
        </div>
    </div>)
}

export default EditProfile