import { useState, useEffect, useCallback } from "react";
import axios from "axios";

export const useFetchData = (url , method = "GET" , body) => {

    const[data , setData] = useState(null);
    const[loading , setLoading] = useState(false);
    const[error , setError] = useState(null);


    const axiosCall = useCallback(async () =>{
        let response;

        setLoading(true);
        setError(null);

        try{
            if(method === "GET"){
                response = await axios.get(url);
            }else if(method === "POST"){
                response = await axios.post(url,body);
            }
            setData(response.data);
        } catch(err){
            setError(err);
        }finally{
            setLoading(false);
        }
    },[url,method,body])


    useEffect(()=>{
        axiosCall();
    },[axiosCall])

    return { data, loading, error, refetch: useFetchData };

};