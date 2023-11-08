import React from "react";
import { Parallax, ParallaxLayer } from '@react-spring/parallax';


const ReactSpring: React.FC = () => {
    return (
        <>
            <Parallax pages={1} style={{top: '0', left: '0'}}>
                <ParallaxLayer offset={0} speed={2.5}>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/6.png"/>
                </ParallaxLayer>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/eierschale.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/box.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/carrot.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/green-apple.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/green-can.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/green-plastic-bottle.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/grey-plasticbottle.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/11.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/glassbottle.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/papercup.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/red-apple.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/watermelon.png"/>
                <img className="object-contain h-40 w-30" src="../../src/images/waste/orange%20can.png"/>
            </Parallax>
        </>
    );
}
export default ReactSpring;