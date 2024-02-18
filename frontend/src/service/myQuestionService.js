import apiUrl from "../component/UtilFunction/apiUrl";
export const myQuestion=async(userId,pageNumber,token)=>{

    console.log("Inside My Question service");
    const response=await fetch(apiUrl+`/api/questions/${userId}/${pageNumber}`,{
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