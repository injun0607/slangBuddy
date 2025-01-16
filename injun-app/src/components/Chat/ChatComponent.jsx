import React,{useState} from "react";
import { useFetchData } from "./useFetch";

const ChatComponent = ({chatData, type="META"}) => {

    cosnt [chatDTO , setChatDTO] = useState({description: "", template: type})

    const [message,setMessage] = useState(chatData);
    const [newMessage, setNewMessage] = useState("");


    const handleSendMessage = async() =>{
        if(!newMessage.trim()){
            return;
        }

        setChatDTO({description: newMessage, template:type})

        = useFetchData("http://localhost:8080/chat","POST",chatDTO);
    }

    


    return (
        <div className="flex flex-col h-screen bg-gray-100">
            <div className="bg-blue-600 text-white text-center py-4 font-bold text-lg">
                대화 불러오기
            </div>
            <div className="flex-1 overflow-y-auto p-4 space-y-4">    
                {(message || [] ).map((chat,index)=>(
                    <div key={index} >
                        <div className="justify-end">
                            <div className="max-w-xs p-4 rounded-lg shadow-md bg-blue-500 text-white">
                                <span>사용자: {chat.description}</span>
                            </div>
                        </div>
                        <div className="justify-start">
                            <div className="max-w-xs p-4 rounded-lg shadow-md bg-gray-200 text-gray-800">
                                <span> chat: {chat.answer}</span>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            <div className="flex items-center p-4 bg-white border-t border-gray-300">
                <input
                    type="text"
                    placeholder="메시지를 입력하세요..."
                    value={newMessage}
                    onChange={(e) => setNewMessage(e.target.value)}
                    className="flex-1 border border-gray-300 rounded-lg px-4 py-2 text-sm focus:outline-none focus:ring focus:ring-blue-300"
                />
                <button
                    onClick={handleSendMessage}
                    className="ml-2 px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 focus:outline-none"
                    >
                전송
                </button>
            </div>
        </div>
    )
}

export default ChatComponent;