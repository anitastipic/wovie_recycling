import React, {useState, useEffect} from 'react';


type TypeWriterProp = {
    text: string[],
    typingDelay: number,
    nextWordDelay: number;
}

export default function Typewriter({text, typingDelay, nextWordDelay}: TypeWriterProp) {
    const [index, setIndex] = useState(0);
    const [subIndex, setSubIndex] = useState(0);
    const [blink, setBlink] = useState(true);

    useEffect(() => {
        if (subIndex <= text[index].length) {
            setTimeout(() => {
                setSubIndex(subIndex + 1);
            }, typingDelay);
        } else {
            setTimeout(() => {
                setIndex((index + 1) % text.length);
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
            <span>{text[index].substring(0, subIndex)}</span>
            <span className={`cursor ${blink ? 'cursor--active' : ''}`} />
        </div>
    );
}

