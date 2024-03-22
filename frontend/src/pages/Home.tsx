import React from "react";
import Navbar from "../components/Navbar.js";
import TypeWriter from "../components/TypeWriter.tsx";
import ViennaVideo from "../components/ViennaVideo.tsx";


export default function Home() {

    const homeText = ["Auch immer auf der Suche nach Containern in deiner Nähe?", "Kein Problem - Wir helfen dir!", "WoVie - Wo und Wie recyceln in Wien."];
    const typingDelay = 75;
    const nextLineDelay = 1500;

    return (
        <div className="bg-third h-[1000vh]">
            <div className="fixed z-20">
                <Navbar/>
            </div>
            <div className="flex flex-col items-center justify-evenly fixed">
                <div className="w-screen opacity-60 z-0 absolute top-0 left-0">
                    <ViennaVideo/>
                </div>
                <div className="absolute top-[45vh] left-[20vw] w-[60vw] z-10 text-center">
                    <TypeWriter
                        text={homeText}
                        typingDelay={typingDelay}
                        nextWordDelay={nextLineDelay}
                    />
                </div>
            </div>
        </div>
    )
}

