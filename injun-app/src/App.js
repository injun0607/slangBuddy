import LoginScreen from "./components/Login/Login";
import ChatScreen from "./components/Chat/Chat";
import ChateSelectBox from "./components/Common/ChatSelectBox";
import { BrowserRouter as Router} from "react-router-dom";
import AppRouter from "./Config/AppRouter";
import "./index.css";





function App() {
  return (
      <Router>
        <AppRouter/>
      </Router>
  );
}

export default App;
