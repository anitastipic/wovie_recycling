import React from "react";
import UserSignUp from "../components/UserSignUp.js";
import Navbar from "../components/Navbar.js";
import MapVideo from "../components/MapVideo.tsx";
import TypeWriter from "../components/TypeWriter.tsx";
import GsapTry from "../components/GsapTry.tsx";
import ViennaVideo from "../components/ViennaVideo.tsx";


export default function Home() {

    const words = ["Auch immer auf der Suche nach Containern in deiner Nähe?", "Kein problem - Wir helfen dir!", "WoVie - Wo und Wie recyceln in Wien."];
    const typingDelay = 75;
    const nextLineDelay = 1500;

    return (
        <div className="bg-third h-[1000vh]">
            <div className="fixed z-20">
                <Navbar/>
            </div>
            <div className="flex flex-col items-center justify-evenly fixed">
                <div className="opacity-20 z-0 absolute top-0 left-0">
                    <ViennaVideo/>
                </div>
                <div className="absolute top-[45vh] left-[20vw] w-[60vw] z-10">
                    <TypeWriter
                        text={words}
                        typingDelay={typingDelay}
                        nextWordDelay={nextLineDelay}
                    />
                </div>
            </div>
        </div>
    )
}

