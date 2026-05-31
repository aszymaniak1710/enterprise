import { Link } from 'react-router-dom';

function ProductItem({ id, title, brand }) {
  return (
    <li>
      <Link to={`/products/${id}`}>{title}</Link> ({brand})
    </li>
  );
}

export default ProductItem;