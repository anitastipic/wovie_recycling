import React, {useEffect, useState} from "react";
import Navbar from "../components/Navbar.js";
import wave from "../assets/wavyBackground.svg";
import pin from "../assets/pin.svg";
import wheel from "../assets/wheel.svg";
import {useNavigate} from "react-router-dom";


export default function Home() {

    const [arrowIsVisible, setArrowIsVisible] = useState(true);
    const [scrollHeight, setScrollHeight] = useState(0);
    const nav = useNavigate();

    useEffect(() => {
        window.addEventListener("scroll", listenToScroll);
        return () =>
            window.removeEventListener("scroll", listenToScroll);
    }, [])

    function listenToScroll() {
        const heightToHideFrom = 50;
        const currentScrollPosition = document.body.scrollTop ||
            document.documentElement.scrollTop;
        setScrollHeight(currentScrollPosition);

        if (currentScrollPosition > heightToHideFrom) {
            arrowIsVisible && setArrowIsVisible(false);
        } else {
            setArrowIsVisible(true);
        }

    }

    return (
        <div className="bg-third h-[100%] overflow-x-clip">
            <Navbar/>
            <div className="h-[84vh] pt-[16vh] items-center relative">
                <div className="h-[25vh] content-center text-center">
                    <p className="text-[2.5vw] font-primary font-bold text-amber-50">WoVie - Wo und Wie recyceln in
                        Wien</p>
                </div>
                <div className="w-full h-[10vh] flex justify-center">
                    <img className="h-[10vh]" src={wheel}/>
                </div>
                <div className="h-[50vh] z-10 overflow-hidden absolute">
                    <div className="-ml-[35vw] h-[170vw] w-[170vw] bg-white rounded-full"></div>
                </div>
            </div>
            <div className="h-[45%] mt-[0vh] z-10 relative bg-blue-400">
                <img className="absolute top-0" src={wave}/>
                <div className="absolute top-[20vw] ml-[12.5vw]">
                    <p className="text-third font-bold text-[4vw] ">Finde Container</p>
                    <p className="text-third font-bold text-[4vw]">in deiner Nähe</p>
                </div>
                <img className="absolute h-[18vw] top-[13vw] left-[74.3vw]" src={pin}/>
                <img className="absolute h-[26vw] top-[30vw] left-[70.3vw]" src={"/trashcanYellow.png"}/>
               <button
                    className="absolute top-[46vw] left-[17.7vw] h-[5vw] w-[15vw] bg-third rounded-full text-[2vw] text-amber-50"
                    onClick={() => nav('/map')}>Zur Karte
                </button>
            </div>
        </div>
    )
}

