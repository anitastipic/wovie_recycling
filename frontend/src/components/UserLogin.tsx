import React, {useState} from "react";
import Navbar from "./Navbar.tsx";
import {useAuth} from "../context/AuthenticationContext.tsx";
import {useNavigate} from "react-router-dom";

export default function UserLogin() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const { authorize } = useAuth();

    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()
        const bodyData = {
            username: username,
            password: password
        }

        fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(bodyData),
            credentials: 'include'
        }).then((res) => {
            console.log(bodyData);
            console.log(res);
            if (!res.ok) throw new Error('Login failed');
            if (res.ok) {
                authorize();
                navigate('/hero');}
        })

    }

    return (
        <div className="bg-third">
            <Navbar/>
            <div className="h-screen w-full flex justify-evenly items-center">
                <div className="loginContainer bg-[#F6F5F3] rounded-r-2xl h-[50vh] w-[40vh] p-6">
                    <p className="font-semibold text-[2.5vh] mb-3 ">Willkommen zurück, Hero!</p>
                    <form className="flex-col space-y-4" onSubmit={handleSubmit}>
                        <label className="label login text-sm font-semibold">Username:</label><br/>
                        <input
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            placeholder="Username"
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                        <label className="label login inline-block text-sm font-semibold ">Password:</label><br/>
                        <input
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="Password"
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                        <button
                            className="bg-white hover:border-wovie text-gray-800 font-semibold mt-5 py-2 px-4 border border-gray-800 rounded shadow"
                            type="submit" onSubmit={() => handleSubmit}>Anmelden
                        </button>
                    </form>
                    <p className="mt-5 text-sm">Kein Account? Kein Problem! Einfach
                        <a href="/register" className="text-wovie font-semibold ">{' hier '}</a>
                        klicken und Hero werden.
                    </p>
                </div>
            </div>
        </div>
    )


}