import axios from "axios";
import React, { useState,useEffect } from "react";

const ChatMeta = () =>{

    const [chatData, setChatData] = useState([]);

    useEffect(()=>{
        axios.get("http://localhost:8080/chat/META")
        .then((response)=>{console.log(response.data)})
        .catch((err) => console.error(err));

    },[])

    return(
        <div>
            Ham입니다.
        </div>
    )
}

export default ChatMeta;