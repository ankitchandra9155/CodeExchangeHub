import { Hop } from 'lucide-react';
import React, { useState } from 'react'
import {AiFillLike, AiFillDislike} from 'react-icons/ai'

function LikeButton() {
  const[liked, setLiked] = useState(false);
  const handleClick = () => {
    setLiked(!liked);
  };
  if(liked)
    return (
        <>
    <AiFillLike
      color="blue" 
      size="50" 
      onClick={handleClick}/> 
      <h1>30</h1>
      </>
      )
  return (
  <>
  <AiFillDislike
    color="red" 
    size="50" 
    onClick={handleClick}/>
    <h1 >31</h1>
    </>)  
}

export default LikeButton