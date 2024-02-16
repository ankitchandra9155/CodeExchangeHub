import React,{useEffect, useState} from 'react'
import { questionDescription } from '../../service/questionDescriptionService';
import { convertTimestamp } from '../UtilFunction/timeConvert';
export default function AnswersComponent({body,username,createdDate}) {

    return (<>
        <div className="rounded-md border border-black/30 p-4 mt-2">
            <dd className="mt-2 text-base text-black-500">
                {body}
            </dd>
            <div class="flex w-full items-center gap-2 py-6 text-sm text-slate-600">
                <div class="h-px w-full bg-slate-200 grid grid-cols-3 gap-1">
                    <span>Posted by: {username}</span>
                    <span>Posted On: {convertTimestamp(createdDate)}</span>
                </div>
            </div>
        </div>
    </>)
}