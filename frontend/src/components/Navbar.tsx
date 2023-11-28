import React from 'react';
import logo from '../images/logos/WoVie-logo-weiß.png';

export default function Navbar(){
    return (
        <header className="bg-grey-800 pt-1.5">
            <nav className="h-[10vh] w-auto px-10 flex items-center justify-between">
                <div className="flex h-[10vh] w-auto min-w-[20vw] justify-self-start">
                    <img src={logo} alt="Logo" className="object-contain"/>
                </div>
                <div className="flex justify-end w-auto">
                    <a className="text-amber-50 text-lg transition-colors duration-300 transform hover:text-wovie mx-3"
                       href="/">Karte</a>
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
        </header>
    );
}