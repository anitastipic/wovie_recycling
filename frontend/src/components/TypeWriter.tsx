import React, {useState, useEffect} from 'react';

const words = ["Auch immer auf der Suche nach Containern in deiner Nähe?", "Kein problem - Wir helfen dir!", "WoVie - Wo und Wie recyceln in Wien."];
const typingDelay = 65;
const nextWordDelay = 2000;

const Typewriter: React.FC = () => {
    const [index, setIndex] = useState(0);
    const [subIndex, setSubIndex] = useState(0);
    const [blink, setBlink] = useState(true);

    useEffect(() => {
        if (subIndex <= words[index].length) {
            setTimeout(() => {
                setSubIndex(subIndex + 1);
            }, typingDelay);
        } else {
            setTimeout(() => {
                setIndex((index + 1) % words.length);
                setSubIndex(0);
            }, nextWordDelay);
        }
    }, [subIndex, index]);

    useEffect(() => {
        const blinkInterval = setInterval(() => {
            setBlink(b => !b);
        }, 500);

        return () => clearInterval(blinkInterval);
    }, []);

    return (
        <div id="typewriter" className=" text-3xl text-amber-50 h-20 flex justify-center items-center">
            <span>{words[index].substring(0, subIndex)}</span>
            <span className={`cursor ${blink ? 'cursor--active' : ''}`} />
        </div>
    );
};

export default Typewriter;
