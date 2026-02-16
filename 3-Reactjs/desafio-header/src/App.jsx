import Header from "./components/Header";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Catalogo from "./components/Catalogo";
import Kanban from "./components/Kanban";

export default function App() {
    return (
        <Router>
            <Header />
            <Routes>
                <Route path="/catalogo" element={<Catalogo />} />
                <Route path="/kanban" element={<Kanban />} />
            </Routes>
        </Router>
    );
}
