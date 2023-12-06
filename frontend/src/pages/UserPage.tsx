import Navbar from "../components/Navbar.tsx";
import {useEffect, useState} from "react";
import UserLogin from "../components/UserLogin.tsx";

type Profile = {
    username: string;
}

export default function UserPage() {

    const [profile, setProfile] = useState<Profile | null>(null);
    const [authorized, setAuthorized] = useState(false);
    const fetchProfile = () => {
        return fetch("http://localhost:8080/user/profile", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: 'include'
        }).then((res) => {
            if (res.ok) setAuthorized(true);
            return res.json()
        })
    }

    useEffect(() => {
        fetchProfile().then(setProfile);
    }, []);

    function handleLogout() {
        fetch("http://localhost:8080/user/logout", {
            method:"POST",
            credentials: 'include'
        }).then(res => {
            if(res.ok) setAuthorized(false);
        })
    }

    return (
        <>
            {authorized &&
                <div className="bg-third h-screen">
                    <Navbar/>
                    <div className="h-screen flex flex-col items-center justify-center">
                        <p className="text-xl font-semibold text-wovie">{profile && "Hi " + profile.username + ", schön dich zu sehn!"}</p>
                        <button onClick={() => handleLogout() } className="bg-white hover:border-wovie text-gray-800 font-semibold py-2 px-4 border border-gray-800 rounded shadow">Logout</button>
                    </div>
                </div>
            }
            {!authorized && <UserLogin/>}
        </>
    )
}