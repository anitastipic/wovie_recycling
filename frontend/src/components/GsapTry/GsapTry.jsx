import React, {useEffect} from "react";
import {gsap} from "gsap";
import {ScrollTrigger} from "gsap/ScrollTrigger";
import Lenis from "@studio-freight/lenis";

function GsapTry() {

    const lenis = new Lenis();

    lenis.on('scroll', ScrollTrigger.update)

    gsap.ticker.add((time) => {
        lenis.raf(time * 1000)
    })

    gsap.ticker.lagSmoothing(0)

    useEffect(() => {
        gsap.registerPlugin(ScrollTrigger);

        const timeline1 = gsap.timeline();
        timeline1
            .to("#waste1", {y: "343vh", duration: 2, rotation: 245, autoAlpha: 100, ease: "none"}, 0.3)
            .to("#waste2", {y: "344vh", duration: 2.5, rotation: 228, autoAlpha: 100, ease: "none"}, "<")
            .to("#waste3", {y: "345vh", duration: 2, rotation: 228, autoAlpha: 100, ease: "none"}, "<")
            .to("#waste4", {y: "323vh", duration: 2, rotation: 765, autoAlpha: 100, ease: "none"}, "<0.3")
            .to("#waste5", {y: "300vh", duration: 2, rotation: 220, autoAlpha: 100, ease: "none"}, "<")
            .to("#waste6", {y: "300vh", duration: 2, rotation: 228, autoAlpha: 100, ease: "none"}, "<")
            .to("#waste6", {y: "300vh", duration: 2, rotation: 228, autoAlpha: 100, ease: "none"}, "<");

        ScrollTrigger.create({
            animation: timeline1,
            trigger: ".wasteScreen",
            start: "top top",
            end: "bottom bottom",
            scrub: true,
            markers: true,
        });


        gsap.from(".endContainer", {
            scrollTrigger: {
                trigger: ".trigger",
                pin: ".wasteScreen",
                markers: true,
                scrub: 2,
                start: "top bottom",
                end: "+=1500 bottom"
            },

        });



        return () => {
            ScrollTrigger.getAll().forEach((trigger) => trigger.kill());
        };
    }, []);

    return (<>
        <div className="gsapTryContainer h-screen">
            <div className="gsapTry">
                <div className="h-[200vh]"></div>
                <div className=" wasteScreen bg-amber-100 flex-row overflow-scroll">
                    <div className="wasteRow1 flex">
                        <div className="waste1Container">
                            <img
                                id="waste1"
                                className="waste invisible object-contain h-[150px] max-w-fit"
                                src="../../../src/images/waste/waste14.png"
                                alt="waste item - carrot"
                            />
                        </div>
                        <div className="waste2Container">
                            <img
                                id="waste2"
                                className="waste invisible object-contain h-[160px] max-w-fit ml-40"
                                src="../../../src/images/waste/waste7.png"
                                alt="waste item - grey plastic bottle"
                            />
                        </div>
                        <div className="waste3Container">
                            <img
                                id="waste3"
                                className="waste invisible object-contain h-[350px] max-w-fit filter blur-[2px] ml-56"
                                src="../../../src/images/waste/waste1.png"
                                alt="waste item - plastic bag"
                            />
                        </div>
                    </div>
                    <div className="wasteRow2 flex">
                        <div className="waste4Container">
                            <img
                                id="waste4"
                                className="waste invisible object-contain h-[120px] max-w-fit -mt-[180px] ml-14 filter blur-[0.5px]"
                                src="../../../src/images/waste/waste3.png"
                                alt="waste item - watermelon"
                            />
                        </div>
                        <div className="waste5Container">
                            <img
                                id="waste5"
                                className="waste invisible object-contain h-[350px] max-w-fit -mt-[150px] filter blur-[1.5px]"
                                src="../../../src/images/waste/waste4.png"
                                alt="waste item - red apple"
                            />
                        </div>
                        <div className="waste6Container">
                            <img
                                id="waste6"
                                className="waste invisible object-contain h-[100px] max-w-fit -mt-[50px] -ml-[50px]"
                                src="../../../src/images/waste/waste5.png"
                                alt="waste item - paper cup"
                            />
                        </div>
                        <div className="waste7Container">
                            <img
                                id="waste7"
                                className="waste invisible object-contain h-[200px] max-w-fit filter blur-[1px] mt-[50px]"
                                src="../../../src/images/waste/waste9.png"
                                alt="waste item - green soda can"
                            />
                        </div>
                    </div>
                    <div className="wasteRow3 flex">
                        <div className="waste8Container">
                            <img
                                id="waste8"
                                className="waste invisible object-contain max-w-fit h-[100px] -mt-[200px] ml-7"
                                src="../../../src/images/waste/waste8.png"
                                alt="waste item - green plastic bottle"
                            />
                        </div>
                        <div className="waste9Container">
                            <img
                                id="waste9"
                                className="waste invisible object-contain h-[200px] max-w-fit filter blur-[1.3px]"
                                src="../../../src/images/waste/waste2.png"
                                alt="waste item - red soda can"
                            />
                        </div>
                        <div className="waste10Container">
                            <img
                                id="waste10"
                                className="waste invisible object-contain h-[80px] max-w-fit ml-40 -mt-32"
                                src="../../../src/images/waste/waste6.png"
                                alt="waste item - pink can"
                            />
                        </div>
                    </div>
                    <div className="wasteRow4 flex">

                        <div className="waste11Container">
                            <img
                                id="waste11"
                                className="waste invisible object-contain h-[150px] max-w-fit mt-12 "
                                src="../../../src/images/waste/waste10.png"
                                alt="waste item - banana"
                            />
                        </div>
                        <div className="waste12Container">
                            <img
                                id="waste12"
                                className="waste invisible object-contain h-[350px] max-w-fit -mt-[150px] ml-72 filter blur-[1.3px]"
                                src="../../../src/images/waste/waste15.png"
                                alt="waste item - cardboard box"
                            />
                        </div>
                    </div>
                    <div className="wasteRow5 flex">
                        <div className="waste13Container">
                            <img
                                id="waste13"
                                className="waste invisible object-contain h-[150px] max-w-fit ml-[370px] -mt-32 "
                                src="../../../src/images/waste/waste11.png"
                                alt="waste item - green apple"
                            />
                        </div>
                        <div className="waste14Container">
                            <img
                                id="waste14"
                                className="waste invisible object-contain h-[200px] max-w-fit -ml-[200px]"
                                src="../../../src/images/waste/waste12.png"
                                alt="waste item - broken glass bottle"
                            />
                        </div>
                        <div className="waste15Container">
                            <img
                                id="waste15"
                                className="waste invisible object-contain h-[130px] ml-[140px] -max-w-fit"
                                src="../../../src/images/waste/waste13.png"
                                alt="waste item - eggshell"
                            />
                        </div>
                    </div>
                    <div className="bg-blue-50 endContainer h-[200vh] w-screen relative opacity-0">
                        <div className="trigger h-1 bg-black absolute bottom-0 "></div>
                    </div>
                    <div className="bg-green-100 h-[100vh]"></div>
                </div>
            </div>
        </div>
    </>);
}

export default GsapTry;
