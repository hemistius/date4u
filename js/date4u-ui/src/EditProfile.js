import React, {useEffect, useRef, useState} from "react";
import {MDBCard, MDBCardHeader, MDBBadge, MDBCardBody, MDBCardTitle, MDBCardText, MDBBtn, MDBIcon, MDBFile, MDBRow, MDBCol, MDBInput, MDBRadio, MDBContainer, MDBTextArea} from 'mdb-react-ui-kit';
import "../css/background.css"
import Navbar from "./Navbar";
import "../css/edit_profile.css"
import axios from "axios";
import {RegisterRequest} from "./dto/RegisterRequest";
import {Profile} from "./dto/Profile";
import {Unicorn} from "./dto/Unicorn";


function EditProfile() {

    const [nicknameValue, setNicknameValue] = useState("")
    const [emailValue, setEmailValue] = useState("")
    const [passwordValue, setPasswordValue] = useState("")
    const [birthdateValue, setBirthdateValue] = useState("")
    const [hornlengthValue, setHornlengthValue] = useState("")
    const [descriptionValue, setDescriptionValue] = useState("")
    const [uploadResult, setUploadResult] = useState(null)
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

    const sendEdit = async (e) => {
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
        const body = JSON.stringify(unicorn);
        const response = await axios.put('/rest/unicorn', body, {
                headers: {
                    'Content-Type': 'application/json',
                }
            }
        )
        console.log(response)
        // if (response.status === 201) {
        //     redirectLogin(e)
        // }
    }

    const redirectProfile = (e) => {
        e.stopPropagation()
        e.preventDefault()
        window.location.href = "/profile";
    }

    const uploadPhoto = async () => {
        const imageFile = imageFileInput.current.files[0]
        if (imageFile === undefined) {
            return
        }
        const fd = new FormData();
        fd.append('file', imageFile)
        try {
            const response = await axios.post('/rest/photo/upload', fd, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }
            )
            if (response.status === 201) {
                imageFileInput.current.value = null
                setUploadResult(true)
            } else {
                setUploadResult(false)
            }
        } catch (e) {
            setUploadResult(false)
        }
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
                    <MDBFile onChange={() => setUploadResult(null)} inputRef={imageFileInput} className="mb-3"/>
                    <MDBBtn onClick={uploadPhoto} color="success"><MDBIcon className="me-2" fas icon="upload"/> Upload</MDBBtn>
                    {uploadResult === true ? <h4 className="uploadResult ms-3"><MDBBadge pill color="success"><MDBIcon fas icon="check-circle"/> Upload erfolgreich</MDBBadge></h4> : ""}
                    {uploadResult === false ? <h4 className="uploadResult ms-3"><MDBBadge pill color="danger"><MDBIcon fas icon="exclamation-triangle"/> Upload fehlgeschlagen</MDBBadge></h4> : ""}

                </MDBCardBody>
            </MDBCard>
            <MDBCard className="w-50">
                <MDBCardHeader>Profil bearbeiten</MDBCardHeader>
                <MDBCardBody>
                    <MDBContainer>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={nicknameInput} className='mb-4 ' type='text' label='Nickname' value={nicknameValue} onChange={(e) => setNicknameValue(e.target.value)}/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={emailInput} className='mb-4 ' type='email' label='E-Mail' value={emailValue} onChange={(e) => setEmailValue(e.target.value)}/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={passwordInput} className='mb-4 ' type='password' label='Passwort' value={passwordValue} onChange={(e) => setPasswordValue(e.target.value)}/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={birthdateInput} className='mb-4 ' type='date' label='Geburtsdatum' value={birthdateValue} onChange={(e) => setBirthdateValue(e.target.value)}/>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol>
                                <MDBInput inputRef={hornlengthInput} className='mb-4 ' type='number' label='Hornlänge (cm)' value={hornlengthValue} onChange={(e) => setHornlengthValue(e.target.value)}/>
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
                                <MDBTextArea label='Beschreibung' rows={3} value={descriptionValue} onChange={(e) => setDescriptionValue(e.target.value)}></MDBTextArea>
                            </MDBCol>
                        </MDBRow>
                        <MDBRow className="justify-content-start">
                            <div className="d-flex">
                                <MDBBtn className="me-2" onClick={sendEdit} color="success"><MDBIcon fas icon="save"/> Speichern</MDBBtn>
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