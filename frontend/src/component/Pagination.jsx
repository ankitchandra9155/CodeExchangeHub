import React, { useState } from 'react';
import { pageNumber } from './Auth/authSlice';
import { useDispatch } from 'react-redux'
export function Pagination({ totalPages }) {
  const dispatch=useDispatch();
  const [currentPage, setCurrentPage] = useState(1);

  const pages = Array.from({ length: Math.min(totalPages, 3) }, (_, index) => index + currentPage);

  const handleClickPrevious = () => {
    if (currentPage > 1) {
      setCurrentPage((prevPage) => prevPage - 3);
    }
  };

  const handleClickNext = () => {
    if (currentPage + 3 <= totalPages) {
      setCurrentPage((prevPage) => prevPage + 3);
    }
  };

  const handleCurrentPage=(page)=>{
    dispatch(pageNumber(page-1));
  }

  return (
    <div className="ml-[230px] mb-4 flex items-center">
      <a
        href="#"
        onClick={handleClickPrevious}
        className={`mx-1 ${currentPage === 1 ? 'cursor-not-allowed' : 'cursor-pointer'} text-sm font-semibold text-gray-900`}
      >
        &larr; Previous
      </a>
      {pages.map((page) => (
        <a
          key={page}
          href="#"
          onClick={()=>handleCurrentPage(page)}
          className="mx-1 flex items-center rounded-md border border-gray-400 px-3 py-1 text-gray-900 hover:scale-105"
        >
          {page}
        </a>
      ))}
      <a
        href="#"
        onClick={handleClickNext}
        className={`mx-1 ${currentPage + 3 > totalPages ? 'cursor-not-allowed' : 'cursor-pointer'} text-sm font-semibold text-gray-900`}
      >
        Next &rarr;
      </a>
    </div>
  );
}
