import { useState } from 'react';
import ProductItem from './ProductItem';

function ProductList({ products }) {
  const [filter, setFilter] = useState('');

  const handleFilterChange = (event) => {
    setFilter(event.target.value);
  };

  return (
    <div>
      <h1>List of products</h1>
      <div style={{ textAlign: 'left', marginBottom: '20px' }}>
        <label htmlFor="product-filter">Filter by product title: </label>
        <input
          id="product-filter"
          type="text"
          value={filter}
          onChange={handleFilterChange}
        />
      </div>
      <ul>
        {products
          .filter((product) =>
            product.title.toLowerCase().includes(filter.toLowerCase())
          )
          .map((product) => (
            <ProductItem
              key={product.id}
              id={product.id} // Przekazujemy id do generowania Linku
              title={product.title}
              brand={product.brand}
            />
          ))}
      </ul>
    </div>
  );
}

export default ProductList;