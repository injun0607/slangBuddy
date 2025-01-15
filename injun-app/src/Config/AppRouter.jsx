import React from "react";
import { Route, Routes, Link } from "react-router-dom";
import ChatHam from "../components/Chat/ChatHam";
import ChatMeta from "../components/Chat/ChatMeta";
import ChateSelectBox from "../components/Common/ChatSelectBox";
import Home from "../components/Common/Home";


const AppRouter = () =>{
    return(
        <Routes>
            <Route path="/" element={<Home/>}></Route>
            <Route path="/chat" element={<ChateSelectBox/>}></Route>
            <Route path="/chat/ham" element={<ChatHam/>}></Route>
            <Route path="/chat/meta" element={<ChatMeta/>}></Route>            
        </Routes>
    )
}

export default AppRouter;