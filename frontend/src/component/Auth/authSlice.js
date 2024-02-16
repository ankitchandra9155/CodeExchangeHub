import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    token:null,
    userId:null,
    userName: null,
    userEmail: null,
    isAuthenthicated: false,
    logIn: true,
    signUp: false,
    page:0,
    questionId:null,
    searchValue:""
}

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login: (state, action) => {
            state.isAuthenthicated = false;
            state.logIn = true;
            state.signUp = false;
        },
        signUp: (state, action) => {
            state.isAuthenthicated = false
            state.logIn = false;
            state.signUp = true;
        },
        signupSuccess: (state, action) => {
            state.isAuthenthicated = false
            state.logIn = true;
            state.signUp = false;
        },
        loginSuccess: (state, action) => {
            state.isAuthenthicated = true
            state.logIn = false;
            state.signUp = false;
            state.userName=action.payload.userName;
            state.userEmail=action.payload.userEmail;
            state.userId=action.payload.userId;
            state.token=action.payload.token;
        },
        pageNumber:(state,action)=>{
            state.page=action.payload;
        },
        getQuestionId:(state,action)=>{
            state.questionId=action.payload;
        },
        getSearchValue:(state,action)=>{
            state.searchValue=action.payload;
        }

    }
})

export const { login, signUp, signupSuccess,loginSuccess,pageNumber,getQuestionId ,getSearchValue} = authSlice.actions
export default authSlice.reducer