import React from 'react';
import logo from '../images/logos/WoVie-logo-weiß.png';
import {Link} from "react-router-dom";

export default function Navbar(){
    return (
            <nav className="h-[13vh] w-full px-10 pt pt-6 flex items-center justify-between fixed">
                <Link to="/" className="flex h-[13vh] w-auto min-w-[20vw] justify-self-start">
                    <img src={logo} alt="Logo" className="object-contain"/>
                </Link>
                <div className="flex justify-end w-auto">
                    <Link to="/map" className="text-amber-50 text-lg transition-colors duration-300 transform hover:text-wovie mx-3"
                       >Karte</Link>
                    <a className="text-amber-50 text-lg transition-colors duration-300 transform hover:text-wovie mx-3"
                       href="/about">Recycling 1 - 0 - 1</a>
                    <a className="text-amber-50 text-lg transition-colors duration-300 transform hover:text-wovie mx-3"
                       href="/services">Werde Müllhero</a>
                    <a className="text-amber-50 text-lg transition-colors duration-300 transform hover:text-wovie mx-3"
                       href="/contact">Über uns</a>
                    <a className="text-amber-50 text-lg transition-colors duration-300 transform hover:text-wovie mx-3"
                       href="/contact">Kontakt</a>
                </div>
            </nav>
    );
}