import { BrowserRouter, Routes, Route } from "react-router-dom"

import RootLayout from "./layouts/RootLayout"
import Home from "./pages/Home"
import Sobre from "./pages/Sobre"
import Post from "./pages/Post"
import NotFound from "./pages/NotFound"

export default function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<RootLayout />}>
          <Route index element={<Home />} />
          <Route path="sobre" element={<Sobre />} />
          <Route path="posts/:id" element={<Post />} />

          <Route path="*" element={<NotFound />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}