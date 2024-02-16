import CardComponent from "./QuestionCardComponent"
export default function AllquestionComponents({setAllQuestionVisable,setQuestionDescription,displayQuestion})
{
    // console.log("AllQuestion--",displayQuestion);
    return(<>
     {
     displayQuestion.map((cardData,key)=>(
        <CardComponent key={key} setAllQuestionVisable={setAllQuestionVisable} setQuestionDescription={setQuestionDescription} cardData={cardData}/>
        ))
     }  
        
        
    </>)
}