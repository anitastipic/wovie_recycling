import React, {useEffect, useRef} from "react";
import {gsap} from "gsap";
import {ScrollTrigger} from "gsap/ScrollTrigger";
import Lenis from "@studio-freight/lenis";

function GsapTry() {

    const lenisRef = useRef(new Lenis());
    const lenis = lenisRef.current;

    useEffect(() => {
        lenis.on('scroll', ScrollTrigger.update)
    }, []);


    useEffect(() => {
        gsap.ticker.add((time) => {
            lenis.raf(time * 1000)
        })
    }, []);


    gsap.ticker.lagSmoothing(0)

    const numWaste = 15
    const width = window.innerWidth
    const height = window.innerHeight


    const wasteScreenRef = useRef<HTMLDivElement>(null);

    function createWasteElements() {
        let wasteNum = 1;
        for (let i = 0; i <= numWaste; i++) {
            const waste = document.createElement("img");
            const wasteClass = `waste waste${wasteNum}`
            const wasteImgPath = `../../src/images/waste/waste${wasteNum}.png`
            waste.setAttribute("class", wasteClass)
            waste.setAttribute("src", wasteImgPath)

            if(wasteScreenRef.current) {wasteScreenRef.current.append(waste)}

            wasteNum++
        }
    }

    useEffect(() => {
        createWasteElements()
    }, []);



    useEffect(() => {
        gsap.registerPlugin(ScrollTrigger)


        return () => {
            ScrollTrigger.getAll().forEach((trigger) => trigger.kill());
        };
    }, []);

    return (<>
        <div className="gsapTryContainer h-screen">
            <div className="gsapTry">
                <div className="h-[200vh]"/>
                <div ref={wasteScreenRef} className=" wasteScreen bg-amber-100 flex-row overflow-scroll"/>
                <div className="bg-blue-50 endContainer h-[200vh] w-screen relative opacity-0">
                    <div className="trigger h-1 bg-black absolute bottom-0 "/>
                </div>
                <div className="bg-green-100 h-[100vh]"/>
            </div>
        </div>
    </>);
}

export default GsapTry;