import React, {useEffect} from "react";
import {gsap} from "gsap";
import {ScrollTrigger} from "gsap/ScrollTrigger";

function GsapTry() {

    useEffect(() => {
        gsap.registerPlugin(ScrollTrigger);

        const animation = gsap.timeline();
        animation
            .to('#waste1', {
                scrollTrigger: {
                    trigger: ".waste",
                    start: "top top",
                    toggleActions: "play pause reverse reverse",
                },
                y: 500,
                duration: 3,
            });

        return () => {
            ScrollTrigger.getAll().forEach((trigger) => trigger.kill());
        };
    }, []);

    return (
        <>
            <div className="w-full min-h-screen"></div>
            <div className="gsapTry relative h-screen" id={"smooth-wrapper"}>
                <div className="flex" id={"smooth-content"}>
                    <img
                        id={"waste1"}
                        className="waste object-contain h-[350px] w-[400px]"
                        src={"../../src/images/waste/waste1.png"}
                        alt={""}
                    />
                </div>
                <div className="h-80 w-full bg-amber-500"></div>
                <img className="w-[870px] h-[360px] -ml-40" src="../../src/images/bin.png" alt="Bin"></img>
            </div>
        </>
    );
};

export default GsapTry;
