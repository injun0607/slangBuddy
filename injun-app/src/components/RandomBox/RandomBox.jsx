import { useState,useEffect } from "react";
import { useFormState } from "react-dom";


function RandomBox(){

    //랜덤박스 아이템 리스트
    const boxItemList = [ 
        { id: 'wood', name: '나무', probability: 0.4 },
        { id: 'stone', name: '돌', probability: 0.3 },
        { id: 'iron', name: '철', probability: 0.2 },
        { id: 'gold', name: '금', probability: 0.1 }
    ]

    const [userItemList,setUserItemList] = useState([]);
    const [mixItemList,setMixItemList] = useState([]);

    const recipes = [
        {
            id: 'basicWeapon',
            name: '기본 무기 제작',
            ingredients: { wood: 1, iron: 1 },
            outcomes: [
              { id: 'sword', name: '검', probability: 0.3 },
              { id: 'dagger', name: '단검', probability: 0.5 },
              { id: 'fail', name: '꽝', probability: 0.2 }
            ]
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

    // 조합시 작동하는 메소드
    const mixItem = () => {
        mixItemList.forEach((item)=>{
            console.log(item.name);
        })
    }

    //믹스할 아이템 추가하기
    const addItemForRecipe = (item) => {
        setMixItemList((prev) => [...prev,item])
    }

    // 제작(크래프트) 시도 함수 - 확률적으로 결과 결정
    const attemptCraft = () => {

        //result ={ingredients : [] , outcomes[]}
        const selectedRecipe = calculateOutcomeProbabilities();
        
        // 결과(outcome)를 결정하기 위한 랜덤 판정
        let randomOutcome = Math.random();
        let cumulativeOutcome = 0;
        let resultOutcome = null;
        for (let outcome of selectedRecipe.outcomes) {
            cumulativeOutcome += outcome.probability;
            if (randomOutcome <= cumulativeOutcome) {
                resultOutcome = outcome;
                break;
            }
        }
        // 혹시 누적 확률 문제로 resultOutcome이 null이라면 마지막 outcome 선택
        if (!resultOutcome) {
            resultOutcome = selectedRecipe.outcomes[selectedRecipe.outcomes.length - 1];
        }

        if (resultOutcome.id === 'fail') {
            console.log(`제작 실패: ${resultOutcome.name}`);
        } else {
            console.log(`${resultOutcome.name} 제작 성공!`);
            // 제작된 아이템을 유저 인벤토리에 추가
            setUserItemList(prev => [...prev, { id: resultOutcome.id, name: resultOutcome.name }]);
        }

        // 사용된 재료는 믹스 리스트에서 제거 (필요한 만큼만 제거)
        let newMixList = [...mixItemList];
        Object.entries(selectedRecipe.ingredients).forEach(([ing, count]) => {
            for (let i = 0; i < count; i++) {
                const index = newMixList.findIndex(item => item.id === ing);
                if (index !== -1) {
                    newMixList.splice(index, 1);
                }
            }
        });
        setMixItemList(newMixList);
    };

    //TODO - 레시피 조회함수 , 나중에 BackEnd로 변경예정
    const findRecipe = (ingredientsNameList) => {
        
        const recipeList = [
            {
                ingredients : ["stone,wood"],
                outcomes : [
                    {
                        id: 'sword',
                        name: 'Sword',
                        // 예: 돌에 민감 (계수 1.0), 금에 덜 민감 (계수 0.2)
                        factors: { stone: 1.0, gold: 0.2 }
                    },
                    {
                        id: 'dagger',
                        name: 'Dagger',
                        // 예: 돌과 금의 영향이 비슷함
                        factors: { stone: 0.5, gold: 0.5 }
                    },
                    {
                        id: 'lance',
                        name: 'Lance',
                        // 예: 금에 민감 (계수 1.0), 돌에 덜 민감 (계수 0.2)
                        factors: { stone: 0.2, gold: 1.0 }
                    }
                ]
            }
        ]
        
        const ingredientNameSet = new Set(ingredientsNameList);
        
        let resultRecipe = null;

        recipeList.forEach((recipe)=>{
            const recipeNameSet =  new Set(recipe.ingredients);
            if (ingredientNameSet.size !== recipeNameSet.size) {
                return;
            }
            // 두 Set이 같은 요소를 가지는지 확인
            for (let item of recipeNameSet) {
                if (!ingredientNameSet.has(item)){
                    return false;
                }
            }

            resultRecipe = recipe;
        })

        return resultRecipe;

    }



    //재료양에따른 레시피 확률 함수
    function calculateOutcomeProbabilities() {

        //먼저 재료 이름 먼저 추출
        const ingredientsNameList = mixItemList.map((item)=>(item.name));
        const mixItemCntMap = mixItemList.reduce((acc,item)=>{
            acc[item.name] = (acc[item.name] || 0) + 1;
            return acc;
        },{})

        //outcomes같은 경우 불러온 걸로 -> 
        const outComeRecipe = findRecipe(ingredientsNameList);

        const outcomes = outComeRecipe.outcomes;
        // 각 결과의 가중치 계산 (비선형 효과를 위해 지수 k 적용 가능)
        const k = 1; // k값은 튜닝 가능. 1이면 선형, 1보다 크면 비선형 효과
        let totalWeight = 0;
        outcomes.forEach(outcome => {
            let weight = 1;
            for (const [ingr, coeff] of Object.entries(outcome.factors)) {
                // 만약 해당 재료가 없으면 0, 있으면 재료 수량에 계수를 곱해 거듭제곱 적용
                const ingrCount = ingredientsNameList[ingr] || 0;
                weight *= Math.pow(ingrCount, coeff * k);
            }
            outcome.weight = weight;
            totalWeight += weight;
        });

        // 각 결과의 확률 계산
        outcomes.forEach(outcome => {
            outcome.probability = (outcome.weight / totalWeight);
        });

        return outcomes;
    }


    return(
        <div>
            <div>
                <button className="px-6 py-2 bg-blue-500 text-white font-bold rounded-lg shadow-md hover:bg-blue-600"
                    onClick={clickBox}
                >click here</button>
                <button className="px-6 py-2 bg-blue-500 text-white font-bold rounded-lg shadow-md hover:bg-blue-600"
                    onClick={mixItem}
                >조합하기</button>
            </div>
            <div>
                <span>유저 아이템리스트</span>
                <ul>
                    {userItemList.map((item,idx)=>(
                        <li key={idx}>
                            <h1>{item.name}</h1> <button onClick={() => addItemForRecipe(item)}>재료추가</button>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    )



}

export default RandomBox;