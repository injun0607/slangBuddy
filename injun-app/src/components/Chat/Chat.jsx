import React, { useState } from "react";


const ChatScreen = () => {
  const [messages, setMessages] = useState([]); // 채팅 메시지 리스트
  const [input, setInput] = useState(""); // 사용자가 입력 중인 메시지

  // 메시지를 전송하는 함수
  const sendMessage = async () => {
    if (!input.trim()) return; // 빈 메시지 무시

    // 1. 사용자가 보낸 메시지를 추가
    const userMessage = { type: "user", text: input };
    setMessages((prev) => [...prev, userMessage]);

    // 2. AI 응답 가져오기
    try {
      const response = await fetch("http://localhost:8080/chat", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ message: input }),
      });

      console.log(response);
      const data = await response.json(); // AI 응답
      const aiMessage = { type: "ai", text: data.data };

      // 3. AI 메시지를 추가
      setMessages((prev) => [...prev, aiMessage]);
    } catch (error) {
      console.error("Error fetching AI response:", error);
    }

    // 4. 입력 필드 초기화
    setInput("");
  };

  return (
    <div style={{ maxWidth: "500px", margin: "0 auto", padding: "20px" }}>
      <div
        style={{
          border: "1px solid #ccc",
          borderRadius: "8px",
          padding: "10px",
          height: "400px",
          overflowY: "scroll",
          marginBottom: "10px",
        }}
      >
        {messages.map((msg, index) => (
          <div
            key={index}
            style={{
              textAlign: msg.type === "user" ? "right" : "left",
              margin: "5px 0",
            }}
          >
            <div
              style={{
                display: "inline-block",
                padding: "10px",
                borderRadius: "8px",
                backgroundColor: msg.type === "user" ? "#d1e7ff" : "#f8d7da",
                color: "#333",
              }}
            >
              {msg.text}
            </div>
          </div>
        ))}
      </div>
      <div>
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="메시지를 입력하세요..."
          style={{
            width: "80%",
            padding: "10px",
            borderRadius: "5px",
            border: "1px solid #ccc",
          }}
        />
        <button
          onClick={sendMessage}
          style={{
            padding: "10px 20px",
            marginLeft: "10px",
            borderRadius: "5px",
            border: "none",
            backgroundColor: "#007bff",
            color: "#fff",
          }}
        >
          전송
        </button>
      </div>
    </div>
  );
};

export default ChatScreen;