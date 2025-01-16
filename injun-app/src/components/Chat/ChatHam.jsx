import React, { useState, useEffect } from "react";
import axios from "axios";
import { useFetchData } from "../../hooks/useFetch";
import ChatComponent from "./ChatComponent";

const ChatHam = () =>{

    const { data: chatData, loading, error } = useFetchData("http://localhost:8080/chat/HAM");

    return(
        <div>
            <ChatComponent chatData={chatData} type="HAM"></ChatComponent>
        </div>
    )
}

export default ChatHam;