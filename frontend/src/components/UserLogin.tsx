import React, {useState} from "react";

const UserLogin: React.FC = () => {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const handleSubmit = () => {
        const bodyData = {
            username: username,
            password: password
        }

        fetch("http://localhost:8080/user/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(bodyData)
        })
    }
    return (
        <div className="bg-third h-screen">
            <div className="h-screen w-full flex justify-center items-center">
                <div className="loginContainer bg-[#F6F5F3] w-[450px] h-[500px] rounded-[15px] text-center p-4">
                    <p className="font-bold text-[20px]">SIGN IN</p>
                    <form className="flex-col space-y-2 " onSubmit={handleSubmit}>
                        <label className="label login mr-2">Username:</label>
                        <input
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                        <br/>
                        <label className="label login mr-2">Password: </label>
                        <input
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        <br/>
                        <button className="bg-white hover:border-wovie text-gray-800 font-semibold py-2 px-4 border border-gray-800 rounded shadow" type="submit" >Submit</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default UserLogin;