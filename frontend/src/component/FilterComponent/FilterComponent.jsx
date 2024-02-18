import React, { useEffect, useState } from 'react'
import { Plus } from 'lucide-react'
import ReactLoading from 'react-loading'
// import CardComponent from '../QuestionComponent/QuestionCardComponent'
import { search } from '../../service/searchService'
import { Pagination } from '../Pagination'
import AllquestionComponents from '../QuestionComponent/AllquestionComponents'
import QuestionDiscription from '../QuestionDiscription/QuestionDiscription'
import PostQuestion from '../PostQuestion/PostQuestion'
import { pagination } from '../../service/paginationService'
import { myQuestion } from '../../service/myQuestionService'
import Loading from '../LoadingComponent/Loading'
import { useDispatch, useSelector } from 'react-redux'
import { getSearchValue } from '../Auth/authSlice'

export default function FilterComponent() {
  const [loading, setLoading] = useState(true)
  const [allQuestionVisable, setAllQuestionVisable] = useState(true)
  const [myQuestionvisibility, setMyQuestionVisibility] = useState(false)
  const [questionDescription, setQuestionDescription] = useState(false)
  const [postQuestion, setPostQuestion] = useState(false)
  const [displayQuestion, setDisplayQuestion] = useState()
  const [isDashBoardVisible,setIsDashBoardVisible]=useState(true)
  // const [pageNumber,setPageNumber]=useState()
  const [totalPages, setTotalPages] = useState(1);
  const token = useSelector((state) => state.auth.token)
  const pageNumber = useSelector((state) => state.auth.page);
  const userId = useSelector((state) => state.auth.userId);
  const searchValue=useSelector((state)=>state.auth.searchValue);
  // console.log("Page number is ", pageNumber);
  console.log("Search Value is ",searchValue);
  
  const handleSearch=async()=>{
    if(searchValue!="")
    {
      setLoading(true)
      setTimeout(async()=>{
        const {jsonQuestionData,totalPage}= await search(searchValue,0,token);
        console.log("searchData",jsonQuestionData);
        setDisplayQuestion(jsonQuestionData)
        setTotalPages(totalPage)
        // setIsDashBoardVisible(false)
        setLoading(false)
      },200)
      // useDispatch(getSearchValue(""))
    }
  }

  useEffect(()=>{
     
  },[displayQuestion])
  
  const postQuestionVisable = () => {
    if (allQuestionVisable)
      setAllQuestionVisable(false)

    if (questionDescription)
      setQuestionDescription(false)

    if(isDashBoardVisible)
      setIsDashBoardVisible(false)
    
    // if(myQuestionvisibility)
    //   setMyQuestionVisibility(false)

    if (postQuestion) { setPostQuestion(false); setAllQuestionVisable(true); }
    else
      setPostQuestion(true)

  }

  const dashBoardVisible = () => {
    console.log("handling dashboard visibility");
    if (!isDashBoardVisible) {
      console.log("handling dashboard visibility 2");
      setAllQuestionVisable(true)
      setQuestionDescription(false)
      setPostQuestion(false)
      
      setLoading(true)
      setMyQuestionVisibility(false)
      setIsDashBoardVisible(true)
    }
    
    
  }

  const myQuestionVisible = () => {
    console.log("Inside My question Visible");
    if(isDashBoardVisible || postQuestion){
    console.log("Inside My question Visible 2");
    setIsDashBoardVisible(false)
    setPostQuestion(false)
    setMyQuestionVisibility(true)
    setAllQuestionVisable(true)
    if(!loading)
      setLoading(true)
  }
}



  useEffect(() => {
    async function fetchData() {
      console.log("inside use effect");
      // setLoading(true);
      setTimeout(async () => {
        let jsonQuestionData;
        let totalPages;
        if (!myQuestionvisibility) {
          const result = await pagination(pageNumber, token)
          jsonQuestionData=result.jsonQuestionData;
          totalPages=result.totalPages;
        }
        else {
          const result = await myQuestion(userId, pageNumber, token)
          
          jsonQuestionData=result.jsonQuestionData;
          totalPages=result.totalPages;
        }
        // const {jsonQuestionData,totalPages}={myQuestionvisibility?(await myQuestion(userId, pageNumber, token)):(await pagination(pageNumber, token))}
        setLoading(false)
        console.log("inside use effect 2");
        setDisplayQuestion(jsonQuestionData)
        setTotalPages(totalPages)
      }, 200);
    }
    fetchData();
  }, [pageNumber,allQuestionVisable,isDashBoardVisible,questionDescription]);

  
  useEffect(()=>{
    handleSearch()
  },[searchValue])

  return (
    <>

      <section className="w-full ">   
          <div className=" w-full px-1 py-4 lg:px-10">
            <div className="lg:grid lg:grid-cols-12 lg:gap-x-2">
              <div className="hidden space-y-6 divide-y lg:col-span-3 lg:block">
                <div className="flex items-center justify-between p-4 hover:bg-gray-200" onClick={dashBoardVisible}>
                  <h6 className="font-semibold">DashBoard</h6>
                  <span className="block cursor-pointer">
                    <Plus className="h-4 w-4" />
                  </span>
                </div>
                <div className="flex items-center justify-between p-4 hover:bg-gray-200" onClick={myQuestionVisible}>
                  <h6 className="font-semibold">My Question</h6>
                  <span className="block  cursor-pointer">
                    <Plus className="h-4 w-4" />
                  </span>
                </div>
                <div className="flex items-center justify-between p-4 hover:bg-gray-200" onClick={postQuestionVisable}>
                  <h6 className="font-semibold" >Post Question</h6>
                  <span className="block  cursor-pointer">
                    <Plus className="h-4 w-4" />
                  </span>
                </div>
              </div>
              <div className="h-[690px] w-full rounded-lg border-2 border-dashed px-2 lg:col-span-9 lg:min-h-min overflow-y-auto">
              {loading ? (<Loading/>) : 
              (
                <span>
                {allQuestionVisable && <AllquestionComponents setAllQuestionVisable={setAllQuestionVisable} setQuestionDescription={setQuestionDescription} displayQuestion={displayQuestion} />}
                {questionDescription && <QuestionDiscription setAllQuestionVisable={setAllQuestionVisable} setQuestionDescription={setQuestionDescription} setIsDashBoardVisible={setIsDashBoardVisible}/>}
                {postQuestion && <PostQuestion setPostQuestion={setPostQuestion} />}
                </span>
              )}
                
              </div>

            </div>
            {loading ? (<Loading/>) : (
            <div className='flex items-center justify-center  v-screen'>
              <Pagination totalPages={totalPages} />
            </div>
            )}
          </div>
      </section>
    </>
  )
}
