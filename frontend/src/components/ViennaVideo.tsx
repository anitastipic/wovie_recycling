import { useEffect, useRef } from "react";

export default function ViennaVideo() {
    const containerRef = useRef<HTMLCanvasElement>(null);

    useEffect(() => {
        const frameCount = 400;
        const canvas = containerRef.current;
        if (!canvas) return;

        const context = canvas.getContext("2d");
        if (!context) return;

        const imageAspectRatio = 1920 / 1080;

        const resizeCanvas = () => {
            const windowAspectRatio = window.innerWidth / window.innerHeight;
            let newWidth;
            let newHeight;

            if (windowAspectRatio > imageAspectRatio) {
                newWidth = window.innerWidth;
                newHeight = window.innerWidth / imageAspectRatio;
            } else {
                newWidth = window.innerHeight * imageAspectRatio;
                newHeight = window.innerHeight;
            }

            canvas.width = newWidth;
            canvas.height = newHeight;

            canvas.style.width = '100vw';
            canvas.style.height = '100vh';
            canvas.style.position = 'fixed';
            canvas.style.left = '50%';
            canvas.style.top = '50%';
            canvas.style.transform = 'translate(-50%, -50%)';
        };

        window.addEventListener("resize", resizeCanvas);
        resizeCanvas();

        const images: HTMLImageElement[] = [];

        const currentFrame = (index: number) => (
            `/WoVieErster/footage5/WoVieFünfter_${index.toString().padStart(3, "0")}.jpeg`
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
            const drawFrame = (frameIndex: number) => {
                context.clearRect(0, 0, canvas.width, canvas.height);
                context.drawImage(images[frameIndex], 0, 0, canvas.width, canvas.height);
            };

            drawFrame(0);

            const handleScroll = () => {
                const scrollFraction = window.scrollY / (document.body.scrollHeight - window.innerHeight);
                const frameIndex = Math.min(frameCount - 1, Math.floor(scrollFraction * frameCount));
                drawFrame(frameIndex);
            };

            window.addEventListener("scroll", handleScroll);

            return () => {
                window.removeEventListener("scroll", handleScroll);
                window.removeEventListener("resize", resizeCanvas);
            };
        });
    }, []);

    return (
        <div className="image-sequence-container h-[500vh] w-screen flex scroll-smooth">
            <canvas ref={containerRef} className="fixed"></canvas>
        </div>
    );
}
