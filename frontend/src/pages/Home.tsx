import React from "react";
import Navbar from "../components/Navbar.js";
import arrow from "../assets/Arrow.svg";


export default function Home() {

    const homeText = ["Auch immer auf der Suche nach Containern in deiner Nähe?", "Kein Problem - Wir helfen dir!", "WoVie - Wo und Wie recyceln in Wien."];
    const typingDelay = 75;
    const nextLineDelay = 1500;
    const style = "text-[2.5vw] font-primary font-bold text-amber-50";

    return (
        <div className="bg-third h-[1000vh]">
            <Navbar/>
            <div className="grid grid-cols-1 pt-[16vh]">
                <div className="h-[20vh] content-center text-center">
                    <p className="text-[2.5vw] font-primary font-bold text-amber-50">WoVie - Wo und Wie recyceln in
                        Wien</p>
                </div>
                <div className="bg-green-500">
                    <img className="" src={"/Vienna.png"}/>
                </div>
                <div className="h-[20vh] w-[100vw] flex justify-center items-center">
                    <img className="h-[4vh]" src={arrow}/>
                </div>
            </div>
        </div>
    )
}

