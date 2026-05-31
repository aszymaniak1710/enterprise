import { useState, useEffect } from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import ProductList from './ProductList';
import ProductDetail from './ProductDetail';
import './App.css';

function App() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch('https://dummyjson.com/products')
      .then((response) => response.json())
      .then((data) => setProducts(data.products))
      .catch((error) => console.error('Error fetching products:', error));
  }, []);

  const router = createBrowserRouter([
    {
      path: "/",
      element: <ProductList products={products} />,
    },
    {
      path: "/products/:id",
      element: <ProductDetail products={products} />,
    },
  ]);

  return <RouterProvider router={router} />;
}

export default App;