import { useState } from "react";
import Login from "../component/Auth/Login";
import Signup from "../component/Auth/Signup";
import { useSelector } from "react-redux";


export default function Auth() {
    const isLogin = useSelector((state) => state.auth.logIn)
    const isSignUp = useSelector((state) => state.auth.signUp)

    return (<>
        {isLogin && <Login />}
        {isSignUp && <Signup />}
    </>)
}