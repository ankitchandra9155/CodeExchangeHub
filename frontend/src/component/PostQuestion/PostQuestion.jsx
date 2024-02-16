import { useState,useEffect } from "react";
import Tag from "./Tag";
import {postQuestion} from  "../../service/postQuestionService"
import { useSelector } from "react-redux";
import { SuccessBanner } from "../Banner/SuccessBanner";
export default function PostQuestion({setPostQuestion}) {
    const[isBannerVisable,setIsBannerVisable]=useState(false)
    const [question, setQuestions] = useState("")
    const [title,setTitle]=useState("")
    const [taglist,setTaglist]=useState([])
    const token=useSelector((state)=>state.auth.token)
    const userId=useSelector((state)=>state.auth.userId)

    useEffect(() => {
        // Hide the component after 3000 milliseconds (3 seconds)
        const timeoutId = setTimeout(() => {
            setIsBannerVisable(false);
        }, 3000);
    
        // Clean up the timeout when the component is unmounted
        return () => clearTimeout(timeoutId);
      }, [isBannerVisable]);
      

    const handelPostQuestion=async(e)=>{
        e.preventDefault();
        const postQuestionData={
            "title":title,
            "body":question,
            "tags":taglist,
            "userId":userId
        }
       
        const {jsonData,isError}=await postQuestion(postQuestionData,token)
        if(isError)
        {
            setTaglist([])
            setQuestions("")
            setTitle("")
        }
        console.log("Post Question",jsonData);
        setIsBannerVisable(true)

    }
    
    return (<>
         { isBannerVisable && <SuccessBanner bannerToShow={"Question is Posted Successfully"}/>}
        <section className="px-2">
          
            <div className="mx-auto max-w-7xl py-5 relative">
                <div>
                    <div className="max-w-7xl">
                        <h1 className="text-2xl font-bold text-black">Post Question</h1>
                    </div>
                    <div className="mt-3">
                        <input
                            value={title}
                            onChange={(e)=>{setTitle(e.target.value)}}
                            className="flex h-[50px] w-full rounded-md border border-black/30 bg-transparent px-3 py-2 text-sm placeholder:text-gray-600 focus:outline-none focus:ring-1 focus:ring-black/30 focus:ring-offset-1 disabled:cursor-not-allowed disabled:opacity-50"
                            type="text"
                            placeholder="Title"
                            
                        ></input>
                    </div>
                    <textarea id="message" rows="10" onChange={(e)=>{setQuestions(e.target.value)}} value={question} className="block p-2.5 w-full text-sm text-gray-900  mt-3 rounded-lg border border-gray-300 placeholder:text-gray-600 focus:outline-none focus:ring-1 focus:ring-black/30 focus:ring-offset-1 disabled:cursor-not-allowed disabled:opacity-50" placeholder="Question Description....."></textarea>
                </div>
                <div className="">
                    <Tag setTaglist={setTaglist} taglist={taglist}/>
                </div>
                <button
                    type="button"
                    onClick={handelPostQuestion}
                    className="rounded-md border border-black absolute top-[590px] right-0   px-3 py-2 text-sm font-semibold text-black shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black"
                >
                    Post Question
                </button>

            </div>
        </section>


    </>)
}