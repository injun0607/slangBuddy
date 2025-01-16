import React,{useState,useEffect, useCallback} from "react";
import { useFetchData } from "./useFetch";

export const useChat = (message,type) =>{

    //하려는것 message, type 받아서 전송
    const messageBox = {message,type}
    const [response,setResponse] = useState(null);



    const sendMessage = useCallback(async()=>{
         
        
    },[message,type])


    return response;

} 