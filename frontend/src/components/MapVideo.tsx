import React from "react";

const MapVideo: React.FC = () => {
    return(
        <div className="relative w-full overflow-hidden" style={{ paddingTop: '25.3%' }}> {/* 16:9 Aspect Ratio */}
            <video
                className="absolute top-0 left-0 w-full h-full object-cover"
                src="../../src/images/wovie-video.mp4"
                autoPlay
                muted
                playsInline>
                Your browser does not support the video tag.
            </video>
        </div>
    );
}

export default MapVideo;