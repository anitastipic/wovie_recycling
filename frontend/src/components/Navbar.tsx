import React from 'react';
import logo from '../../public/images/logos/WoVie-logo-weiß.png';

const Navbar: React.FC = () => {
    return (
        <header className="bg-grey-800 text-wovie px-0">
            <nav className=" px-10 py-5 flex justify-between items-center">
                <img src={logo} alt="Logo" className="w-20 h-20 text-white " />
                <div className="flex">
                    <a className="text-wovie font-medium text-xl transition-colors duration-300 transform hover:text-amber-50 mx-3" href="/">Karte</a>
                    <a className="text-wovie font-medium text-xl transition-colors duration-300 transform hover:text-amber-50 mx-3" href="/about">Recycling 1 - 0 - 1</a>
                    <a className="text-wovie font-medium text-xl transition-colors duration-300 transform hover:text-amber-50 mx-3" href="/services">Werde Müllhero</a>
                    <a className="text-wovie font-medium text-xl transition-colors duration-300 transform hover:text-amber-50 mx-3" href="/contact">Über uns</a>
                    <a className="text-wovie font-medium text-xl transition-colors duration-300 transform hover:text-amber-50 mx-3" href="/contact">Kontakt</a>
                </div>
            </nav>
        </header>
    );
};

export default Navbar;