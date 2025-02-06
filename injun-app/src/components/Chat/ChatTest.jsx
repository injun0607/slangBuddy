import React,{useState} from "react";
import "./ChatScreen.css";
import { useFetchData } from "../../hooks/useFetch";


const ChatTest= (type = "META") =>{
    //데이터 받아오고 
    //TYPE주고, 
    const {data:chatData , loading, error} = useFetchData(`http://localhost:8080/chat/META`);
    

    //데이터 받아오는 부분 따로만들고
    const [messages, setMessages] = useState([
        { id: 1, sender: "AI", text: "안녕하세요! 무엇을 도와드릴까요?" },
        { id: 2, sender: "USER", text: "AI와 대화해보고 싶어요." },
    ]);

    const [inputValue,setInputValue] = useState("");

    const handleSend = () =>{
        if(!inputValue.trim()){
            return;
        }

        setMessages([...messages,{id:messages.length+1, sender: "USER", text: inputValue}])
        setInputValue("");
    };

    if(loading){
        return (
            <div>로딩중입니다.</div>
        )
    }

    return(
        <div className="chat-container">
            {/* Header*/}
            <div className="chat-header">
                <h3>AI 채팅</h3>
                <span>현재 대화 중...</span>
            </div>

            {/*Chat Messages*/}
            <div className="chat-messages">
                {messages.map((message)=>(
                    <div
                        key={message.id}
                        className={`message-bubble ${message.sender === "USER" ? "user-message" : "ai-message"}`}
                    >
                        {message.text}
                    </div>
                ))}
            </div>

            {/* Inpur Field */}
            <div className="chat-input">
                <input 
                    type="text"
                    placeholder="메시지 입력.."
                    value={inputValue}
                    onChange={(e)=> setInputValue(e.target.value)}
                />
                <button onClick={handleSend}>전송</button>
            </div>
        </div>
    )



}

export default ChatTest;