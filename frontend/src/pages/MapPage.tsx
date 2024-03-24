import Map from "../components/Map.tsx";
import Navbar from "../components/Navbar.tsx";

export default function MapPage() {
    return (
        <div className="bg-third h-screen w-full scroll-m-0">
            <div>
                <Navbar></Navbar>
            </div>
            <div className="pt-[22vh] w-full">
                <Map/>
            </div>
        </div>
    )
}