import { ThreeCircles } from "react-loader-spinner"
import "./Loading.css"
export default function Loading() {
    return (
        <>
           <div className="loader-container">
            <ThreeCircles
                visible={true}
                height="100"
                width="100"
                color="#03001"
                ariaLabel="three-circles-loading"
                wrapperStyle={{}}
                wrapperClass="" />
            </div>
        </>
    )
}