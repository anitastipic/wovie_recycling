import React from "react";
import UserSignUp from "../components/UserSignUp.js";
import Navbar from "../components/Navbar.js";
import MapVideo from "../components/MapVideo.tsx";
import TypeWriter from "../components/TypeWriter.tsx";
import GsapTry from "../components/GsapTry.tsx";


export default function Home() {

    const words = ["Auch immer auf der Suche nach Containern in deiner Nähe?", "Kein problem - Wir helfen dir!", "WoVie - Wo und Wie recyceln in Wien."];
    const typingDelay = 75;
    const nextLineDelay = 1500;

    return (
        <div className="bg-third h-screen">
            <div className="flex flex-col h-[89.5vh] mt-[10vh] items-center justify-evenly">
                <div className="mb-7">
                    <TypeWriter
                        text={words}
                        typingDelay={typingDelay}
                        nextWordDelay={nextLineDelay}
                    />
                </div>
                <div className="text-white ">
                    <p>Scroll down</p>
                </div>
            </div>
        </div>
    )
}

