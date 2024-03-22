import React, {useState} from "react";
import Navbar from "./Navbar.tsx";
import {useNavigate} from "react-router-dom";

export default function UserSignUp() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()
        const bodyData = {
            username: username,
            password: password
        }

        await fetch("http://localhost:8080/user/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(bodyData)
        })
        navigate('/login');
    }
    return (
        <div className="bg-third h-screen ">
            <Navbar/>
            <div className="h-screen w-full flex justify-center items-center">
                <div className="loginContainer bg-[#F6F5F3] rounded-r-2xl text-center h-[50vh] w-[40vh] p-6">
                    <p className="font-semibold text-[2.5vh] mb-5">Einfach ausfüllen und loslegen!</p>
                    <form className="flex-col space-y-2" onSubmit={handleSubmit}>
                        <label className="label login">Username:</label><br/>
                        <input
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                        <br/>
                        <label className="label login">Password:</label><br/>
                        <input
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        <br/>
                        <button className="bg-white hover:border-wovie text-gray-800 font-semibold py-2 px-4 border border-gray-800 rounded shadow" type="submit" onSubmit={() => handleSubmit} >Registrieren</button>
                    </form>
                </div>
            </div>
        </div>
    )
}
