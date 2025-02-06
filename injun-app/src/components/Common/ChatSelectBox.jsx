import React, { useState,useEffect } from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import axios from "axios";

const ChateSelectBox = () =>{

    return (
        <div>
            <div className="px-6 py-2 bg-blue-500 text-white font-bold rounded-lg shadow-md hover:bg-blue-600"><Link to="/chat/ham">Ham 이동</Link></div>
            <div className="px-6 py-2 bg-blue-500 text-white font-bold rounded-lg shadow-md hover:bg-blue-600"><Link to="/chat/meta">Meta 이동</Link></div>
            <div className="px-6 py-2 bg-blue-500 text-white font-bold rounded-lg shadow-md hover:bg-blue-600"><Link to="/chat/test">Test 이동</Link></div>
        </div>
    )


}

export default ChateSelectBox;