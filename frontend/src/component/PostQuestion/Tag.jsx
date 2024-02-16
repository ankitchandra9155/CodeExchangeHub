import { useState } from "react";

export default function Tag({setTaglist,taglist}) {    
    const [tag,setTag]=useState("")
    const handelAdd=()=>{
        setTaglist([...taglist,tag])
        setTag("")
    }
    const handelClose=(key)=>{
        console.log(taglist);
        let updatetag=[...taglist]
        updatetag= updatetag.filter((str,index)=>index!==key)
        setTaglist(updatetag)
        
        
    }
    return (<>
        <div className=" flex flex-col   sm:mx-0">
            <div className="py-2 px-2 rounded  overflow-hidden w-full ">
                <div className="flex mt-8 ">  
                    <div className="flex p-2.5 w-full text-sm text-gray-900  mr-2 mt-3 rounded-lg border border-gray-300 placeholder:text-gray-600 focus:outline-none focus:ring-1 focus:ring-black/30 focus:ring-offset-1 disabled:cursor-not-allowed disabled:opacity-50">
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            className="h-6 w-6 opacity-50 ml-2"
                            fill="none"
                            viewBox="0 0 24 24"
                            stroke="currentColor"
                        >
                            <path
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                strokeWidth={2}
                                d="M7 7h.01M7 3h5c.512 0 1.024.195 1.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A1.994 1.994 0 013 12V7a4 4 0 014-4z"
                            />
                        </svg>
                        <input
                            value={tag}
                            onChange={(e)=>{setTag(e.target.value)}}
                            className="outline-none text-sm sm:text-base ml-1 w-full  border-transparent focus:border-transparent focus:ring-0"
                            type="text"
                            placeholder="Add a tag..."
                        />

                    </div>
                    <button
                        type="button"
                        onClick={handelAdd}
                        className="rounded-md border border-black mt-3 px-3  text-sm font-semibold text-black shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black"
                    >
                        ADD
                    </button>
                </div>
                <div className="my-3 flex flex-wrap -m-1">
                    {taglist.map((tag, key) => (
                        <span key={key} className="m-1 flex flex-wrap justify-between items-center text-xs sm:text-sm bg-gray-200 hover:bg-gray-300 rounded px-4 py-2 font-bold leading-loose cursor-pointer ">
                            {tag}
                            <svg
                                onClick={()=>handelClose(key)}
                                xmlns="http://www.w3.org/2000/svg"
                                className="w-3 h-3 sm:h-4 sm:w-4 ml-4 text-gray-500 hover:text-gray-600"
                                viewBox="0 0 20 20"
                                fill="currentColor"
                            >
                                <path
                                    fillRule="evenodd"
                                    d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                                    clipRule="evenodd"
                                />
                            </svg>
                        </span>
                    ))}
                </div>
            </div>
        </div>

    </>)
}