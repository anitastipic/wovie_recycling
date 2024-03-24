import Navbar from "../components/Navbar.tsx";

export default function ErrorPage() {
    return(
        <div className="bg-third h-[100vh]">
            <Navbar/>
            <div className="h-[100vh] items-center text-center content-center">
                <p className="text-2xl text-amber-50 font-bold">Ups, something went wrong.</p>
            </div>
        </div>
    );
}