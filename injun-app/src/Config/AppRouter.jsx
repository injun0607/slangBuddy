import React from "react";
import { Route, Routes, Link } from "react-router-dom";
import ChatHam from "../components/Chat/ChatHam";
import ChatMeta from "../components/Chat/ChatMeta";
import ChatTest from "../components/Chat/ChatTest";
import ChateSelectBox from "../components/Common/ChatSelectBox";
import Home from "../components/Common/Home";
import Brain from "../components/Brain/Brain";
import RandomBox from "../components/RandomBox/RandomBox";

const AppRouter = () =>{
    return(
        <Routes>
            <Route path="/" element={<Home/>}></Route>
            <Route path="/chat" element={<ChateSelectBox/>}></Route>
            <Route path="/chat/ham" element={<ChatHam/>}></Route>
            <Route path="/chat/meta" element={<ChatMeta/>}></Route> 
            <Route path="/chat/test" element={<ChatTest/>}></Route>
            <Route path="/brain" element={<Brain/>}></Route>
            <Route path="/box" element={<RandomBox/>}></Route>
        </Routes>
    )
}

export default AppRouter;