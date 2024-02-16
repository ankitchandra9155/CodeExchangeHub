import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../component/Auth/authSlice";

const store=configureStore({
    reducer:{
        auth:authReducer
    }
});
export default store