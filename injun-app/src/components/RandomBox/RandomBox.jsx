import { useState,useEffect } from "react";


function RandomBox(){

    //랜덤박스 아이템 리스트
    const boxItemList = [ 
        { id: 'wood', name: '나무', probability: 0.4 },
        { id: 'stone', name: '돌', probability: 0.3 },
        { id: 'iron', name: '철', probability: 0.2 },
        { id: 'gold', name: '금', probability: 0.1 }
    ]

    const [userItemList,setUserItemList] = useState([]);

    const recipes = [
        {
          id: 'sword',
          name: '검',
          ingredients: { wood: 3, stone: 2 }
        }
    ];

    //클릭시 작동하는 메소드
    const clickBox = () => {

        let random = Math.random();
        let cumulative = 0;
        let selectedItem = null;
        for (let item of boxItemList){
            cumulative += item.probability;
            if(random <= cumulative){
                selectedItem = item;
                break;
            }
        }

        console.log('앙녕')
        setUserItemList((prev)=>([...prev,selectedItem]));
    }

    //조합시 작동하는 메소드
    // const mixItem = () => {
    //     {userItemList.map((item,idx)=>{
    //         console.log({item});
    //     })}
    // }


    return(
        <div>
            <div>
                <button className="px-6 py-2 bg-blue-500 text-white font-bold rounded-lg shadow-md hover:bg-blue-600"
                    onClick={clickBox}
                >click here</button>
                <button className="px-6 py-2 bg-blue-500 text-white font-bold rounded-lg shadow-md hover:bg-blue-600"
                    onClick={clickBox}
                >조합하기</button>
            </div>
            <div>
                <span>유저 아이템리스트</span>
                <ul>
                    {userItemList.map((item,idx)=>(
                        <li key={idx}>
                            <h1>{item.name}</h1>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    )



}

export default RandomBox;