import React, {useState} from "react";

const UserSignUp: React.FC = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

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
    }
    return (
        <div className="bg-third">
            <div className="h-[89.5vh] w-full flex justify-center items-center">
                <div className="loginContainer bg-[#F6F5F3] rounded-[15px] text-center h-[50vh] w-[40vh] p-6">
                    <p className="font-bold text-[20px] mb-5">Create your account</p>
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
                        <button className="bg-white hover:border-wovie text-gray-800 font-semibold py-2 px-4 border border-gray-800 rounded shadow" type="submit" onSubmit={() => handleSubmit} >Submit</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default UserSignUp;