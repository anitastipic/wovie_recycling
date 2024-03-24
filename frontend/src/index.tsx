import React from "react";
import ReactDOM from "react-dom/client";
import {createBrowserRouter, RouterProvider} from "react-router-dom";

import Layout from "./pages/Layout.js";
import ErrorPage from "./pages/ErrorPage.js";
import Home from "./pages/Home.js";
import MapPage from "./pages/MapPage.tsx";
import UserSignUp from "./components/UserSignUp.tsx";
import UserLogin from "./components/UserLogin.tsx";
import UserPage from "./pages/UserPage.tsx";
import {AuthProvider} from "./context/AuthenticationContext.tsx";
import InProgress from "./pages/InProgress.tsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout/>,
        errorElement: <ErrorPage/>,
        children: [
            {
                path: "/",
                element: <Home/>,
            },
            {
                path: "/map",
                element: <MapPage/>,
            },
            {
                path: "/login",
                element: <UserLogin/>
            },
            {
                path: 'register',
                element: <UserSignUp/>
            },
            {
                path: "/hero",
                element: <UserPage/>
            },
            {
                path: "/recycling-1-0-1",
                element: <InProgress/>
            },

        ],
    },
]);

const root = ReactDOM.createRoot(document.getElementById("root") as HTMLElement);
root.render(
    <AuthProvider>
        <React.StrictMode>
            <RouterProvider router={router}/>
        </React.StrictMode>
    </AuthProvider>
);