import React from "react";
import { Link } from "react-router-dom";

const Home = () =>{

    return(
        <div>
            <h1>홈 입니다</h1>
            <nav>
                <button className="px-6 py-2 bg-blue-500 text-white font-bold rounded-lg shadow-md hover:bg-blue-600">
                    <Link to='/login'>로그인 하기</Link>
                </button>
                <button className="px-6 py-2 bg-blue-500 text-white font-bold rounded-lg shadow-md hover:bg-blue-600">
                    <Link to='/chat'>채팅하기</Link>
                </button>
            </nav>
        </div>
    )
}

export default Home;