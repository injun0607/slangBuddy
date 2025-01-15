import React, { useState, useEffect } from "react";
import axios from "axios";
//


const ChatHam = () =>{
    const [chatData,setChatData] = useState([]);
    

    useEffect(()=>{
        axios.get("http://localhost:8080/chat/HAM")
            .then((response) => setChatData(response.data))
            .catch((err) => console.error(err));
    },[]);
    //data.

    return(
        <div>
            <h1>대화내역 불러오기</h1>
            {chatData.map((chat,index)=>(
                <div key={index}>
                    <div style={{textAlign: 'right'}}>
                        <h3>사용자: </h3>
                        <span>{chat.description}</span>
                    </div>
                    <div style={{textAlign: 'left'}}>
                        <h3>chat: </h3>
                        <span>{chat.answer}</span>
                    </div>
                </div>
            ))}
        </div>
    )
}

export default ChatHam;