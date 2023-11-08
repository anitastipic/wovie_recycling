import React from "react";
import ReactSpringTry from "./components/ReactSpringTry.tsx";
import MapVideo from "./components/MapVideo.tsx";

const App: React.FC = () => {

    return (
        <div className="bg-gray-800 min-h-screen">
            <ReactSpringTry/>
            <MapVideo/>
        </div>
    )
}


export default App
