import { useParams, Link } from 'react-router-dom';

function ProductDetail({ products }) {
  const { id } = useParams();

  const filteredProducts = products.filter((p) => p.id === parseInt(id));

  if (filteredProducts.length === 0) {
    return null;
  }

  const product = filteredProducts[0];

  return (
    <div style={{ textAlign: 'left', padding: '20px' }}>
      <h2>{product.title}</h2>
      <p>
        <strong>Category:</strong> {product.category} <br />
        <strong>Brand:</strong> {product.brand} <br />
        <strong>Description:</strong> {product.description} <br />
        <strong>Price:</strong> ${product.price} <br />
      </p>
      <div style={{ marginBottom: '20px' }}>
        <img src={product.thumbnail} alt={product.title} style={{ maxWidth: '300px', borderRadius: '8px' }} />
      </div>
      <Link to="/">Back to product list</Link>
    </div>
  );
}

export default ProductDetail;