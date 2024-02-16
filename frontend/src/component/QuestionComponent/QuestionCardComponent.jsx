import React,{useState} from 'react'
import {convertTimestamp} from '../UtilFunction/timeConvert'
import { getQuestionId } from '../Auth/authSlice';
import { useDispatch } from 'react-redux'
import { useSelector } from 'react-redux'
export default function CardComponent({ setAllQuestionVisable, setQuestionDescription, cardData }) {
    const dispatch=useDispatch();
    const token=useSelector((state)=>state.auth.token)
    const visable = () => {
        setAllQuestionVisable(false)
        setQuestionDescription(true)
        console.log(cardData.id);
        dispatch(getQuestionId(cardData.id))
    }

    return (
        <>
            <div className="mx-auto w-full max-w-7xl  py-0">
                <div className="mx-auto  max-w-7xl md:my-6">
                    <div className="overflow-hidden rounded-xl bg-white p-4 shadow">
                        <p className="text-2xl font-bold text-gray-900 float-left" onClick={visable}>{cardData.title}</p>
                        <div className="mt-10 ">
                            <p className="text-base  text-gray-900">{cardData.body}</p>
                        </div>
                        <div className="mt-2">
                            {cardData.tags.map((tag, key) => (
                                <span key={key}className=" rounded-full bg-gray-100 px-2 text-xs font-semibold leading-5 text-green-800 mr-2 ">
                                    {tag}
                                </span>
                            ))

                            }
                        </div>
                        <div className="flex w-full items-center gap-2 py-6 text-sm text-slate-600">
                            <div className="h-px w-full bg-slate-200 grid grid-cols-3 gap-1">
                                <span onClick={() => { console.log("hello"); }}>Votes:{cardData.voteCount}</span>
                                <span>Posted by: {cardData.username}</span>
                                <span>Posted On: {convertTimestamp(cardData.createdDate)}</span>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

{/* <div className="mx-auto w-full max-w-7xl  py-0">
            <div className="mx-auto  max-w-7xl md:my-6">
                <div className="overflow-hidden rounded-xl bg-white p-4 shadow">
                    <p className="text-2xl font-bold text-gray-900 float-left"onClick={visable}>Title</p>
                    <div className="mt-6 ">
                        <p className="text-base text-gray-900 float-left">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Natus, esse voluptate. Numquam, natus quibusdam. Perferendis, quae maxime delectus quos minus consequuntur necessitatibus! Sit quasi aliquam optio culpa, neque eaque dolore?</p>
                    </div>
                    <div>
                        <span className="inline-flex rounded-full bg-gray-100 px-2 text-xs font-semibold leading-5 text-green-800 mr-2 ">
                            JAVA
                        </span>
                        <span className="inline-flex rounded-full bg-gray-100 px-2 text-xs font-semibold leading-5 text-green-800 mr-2">
                            springboot
                        </span>
                        <span className="inline-flex rounded-full bg-gray-100 px-2 text-xs font-semibold leading-5 text-green-800 mr-2">
                            JVM
                        </span>
                        <span className="inline-flex rounded-full bg-gray-100 px-2 text-xs font-semibold leading-5 text-green-800 mr-2">
                            Error
                        </span>
                    </div>
                    <div className="flex w-full items-center gap-2 py-6 text-sm text-slate-600">
                        <div className="h-px w-full bg-slate-200 grid grid-cols-3 gap-1">
                            <span onClick={()=>{console.log("hello");}}>Votes:0</span>
                            <span>Posted by: ankit</span>
                            <span>Posted On: June 16,2023</span> 
                        </div>
                    </div>            
                    
                </div>
            </div>
        </div> */}
        </>

    )
}
