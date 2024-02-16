export const postAnswer=async(token,body,questionId,userId)=>{

    console.log("Post Answer Service");
    const response=await fetch(`http://localhost:8080/api/answer`,{
        method:"POST",
        headers:{
            "Authorization":token,
            "Content-Type":"application/json",
        },
        body:JSON.stringify({body:body,questionId:questionId,userId:userId})
    })
    if(!response.ok)
    {
        console.log("Error in fetching Api");
    }
    const jsonData=await response.json()
    console.log("Post Question Service Service",jsonData);
    return {jsonData};

}