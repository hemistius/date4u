import React, {useEffect, useState} from "react";
import Navbar from "./Navbar";
import {MDBBtn, MDBCarousel, MDBCarouselElement, MDBCarouselInner, MDBCarouselItem, MDBCol, MDBContainer, MDBIcon, MDBInput, MDBRow, MDBTextArea} from 'mdb-react-ui-kit';
import "../css/profile.css"
import "../css/background.css"
import axios from "axios";

function Profile() {

    const [profile, setProfile] = useState({photos: []})
    const [liked, setLiked] = useState(false)

    useEffect(() => {
        loadProfile()
        loadLiked()
    }, [])

    const loadProfile = async () => {
        const params = (new URL(document.location)).searchParams;
        const username = params.get("username")
        const response = await axios.get('/rest/profile', {params: {username: username}})
        setProfile(response.data)
    }

    const loadLiked = async () => {
        const params = (new URL(document.location)).searchParams;
        const username = params.get("username")
        const response = await axios.get("/rest/like", {params: {username: username}})
        setLiked(response.data.liked)
    }

    const toggleLiked = async () => {
        const params = (new URL(document.location)).searchParams;
        const username = params.get("username")
        if (liked) {
            await axios.delete("/rest/like", {params: {username: username}})
        } else {
            await axios.put("/rest/like", null,{params: {username: username}})
        }
        setLiked(!liked)
    }

    return (<div>
        <Navbar></Navbar>
        <MDBContainer>
            <MDBRow>
                <MDBCol>
                    <MDBRow className='mb-3 mt-3'>
                        <MDBCarousel dark showControls fade interval={999999999}>
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
                </MDBCol>
                <MDBCol size="9" className="mx-3">
                    <MDBRow className='mb-3 mt-3'>
                        <h3 className="p-0 text-light">Profil von {profile.nickname} <MDBBtn onClick={toggleLiked} size="m" style={{backgroundColor: '#ec4a89'}} floating><MDBIcon size="lg" far={!liked} fas={liked} icon="heart"/></MDBBtn></h3>
                    </MDBRow>
                    <MDBRow>
                        <ProfileInfoForm profile={profile}/>
                    </MDBRow>
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