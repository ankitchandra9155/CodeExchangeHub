import apiUrl from "../component/UtilFunction/apiUrl";
export const search=async(title,pageNumber,token)=>{

    
    const response=await fetch(apiUrl+`/api/search/${title}/${pageNumber}`,{
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
    console.warn(jsonQuestionData);
    return {jsonQuestionData,totalPages};

}