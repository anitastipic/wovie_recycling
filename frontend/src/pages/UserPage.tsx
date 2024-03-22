import Navbar from "../components/Navbar.tsx";
import {useEffect, useState} from "react";
import UserLogin from "../components/UserLogin.tsx";
import {useAuth} from "../context/AuthenticationContext.tsx";

type Profile = {
    username: string;
}

export default function UserPage() {

    const {authorized, profile, login, logout} = useAuth();
    const fetchProfile = async () => {
        try {
            const response = await fetch("http://localhost:8080/user/profile", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                },
                credentials: 'include'
            });

            if (response.ok) {
                const profileData: Profile = await response.json();
                login(profileData);
            } else {
                logout();
            }
        } catch (error) {
            console.error('Error fetching profile:', error);
            logout();
        }
    };

    useEffect(() => {
        if (!authorized) {
            fetchProfile();
        }
    }, [authorized, login, logout]);

    function handleLogout() {
        fetch("http://localhost:8080/auth/logout", {
            method: "POST",
            credentials: 'include'
        }).then(res => {
            if (res.ok) {
                logout();
            } else {
                console.error('Logout failed');
            }
        }).catch(error => {
            console.error('Error during logout:', error);
        });
    }

    if (!authorized) {
        return <UserLogin/>;
    }
    return (
        <div className="bg-third h-screen">
            <Navbar/>
            <div className="h-screen flex flex-col items-center justify-center">
                <p className="text-xl font-semibold text-wovie">
                    {profile ? `Hi ${profile.username}, schön dich zu sehen!` : 'Hi there!'}
                </p>
                <button onClick={handleLogout}
                        className="bg-white hover:border-wovie text-gray-800 font-semibold py-2 px-4 border border-gray-800 rounded shadow">
                    Logout
                </button>
            </div>
        </div>

    );
}