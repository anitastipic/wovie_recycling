import React, { useEffect } from "react";
import { gsap } from "gsap";
import { ScrollTrigger } from "gsap/ScrollTrigger";

gsap.registerPlugin(ScrollTrigger);

const GsapTry: React.FC = () => {

    useEffect(() => {
        const animation: GSAPTimeline = gsap.timeline();
        animation
            .to('#waste0', {
            scrollTrigger: {
                trigger: ".waste",
                start: "top top",
                toggleActions: "play pause reverse reverse",
            },
            y: 500,
            duration: 3
        });

        // Cleanup function to kill ScrollTriggers on unmount
        return () => {
            ScrollTrigger.getAll().forEach((trigger) => trigger.kill());
        };
    }, []); // Empty dependencies array

    return (
        <>
            <div className="w-full min-h-screen"></div>
            <div className="gsapTry relative h-screen">
                <div className="flex">
                    {Array.from({ length: 15 }, (_, index) => (
                        <img
                            key={index}
                            id={`waste${index}`}
                            className="waste object-contain max-h-[200px] max-w-[280px]"
                            src={`../../src/images/waste/waste${index + 1}.png`} // Make sure the paths and names match
                            alt={`Waste item ${index + 1}`}
                        />
                    ))}
                </div>
                <div className="h-80 w-full bg-amber-500"></div>
                <img className="w-[870px] h-[360px] -ml-40" src="../../src/images/bin.png" alt="Bin"></img>
            </div>
        </>
    );
};

export default GsapTry;
