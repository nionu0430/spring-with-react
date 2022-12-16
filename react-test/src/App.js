import './styles/App.css';
import Header from './component/Header';
import Footer from './component/Footer';

import Main from './page/Main';
import Service from './page/Service';
import Test from './page/Test';

import Error from './error/Error';

import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <div >
      <BrowserRouter>      
      <Header></Header>
        <div className='App'>
        <Routes>
          <Route path='/' element={<Main/>}> </Route>
          <Route path='/service' element={<Service/>}> </Route>
          <Route path='/test/*' element={<Test/>}> </Route>
          <Route path='*' element={<Error/>}> </Route>
        </Routes>
        </div>
      </BrowserRouter>
      <Footer></Footer>
    </div>
  );
}

export default App;
