import React, {useState, useEffect, useRef} from "react";
import {MDBBtn, MDBPagination, MDBPaginationItem, MDBPaginationLink, MDBRipple, MDBCheckbox, MDBBtnGroup, MDBCol, MDBContainer, MDBListGroup, MDBListGroupItem, MDBRange, MDBRow, MDBCard, MDBCardBody, MDBCardTitle, MDBCardText, MDBCardImage, MDBIcon} from 'mdb-react-ui-kit';
import Navbar from "./Navbar";
import "../css/profile.css"
import "../css/search.css"
import "../css/background.css"
import axios from "axios";

const dateToAge = date => {
    const ageDifMs = Date.now() - new Date(date).getTime();
    const ageDate = new Date(ageDifMs); // miliseconds from epoch
    return Math.abs(ageDate.getUTCFullYear() - 1970);
}

function Search() {

    const minAgeRange = useRef(null)
    const maxAgeRange = useRef(null)
    const minLengthRange = useRef(null)
    const maxLengthRange = useRef(null)
    const maleCheck = useRef(null)
    const femaleCheck = useRef(null)
    const diverseCheck = useRef(null)

    const [minAgeValue, setMinAgeValue] = useState(18)
    const [ageMin, setAgeMin] = useState(18)
    const [maxAgeValue, setMaxAgeValue] = useState(99)
    const [maxAgeMin, setMaxAgeMin] = useState(19)
    const [ageMax, setAgeMax] = useState(99)
    const [minLengthValue, setMinLengthValue] = useState(0)
    const [maxLengthValue, setMaxLengthValue] = useState(40)
    const [maxLengthMin, setMaxLengthMin] = useState(1)
    const [lengthMax, setLengthMax] = useState(40)
    const [lengthMin, setLengthMin] = useState(0)
    const [profiles, setProfiles] = useState([])

    const minAgeRangeChanged = (e) => {
        setMinAgeValue(e.target.value)
        setMaxAgeMin(Math.min(Number.parseInt(e.target.value) + 1, 99))
        maxAgeRange.current.dispatchEvent(new Event("input", {bubbles: true}))
        changeParam("minAge", e.target.value)
    }

    const maxAgeRangeChanged = (e) => {
        setMaxAgeValue(e.target.value)
        changeParam("maxAge", e.target.value)
    }

    const minLengthRangeChanged = (e) => {
        setMinLengthValue(e.target.value)
        setMaxLengthMin(Math.min(Number.parseInt(e.target.value) + 1, 99))
        maxLengthRange.current.dispatchEvent(new Event("input", {bubbles: true}))
        changeParam("minLength", e.target.value)
    }

    const maxLengthRangeChanged = (e) => {
        setMaxLengthValue(e.target.value)
        changeParam("maxLength", e.target.value)
    }

    const genderChecked = (e) => {
        const checked = e.target.checked
        switch (e.target.id) {
            case 'male-check':
                changeParam('male', checked);
                break;
            case 'female-check':
                changeParam('female', checked);
                break;
            case 'diverse-check':
                changeParam('diverse', checked);
                break;
        }
    }

    const changeParam = (key, value) => {
        const url = new URL(document.location)
        url.searchParams.set(key, value)
        window.history.replaceState('', '', url.toString());
    }

    const loadQueryRanges = async () => {
        const response = await axios.get('/rest/profile/query/ranges')
        const data = response.data
        setAgeMin(dateToAge(data.maxBirthdate))
        setAgeMax(dateToAge(data.minBirthdate))
        setLengthMin(data.minLength)
        setLengthMax(data.maxLength)
    }

    const searchUsers = async () => {
        const params = (new URL(document.location)).searchParams;
        const queryParams = {}
        if (params.has("minAge")) {
            queryParams['minAge'] = params.get("minAge")
        }
        if (params.has("maxAge")) {
            queryParams['maxAge'] = params.get("maxAge")
        }
        if (params.has("minLength")) {
            queryParams['minLength'] = params.get("minLength")
        }
        if (params.has("maxLength")) {
            queryParams['maxLength'] = params.get("maxLength")
        }
        let anyTrue = false
        if (params.has("male")) {
            queryParams['male'] = params.get("male")
            if (params.get("male").toLowerCase() === 'true') {
                anyTrue = true
            }
        } else {
            queryParams['male'] = 'false'
        }
        if (params.has("female")) {
            queryParams['female'] = params.get("female")
            if (params.get("female").toLowerCase() === 'true') {
                anyTrue = true
            }
        } else {
            queryParams['female'] = 'false'
        }
        if (params.has("diverse")) {
            queryParams['diverse'] = params.get("diverse")
            if (params.get("diverse").toLowerCase() === 'true') {
                anyTrue = true
            }
        } else {
            queryParams['diverse'] = 'false'
        }
        // console.log(anyTrue)
        if (!anyTrue) {
            delete queryParams.male
            delete queryParams.female
            delete queryParams.diverse
        }
        // console.log(Array.from(params.entries()))

        const response = await axios.get('/rest/profile/query', {params: queryParams})
        const data = response.data
        data.sort((a, b) => {
            if (a.nickname.toLowerCase() > b.nickname.toLowerCase()) return 1;
            if (a.nickname.toLowerCase() < b.nickname.toLowerCase()) return -1;
            return 0;
        })
        setProfiles(data)
        return data
    }


    const initPage = async () => {
        await loadQueryRanges()

        const params = (new URL(document.location)).searchParams;
        if (params.has("minAge")) {
            setMinAgeValue(Number.parseInt(params.get("minAge")))
            // setMinAgeValue)
            // minAgeRange.current.value = Number.parseInt(params.get("minAge"))
        }
        if (params.has("maxAge")) {
            setMaxAgeValue(Number.parseInt(params.get("maxAge")))
        }
        if (params.has("minLength")) {
            setMinLengthValue(Number.parseInt(params.get("minLength")))
        }
        if (params.has("maxLength")) {
            setMaxLengthValue(Number.parseInt(params.get("maxLength")))
        }
        if (params.has("male")) {
            const checked = (params.get("male").toLowerCase() === 'true');
            if (checked) {
                maleCheck.current.checked = true
            } else {
                maleCheck.current.checked = false
            }
        }
        if (params.has("female")) {
            const checked = (params.get("female").toLowerCase() === 'true');
            if (checked) {
                maleCheck.current.checked = true
            } else {
                maleCheck.current.checked = false
            }
        }
        if (params.has("diverse")) {
            const checked = (params.get("diverse").toLowerCase() === 'true');
            if (checked) {
                maleCheck.current.checked = true
            } else {
                maleCheck.current.checked = false
            }
        }
        minLengthRange.current.dispatchEvent(new Event("input", {bubbles: true}))
        maxLengthRange.current.dispatchEvent(new Event("input", {bubbles: true}))
        minAgeRange.current.dispatchEvent(new Event("input", {bubbles: true}))
        maxAgeRange.current.dispatchEvent(new Event("input", {bubbles: true}))

        const userProfiles = await searchUsers()
    }

    useEffect(() => {
        initPage()
    }, [])

    return (<div>
        <Navbar></Navbar>
        <MDBContainer className="mx-1 mt-3" fluid>
            <MDBRow className="mb-5">
                <MDBCol><h3 className="text-light">Ich suche Einhörner...</h3></MDBCol>
            </MDBRow>
            <MDBRow className="mb-3">
                <MDBCol>
                    <MDBRange inputRef={minAgeRange} onChange={minAgeRangeChanged} value={minAgeValue} min={ageMin} max={ageMax} step='1' id='minAgeRange' label={'Alter von ' + minAgeValue}/>
                </MDBCol>
                <MDBCol>
                    <MDBRange inputRef={maxAgeRange} onChange={maxAgeRangeChanged} value={maxAgeValue} min={maxAgeMin} max={ageMax} step='1' id='maxAgeRange' label={'bis ' + maxAgeValue}/>
                </MDBCol>
                <MDBCol>
                    <MDBRange inputRef={minLengthRange} onChange={minLengthRangeChanged} value={minLengthValue} min={lengthMin} max={lengthMax} step='1' id='minLengthRange' label={'Hornlänge von ' + minLengthValue + 'cm'}/>
                </MDBCol>
                <MDBCol>
                    <MDBRange inputRef={maxLengthRange} onChange={maxLengthRangeChanged} value={maxLengthValue} min={maxLengthMin} max={lengthMax} step='1' id='maxLengthRange' label={'bis ' + maxLengthValue + 'cm'}/>
                </MDBCol>
                <MDBCol className="pt-4">
                    <MDBBtnGroup>
                        <MDBCheckbox inputRef={maleCheck} onClick={genderChecked} name='btnCheck' btn id='male-check' wrapperTag='span' label='M'/>
                        <MDBCheckbox inputRef={femaleCheck} onClick={genderChecked} name='btnCheck' btn id='female-check' wrapperClass='mx-2' wrapperTag='span' label='W'/>
                        <MDBCheckbox inputRef={diverseCheck} onClick={genderChecked} name='btnCheck' btn id='diverse-check' wrapperTag='span' label='D'/>
                    </MDBBtnGroup>
                </MDBCol>
            </MDBRow>
            <MDBRow className="mb-5">
                <MDBCol>
                    <MDBBtn onClick={searchUsers} rounded>Suche starten</MDBBtn>
                </MDBCol>
            </MDBRow>
            <MDBRow className="mb-2">
                <MDBCol>
                    {/*<h4 className="text-light">Treffer X-X (von {profiles.length})</h4>*/}
                    <h4 className="text-light">{profiles.length} Treffer</h4>
                </MDBCol>
            </MDBRow>
            <MDBRow className="row-cols-xxl-auto">
                <MDBCol className="col-lg">
                    <div className="d-flex flex-lg-wrap justify-content-center">
                        {profiles.map(profile => (<ProfileCard key={profile.id} profile={profile}></ProfileCard>))}
                    </div>
                </MDBCol>
            </MDBRow>
        </MDBContainer>
    </div>)
}


function ProfileCard(props) {

    const genderIcon = (gender) => {
        switch (gender) {
            case 0:
                return <MDBIcon fas icon="mars"/>
            case 1:
                return <MDBIcon fas icon="venus"/>
            case 2:
                return <MDBIcon fas icon="transgender-alt"/>
        }
    }

    return (<MDBCard className="mx-4 mb-5">
        <MDBRipple rippleColor='light' rippleTag='div' className='bg-image hover-overlay'>
            <MDBCardImage src={`/img/profile/${props.profile.photos.find(p => p.profilePhoto).name}.jpg`} position='top' alt={'...'}/>
            <a href={`/profile?username=${props.profile.nickname}`}>
                <div className='mask' style={{backgroundColor: 'rgba(251, 251, 251, 0.15)'}}></div>
            </a>
        </MDBRipple>
        <MDBCardBody>
            <MDBCardTitle>{props.profile.nickname} {genderIcon(props.profile.gender)}</MDBCardTitle>
            <MDBCardText>
                {dateToAge(props.profile.birthdate)} Jahre<br/>
                {props.profile.hornlength}cm Hornlänge
            </MDBCardText>
        </MDBCardBody>
    </MDBCard>)
}

export default Search