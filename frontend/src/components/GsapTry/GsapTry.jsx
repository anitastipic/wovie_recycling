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

        const timeline = gsap.timeline({});
        timeline.fromTo("#waste1", {y: 0}, {y: "150vh", duration: 95, ease: "none", rotation:50});
        timeline.fromTo("#waste2", {y:0},{y: "150vh", duration: 75, ease: "none", rotation:100}, "<");

        ScrollTrigger.create({
            animation: timeline,
            trigger: "#waste1",
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
                        <div className="wateContainer1 flex">
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
                                className="waste object-contain h-[370px] w-[420px]"
                                src="../../../src/images/waste/waste3.png"
                                alt="Description for waste 3"
                            />
                        </div>
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
