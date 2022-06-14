import React from "react";
import {MDBContainer, MDBNavbar, MDBNavbarBrand, MDBNavbarItem, MDBNavbarLink, MDBNavbarNav} from 'mdb-react-ui-kit';

function Navbar() {
    return (
        <MDBNavbar dark bgColor="dark" expand='lg'>
            <MDBContainer fluid>
                    <MDBNavbarBrand>Date4U</MDBNavbarBrand>
                    <MDBNavbarNav>
                        <MDBNavbarItem>
                            <MDBNavbarLink href="/profile">Profil</MDBNavbarLink>
                        </MDBNavbarItem>
                        <MDBNavbarItem>
                            <MDBNavbarLink href="/search">Suche</MDBNavbarLink>
                        </MDBNavbarItem>
                    </MDBNavbarNav>
            </MDBContainer>
        </MDBNavbar>
    )
}

export default Navbar