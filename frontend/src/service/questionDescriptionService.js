export const questionDescription=async(token,userId,questionId)=>{
    console.log("----------------Question Desc--------------");
    console.log(token);
    console.log(questionId);
    console.log(userId);
    const response=await fetch(`http://localhost:8080/api/question/${questionId}/${userId}`,{
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
    const jsonQuestionData= data.questionDto;
    const jsonAnswerData=data.answerDtoList;
    console.log("----------------Question Desc--------------");
    console.log(jsonQuestionData);
    return {jsonQuestionData,jsonAnswerData};
}