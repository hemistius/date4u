import React from "react";
import {MDBContainer, MDBNavbar, MDBNavbarBrand, MDBNavbarItem, MDBNavbarLink, MDBNavbarNav, MDBDropdown, MDBDropdownMenu, MDBDropdownToggle, MDBDropdownItem, MDBDropdownLink} from 'mdb-react-ui-kit';
import "../css/navbar.css"

function Navbar() {

    const performLogout = () => {
        document.getElementById('logoutForm').submit()
    }

    return (
        <MDBNavbar dark bgColor="dark" expand='lg'>
            <MDBContainer fluid>
                <MDBNavbarBrand><img className="rounded-circle" src={'/img/logo/color_bg.svg'} height='40' alt='' loading='lazy'/>Date4U</MDBNavbarBrand>
                <MDBNavbarNav>
                    <MDBNavbarItem>
                        <MDBNavbarLink href="/search">Suche</MDBNavbarLink>
                    </MDBNavbarItem>
                </MDBNavbarNav>
                <MDBDropdown>
                    <MDBDropdownToggle floating><img
                        src={'/img/profile/current'}
                        className='round-img'
                        alt=''
                    /></MDBDropdownToggle>
                    <MDBDropdownMenu dark className="mt-1">
                        <MDBDropdownItem >
                            <MDBDropdownLink href={"/profile"}>Profil</MDBDropdownLink>
                        </MDBDropdownItem>
                        <MDBDropdownItem>
                            <MDBDropdownLink onClick={performLogout}>Abmelden</MDBDropdownLink>
                        </MDBDropdownItem>
                    </MDBDropdownMenu>
                </MDBDropdown>
            </MDBContainer>
            <form id="logoutForm" action="/perform_logout" method="post">
                <input className="logout-form" type="submit" value="Logout"/>
            </form>
        </MDBNavbar>
    )
}

export default Navbar