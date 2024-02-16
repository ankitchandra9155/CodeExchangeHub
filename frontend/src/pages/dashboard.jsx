import { useState } from "react"
import Navbar from "../component/Navbar"
import FilterComponent from "../component/FilterComponent/FilterComponent"
import Login from "../component/Auth/Login"
import Auth from "./Auth"
import { useSelector } from "react-redux"
import { login } from "../component/Auth/authSlice"
export default function Dashboard() {
    const isAuthenticate=useSelector((state)=>state.auth.isAuthenthicated)
    console.log("My console",isAuthenticate)
   
    
    return (<>
        <Auth/>
        {isAuthenticate&&<Navbar />}
        <div className="mt-[80px]">
        {isAuthenticate&&<FilterComponent />}
        </div>
    </>)
}