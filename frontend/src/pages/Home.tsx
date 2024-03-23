import React from "react";
import Navbar from "../components/Navbar.js";
import arrow from "../assets/Arrow.svg";
import wave from "../assets/wavyBackground.svg";
import pin from "../assets/pin.svg";


export default function Home() {

    const homeText = ["Auch immer auf der Suche nach Containern in deiner Nähe?", "Kein Problem - Wir helfen dir!", "WoVie - Wo und Wie recyceln in Wien."];
    const typingDelay = 75;
    const nextLineDelay = 1500;
    const style = "text-[2.5vw] font-primary font-bold text-amber-50";

    return (
        <div className="bg-third h-[216%]">
            <Navbar/>
            <div className="grid grid-cols-1 pt-[16vh] h-[100%]">
                <div className="h-[20vh] content-center text-center">
                    <p className="text-[2.5vw] font-primary font-bold text-amber-50">WoVie - Wo und Wie recyceln in
                        Wien</p>
                </div>
                <div className="">
                    <img className="" src={"/Vienna.png"}/>
                </div>
                <div className="h-[20vh] w-[100vw] flex justify-center items-center">
                    <img className="h-[4vh]" src={arrow}/>
                </div>
            </div>
            <div className="h-[100%] relative">
                <div className="absolute top-[15vw] ml-[12vw]">
                    <p className="text-amber-50 font-bold text-[4vw] ">Finde Container</p>
                    <p className="text-amber-50 font-bold text-[4vw]">in deiner Nähe</p>
                </div>
                <img className="" src={wave}/>
                <img className="absolute h-[27vw] top-[10vw] left-[70vw]" src={pin}/>
                <img className="absolute h-[15vw] top-[37vw] left-[59.5vw]" src="/trashcanYellow.png"/>
                <img className="absolute h-[9vw] top-[41.5vw] left-[74.5vw]" src="/trashcanRed.png"/>
                <img className="absolute h-[11.5vw] top-[39.8vw] left-[86.5vw]" src="/trashcanBrown.png"/>
                <button className="absolute top-[45.5vw] left-[17vw] h-[5vw] w-[15vw] bg-third rounded-full text-[2vw] text-amber-50">Zur Karte</button>
            </div>
        </div>
    )
}

