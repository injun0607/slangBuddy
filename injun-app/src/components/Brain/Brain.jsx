
import React, { useState } from 'react';

function Brain() {

    return (
        <div className="App">
            <header>
                <h1>Brainstorming Hub</h1>
            </header>
            <BrainstormingBoard />
        </div>
    )
}

function BrainstormingBoard(){
    const [ideas,setIdeas] = useState([]);
    const [inputValue , setInputValue] = useState("");

    const addIdeas = (e) => {
        
        if(!inputValue.trim()){
            return;
        }
        console.log('하이');
        setIdeas([...ideas,inputValue]);
    }

    return (
        <div>
            <ul>
                {ideas.map((idea,idx)=>(
                    <li key={idx}>{idea}</li>
                ))}
            </ul>
            <input type="text"
                onChange={(e)=>setInputValue(e.target.value)}
            />
            <button
            onClick={addIdeas}>저장</button>
        </div>
    )


}



function BrainstormingBoard2() {
    const [ideas, setIdeas] = useState(["아이디어 1", "아이디어 2", "아이디어 3"]);
    // 선택된 아이디어의 인덱스 (null이면 선택 안 됨)
    const [selectedIdea, setSelectedIdea] = useState(null);
    // input에 입력되는 값
    const [inputValue, setInputValue] = useState("");
    // 연결된 아이디어 정보를 저장 (어떤 아이디어와 어떤 연결 텍스트가 연결됐는지)
    const [connections, setConnections] = useState([]);
  
    // 아이디어 클릭 시 해당 아이디어를 선택하고 input 초기화
    const handleIdeaClick = (index) => {
      setSelectedIdea(index);
      setInputValue("");
    };
  
    // input 값을 제출하면 연결 정보에 추가
    const handleConnectionSubmit = (e) => {
      e.preventDefault();
      if (inputValue.trim() === "") return;
      setConnections([...connections, { ideaIndex: selectedIdea, connectionText: inputValue }]);
      setSelectedIdea(null);
      setInputValue("");
    };
  
    return (
      <div className="relative p-8">
        <h1 className="text-3xl font-bold mb-6">Brainstorming Hub</h1>
        
        {/* 아이디어 목록: 각 아이디어를 원으로 표시 */}
        <div className="flex flex-wrap gap-4">
          {ideas.map((idea, index) => (
            <div
              key={index}
              className="bg-blue-500 text-white rounded-full w-24 h-24 flex items-center justify-center cursor-pointer hover:bg-blue-600"
              onClick={() => handleIdeaClick(index)}
            >
              {idea}
            </div>
          ))}
        </div>
  
        {/* 선택된 아이디어가 있을 때, 연결 input 영역 표시 */}
        {selectedIdea !== null && (
          <div className="mt-8">
            <h2 className="text-xl font-semibold mb-4">연결할 아이디어를 입력해봐</h2>
            <div className="flex items-center">
              {/* 선택된 아이디어 (원 형태) */}
              <div className="bg-blue-500 text-white rounded-full w-24 h-24 flex items-center justify-center">
                {ideas[selectedIdea]}
              </div>
              {/* 간단한 연결선을 SVG로 표시 */}
              <svg className="w-16 h-2 mx-4" viewBox="0 0 100 10">
                <line x1="0" y1="5" x2="100" y2="5" stroke="black" strokeWidth="2" />
              </svg>
              {/* 연결할 아이디어를 입력받는 input */}
              <form onSubmit={handleConnectionSubmit} className="flex-grow">
                <input
                  type="text"
                  value={inputValue}
                  onChange={(e) => setInputValue(e.target.value)}
                  placeholder="연결할 아이디어 입력..."
                  className="w-full border border-gray-300 rounded p-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
                />
              </form>
            </div>
          </div>
        )}
  
        {/* 이미 연결된 아이디어 목록 표시 */}
        {connections.length > 0 && (
          <div className="mt-12">
            <h2 className="text-2xl font-semibold mb-4">연결된 아이디어</h2>
            <ul className="space-y-4">
              {connections.map((conn, idx) => (
                <li key={idx}>
                  <div className="flex items-center">
                    <div className="bg-blue-500 text-white rounded-full w-24 h-24 flex items-center justify-center">
                      {ideas[conn.ideaIndex]}
                    </div>
                    <svg className="w-16 h-2 mx-4" viewBox="0 0 100 10">
                      <line x1="0" y1="5" x2="100" y2="5" stroke="black" strokeWidth="2" />
                    </svg>
                    <span className="border border-gray-300 rounded p-2">{conn.connectionText}</span>
                  </div>
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>
    );
}


export default Brain;