export const pagination=async(pageNumber,token)=>{

    console.log("Ankittttttt");
    const response=await fetch(`http://localhost:8080/api/questions/${pageNumber}`,{
        method:"GET",
        headers:{
            "Authorization":token,
            "Content-Type":"application/json",
        },
    })
    if(!response.ok)
    {
        console.log("Error in fetching Api");
    }
    const data=await response.json()
    const jsonQuestionData= data.questionDtoList;
    const totalPages= data.totalPages;
    // console.log("Service",jsonQuestionData);
    return {jsonQuestionData,totalPages};

}