import React from "react";
import {Outlet} from "react-router-dom";
import Navbar from "../components/Navbar.tsx";

export default function Layout() {
    return(
        <div className="bg-third">
            <Navbar/>
            <Outlet />
        </div>
    );
}