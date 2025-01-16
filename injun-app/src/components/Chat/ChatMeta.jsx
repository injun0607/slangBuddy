import axios from "axios";
import React, { useState,useEffect } from "react";
import { useFetchData } from "../../hooks/useFetch";
import ChatComponent from "./ChatComponent";

const ChatMeta = () =>{

    const {data:chatData, loading, error} = useFetchData("http://localhost:8080/chat/META");
    

    return(
        <div>
            <ChatComponent chatData={chatData}></ChatComponent>
        </div>
    )
}

export default ChatMeta;