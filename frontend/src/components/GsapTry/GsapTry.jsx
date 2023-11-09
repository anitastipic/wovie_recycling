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

        const timeline1 = gsap.timeline({});
        timeline1.fromTo("#waste1", {y: 0}, {y: "150vh", duration: 95, ease: "none", rotation: 50});
        timeline1.fromTo("#waste2", {y: 0}, {y: "150vh", duration: 75, ease: "none", rotation: 100}, "<");
        timeline1.fromTo("#waste3", {y: 0}, {y: "150vh", duration: 95, ease: "none", rotation: 70}, "<");

        const timeline2 = gsap.timeline({});
        timeline2.fromTo("#waste4", {y: 0}, {y: "100vh", duration: 55, ease: "none", rotation: 30}, "<");
        timeline2.fromTo("#waste5", {y: 0}, {y: "100vh", duration: 45, ease: "none", rotation: 60}, "<");
        timeline2.fromTo("#waste6", {y: 0}, {y: "100vh", duration: 85, ease: "none", rotation: 20}, "<");
        timeline2.fromTo("#waste7", {y: 0}, {y: "100vh", duration: 35, ease: "none", rotation: 10}, "<");

        ScrollTrigger.create({
            animation: timeline1,
            trigger: ".wasteContainer1",
            start: "top top",
            scrub: true,
            markers: true,
        })

        ScrollTrigger.create({
            animation: timeline2,
            trigger: ".wasteContainer2",
            start: "top top",
            scrub: true,
            markers: true,
        })

        return () => {
            ScrollTrigger.getAll().forEach((trigger) => trigger.kill());
        };
    }, []);

    return (
        <>
            <div className="">
                <div className="gsapTry h-screen bg-amber-50">
                    <div className="flex-row bg-amber-100">
                        <div className="h-10 bg-amber-200"></div>
                        <div className="wasteContainer1 flex">
                            <img
                                id="waste1"
                                className="waste object-contain h-[450px] w-[500px]"
                                src="../../../src/images/waste/waste1.png"
                                alt="waste item - plastic bag"
                            />
                            <img
                                id="waste2"
                                className="waste object-contain h-[350px] w-[400px] ml-20 mr-24 mt-32"
                                src="../../../src/images/waste/waste2.png"
                                alt="waste item - red soda can"
                            />
                            <img
                                id="waste3"
                                className="waste object-contain h-[320px] w-[370px]"
                                src="../../../src/images/waste/waste3.png"
                                alt="Description for waste 3"
                            />
                        </div>
                        <div className="wasteContainer2 flex">
                            <img
                                id="waste4"
                                className="waste object-contain h-[350px] w-[400px]"
                                src="../../../src/images/waste/waste4.png"
                                alt="Description for waste 4"
                            />
                            <img
                                id="waste5"
                                className="waste object-contain h-[350px] w-[400px]"
                                src="../../../src/images/waste/waste5.png"
                                alt="Description for waste 5"
                            />
                            <img
                                id="waste6"
                                className="waste object-contain h-[350px] w-[400px]"
                                src="../../../src/images/waste/waste6.png"
                                alt="Description for waste 6"
                            />
                        </div>
                        <img
                            id="waste7"
                            className="waste object-contain h-[350px] w-[400px]"
                            src="../../../src/images/waste/waste7.png"
                            alt="Description for waste 7"
                        />
                        <img
                            id="waste8"
                            className="waste object-contain h-[350px] w-[400px]"
                            src="../../../src/images/waste/waste8.png"
                            alt="Description for waste 8"
                        />
                        <img
                            id="waste9"
                            className="waste object-contain h-[350px] w-[400px]"
                            src="../../../src/images/waste/waste9.png"
                            alt="Description for waste 9"
                        />
                        <img
                            id="waste10"
                            className="waste object-contain h-[350px] w-[400px]"
                            src="../../../src/images/waste/waste10.png"
                            alt="Description for waste 10"
                        />
                        <img
                            id="waste11"
                            className="waste object-contain h-[350px] w-[400px]"
                            src="../../../src/images/waste/waste11.png"
                            alt="Description for waste 11"
                        />
                        <img
                            id="waste12"
                            className="waste object-contain h-[350px] w-[400px]"
                            src="../../../src/images/waste/waste12.png"
                            alt="Description for waste 12"
                        />
                        <img
                            id="waste13"
                            className="waste object-contain h-[350px] w-[400px]"
                            src="../../../src/images/waste/waste13.png"
                            alt="Description for waste 13"
                        />
                        <img
                            id="waste14"
                            className="waste object-contain h-[350px] w-[400px]"
                            src="../../../src/images/waste/waste14.png"
                            alt="Description for waste 14"
                        />
                        <img
                            id="waste15"
                            className="waste object-contain h-[350px] w-[400px]"
                            src="../../../src/images/waste/waste15.png"
                            alt="Description for waste 15"
                        />
                    </div>
                    <div className="h-80 w-full bg-amber-500"></div>
                </div>
            </div>
        </>
    );
}

export default GsapTry;
