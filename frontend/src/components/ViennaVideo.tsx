import {useEffect, useRef} from "react";

export default function ViennaVideo() {
    const containerRef = useRef<HTMLCanvasElement>(null);

    useEffect(() => {
        const frameCount = 300;
        const canvas = containerRef.current;
        if (!canvas) return;

        const context = canvas.getContext('2d');
        if (!context) return;

        canvas.width = screen.width;
        canvas.height = screen.height;

        const images: HTMLImageElement[] = [];

        const currentFrame = (index: number) => (
            `/WoVieErster/footage4/WoVieVierter_${index.toString().padStart(3, "0")}.jpeg`
        );

        const preloadImages = () => {
            const promises = [];

            for (let i = 1; i <= frameCount; i++) {
                const img = new Image();
                img.src = currentFrame(i);
                const promise = new Promise((resolve, reject) => {
                    img.onload = resolve;
                    img.onerror = reject;
                });
                images.push(img);
                promises.push(promise);
            }

            return Promise.all(promises);
        };

        preloadImages().then(() => {
            drawFrame(0);
            const handleScroll = () => {
                const scrollFraction = window.scrollY / (document.body.scrollHeight - window.innerHeight);
                const frameIndex = Math.min(images.length - 1, Math.floor(scrollFraction * images.length));
                drawFrame(frameIndex);
            };

            window.addEventListener('scroll', handleScroll);

            // Cleanup
            return () => window.removeEventListener('scroll', handleScroll);
        });

        const drawFrame = (frameIndex: number) => {
            context.clearRect(0, 0, canvas.width, canvas.height);
            context.drawImage(images[frameIndex], 0, 0, canvas.width, canvas.height);
        };
    }, []);



        return (
        <div className="image-sequence-container h-[500vh] w-screen flex justify-center scroll-smooth">
            <canvas ref={containerRef} className={`w-[${screen.width}] h-[${screen.height}] fixed`}></canvas>
        </div>
    );
}