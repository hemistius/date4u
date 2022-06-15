import React, {useState, useEffect} from "react";
import Navbar from "./Navbar";
import {MDBContainer, MDBRow, MDBCol, MDBBtn, MDBIcon, MDBListGroup, MDBListGroupItem, MDBCarousel, MDBCarouselInner, MDBCarouselItem, MDBCarouselElement, MDBCarouselCaption, MDBTextArea, MDBInput} from 'mdb-react-ui-kit';
import "../css/profile.css"
import "../css/background.css"
import axios from "axios";

function Profile() {

    const [profile, setProfile] = useState({photos: []})

    useEffect(() => {
        loadProfile()
    }, [])

    const loadProfile = async () => {
        const params = (new URL(document.location)).searchParams;
        const username = params.get("username")
        const response = await axios.get('/rest/profile', {params: {username: username}})
        setProfile(response.data)
    }

    return (<div>
        <Navbar></Navbar>
        <MDBContainer>
            <MDBRow>
                <MDBCol>
                    {/*<MDBContainer>*/}
                    <MDBRow className='mb-3 mt-3'>
                        <MDBCarousel dark  showControls fade interval={999999999}>
                            <MDBCarouselInner>
                                {profile.photos.map((photo, idx) => (
                                    <MDBCarouselItem key={photo.id} className={photo.profilePhoto ? 'active' : ''}>
                                        <MDBCarouselElement src={"/img/profile/" + photo.name + ".jpg"} alt='...'/>
                                    </MDBCarouselItem>
                                ))}
                            </MDBCarouselInner>
                        </MDBCarousel>
                    </MDBRow>
                    <MDBRow className='mb-3 text-light'>
                        <MDBCol className="p-0">
                            <MDBInput className='mb-4 text-light' placeholder="." disabled readonly label="Nickname" value={profile.nickname}/>
                        </MDBCol>
                    </MDBRow>
                    <MDBRow className='mb-3'>
                        <MDBBtn floating><MDBIcon fas icon="thumbs-up"/></MDBBtn>
                    </MDBRow>
                    {/*</MDBContainer>*/}
                </MDBCol>
                <MDBCol size="9" className="mx-3">
                    {/*<MDBContainer>*/}
                    <MDBRow className='mb-3 mt-3'>
                        <h3 className="p-0 text-light">Profil von {profile.nickname}</h3>
                    </MDBRow>
                    <MDBRow>
                        <ProfileInfoForm profile={profile}/>
                    </MDBRow>
                    {/*</MDBContainer>*/}
                </MDBCol>
            </MDBRow>
        </MDBContainer>
    </div>)
}

function ProfileInfoForm(props) {

    const parseGender = (genderId) => {
        switch (genderId) {
            case 0:
                return 'M'
            case 1:
                return 'F'
            case 2:
                return 'D'
            default:
                return '-'
        }
    }

    return (
        <form className="form-white">
            <MDBInput className='mb-4 text-light' placeholder="." disabled readonly label="Geburtsdatum" value={props.profile.birthdate}/>
            <MDBInput className='mb-4 text-light' placeholder="." disabled readonly label="Hornlänge" value={props.profile.hornlength + "cm"}/>
            <MDBInput className='mb-4 text-light' disabled readonly label="Geschlecht" value={parseGender(props.profile.gender)}/>
            <MDBTextArea className="text-light" placeholder="." label="Beschreibung" disabled readonly rows={4} value={props.profile.description}/>
        </form>
    )
}

export default Profile