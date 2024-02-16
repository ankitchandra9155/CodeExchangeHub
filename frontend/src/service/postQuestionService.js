import { useSelector } from "react-redux";

export const postQuestion=async(postQuestionData,token)=>{
    let isError=false;
    console.log("Bearer "+token.replace(/^Bearer\s/, ''));
    const response= await fetch("http://localhost:8080/api/question",{
        method:'Post',
        headers:{
            'Content-Type': 'application/json',
            'Authorization': token
        },
        body:JSON.stringify({title:postQuestionData.title,body:postQuestionData.body,tags:postQuestionData.tags,userId:postQuestionData.userId})
    })
    if(!response.ok)
    {
        console.log("Error in post question");
        isError=true;
    }
    // console.log(await response.json());
    const jsonData=await response.json();
    console.log(jsonData);
    return {jsonData,isError}

}