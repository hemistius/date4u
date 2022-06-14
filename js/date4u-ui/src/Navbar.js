import React from "react";
import {MDBContainer, MDBNavbar, MDBNavbarBrand, MDBNavbarItem, MDBNavbarLink, MDBNavbarNav} from 'mdb-react-ui-kit';

function Navbar() {
    return (
        <MDBNavbar dark bgColor="dark">
            <MDBContainer fluid>
                    <MDBNavbarBrand>Date4U</MDBNavbarBrand>
                    <MDBNavbarNav>
                        <MDBNavbarItem>
                            <MDBNavbarLink href="/profile">Profil</MDBNavbarLink>
                        </MDBNavbarItem>
                    </MDBNavbarNav>
            </MDBContainer>
        </MDBNavbar>
    )
}

export default Navbar