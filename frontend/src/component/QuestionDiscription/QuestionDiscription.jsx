import React, { useEffect, useState } from 'react'
import AnswersComponent from './AnswersComponent'
import PostAnswer from './PostAnswer'
import { useSelector } from 'react-redux'
import { questionDescription } from '../../service/questionDescriptionService'
export default function QuestionDiscription({ setAllQuestionVisable, setQuestionDescription, setIsDashBoardVisible }) {
    const token = useSelector((state) => state.auth.token);
    const userId = useSelector((state) => state.auth.userId);
    const questionId = useSelector((state) => state.auth.questionId);

    const [title, setTitle] = useState("");
    const [body, setBody] = useState("");
    const [username, setuserName] = useState("");
    const [createdDate, setCreatedDate] = useState();
    const [tags, setTags] = useState([]);
    const [voteCount, setVoteCount] = useState(0);
    const [answerData, setAnswerData] = useState()
    const [update,setUpdate]=useState(false);


    const [loading, setLoading] = useState(true)

    useEffect(() => {
        async function fetchData() {
            console.log("inside use effect");
            // setLoading(true);
            setTimeout(async () => {
                const { jsonQuestionData, jsonAnswerData } = await questionDescription(token, userId, questionId)
                // console.log("This is Answer data", jsonAnswerData);
                setAnswerData(jsonAnswerData)
                setLoading(false)
                setTitle(jsonQuestionData.title);
                setBody(jsonQuestionData.body);
                setTags(jsonQuestionData.tags);
            }, 100);

        }
        fetchData();
        setAllQuestionVisable(false);
        setQuestionDescription(true);
        setIsDashBoardVisible(false);

        setUpdate(false);
    }, [update])


    return (

        <section className="px-2">
            {loading ? (<p>Loading...</p>) : (
                <div className="mx-auto max-w-7xl py-5">
                    <div>
                        <div className="max-w-7xl">
                            <h1 className="text-2xl font-bold text-black">{title}</h1>
                            <p className="mt-4 text-base leading-6 tracking-wide text-black">
                                {body}
                            </p>
                            <div>
                                {tags.map((tag, index) => (
                                    <span className="inline-flex rounded-full bg-gray-100 px-2 text-xs font-semibold leading-5 text-green-800 mr-2 ">
                                        {tag}
                                    </span>

                                ))}
                            </div>
                        </div>
                        <h1 className="text-xl font-bold text-black mt-3 ">All Answers</h1>
                        <div className="h-[290px] overflow-y-auto py-3">
                            {answerData.map((answer, index) => (
                                <AnswersComponent
                                    key={index}
                                    body={answer.body}
                                    username={answer.username}
                                    createdDate={answer.createdDate}
                                />
                            ))}
                        </div>
                        <h1 className="text-xl font-bold text-black mt-3 ">Your Answers</h1>
                        <PostAnswer token={token} questionId={questionId} userId={userId} setUpdate={setUpdate} setLoading={setLoading}/>
                    </div>
                </div>
            )}
        </section>
    )
}
