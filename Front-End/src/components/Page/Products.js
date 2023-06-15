import React, { useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import { Button } from "../Button";
import "../css/Products.css";

const Products = () => {
    const location = useLocation();
    const [products, setProducts] = useState([]);
    const [currentProduct, setCurrentProduct] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);
    const [searchInput, setSearchInput] = useState("");
    const [user, setUser] = useState(null);

    useEffect(() => {
        const number = extractNumberFromUrl(location.pathname);
        console.log(number);
        // Fetch products for the user using the extracted number (userId)
        if (number) {
            fetch(`http://localhost:8080/api/products/user/${number}`)
                .then((response) => response.json())
                .then((data) => setProducts(data))
                .catch((error) => console.error("Error fetching products:", error));
        }

        // Fetch user details
        if (number) {
            fetch(`http://localhost:8080/api/users/${number}`)
                .then((response) => response.json())
                .then((data) => setUser(data))
                .catch((error) => console.error("Error fetching user:", error));
        }
    }, [location]);

    const extractNumberFromUrl = (url) => {
        const regex = /\/(\d+)/;
        const match = url.match(regex);
        if (match && match.length > 1) {
            return parseInt(match[1], 10);
        }
        return null;
    };

    const onChangeSearchInput = (e) => {
        setSearchInput(e.target.value);
    };

    // Delete all products
    const removeAllProducts = () => {
        const requestOptions = {
            method: "DELETE",
        };
        fetch("http://localhost:8080/api/products", requestOptions)
            .then((response) => response.json())
            .then((data) => {
                setProducts(data);
                window.location.reload();
                alert(
                    "You have successfully deleted all products. If not, please reload your page."
                );
            });
    };

    const id = extractNumberFromUrl(location.pathname);

    // Search for title
    const searchByTitle = () => {
        setCurrentProduct(null);
        setCurrentIndex(-1);
        fetch(`http://localhost:8080/api/products?title=${searchInput}`)
            .then((response) => response.json())
            .then((data) => setProducts(data));
    };

    if (!user) {
        return (
            <h2 style={{ margin: "100px", textAlign: "center" }}>
                Loading user...
            </h2>
        );
    }

    if (products.length === 0) {
        return (
            <h2 style={{ margin: "100px", textAlign: "center" }}>
                {user.name} has not created any products yet.
            </h2>
        );
    } else {
        return (
            <div>
                <h4>Created products {user.name}</h4>
                <div className="search">
                    <div>
                        <input
                            type="text"
                            className="search-input"
                            placeholder="Search by title"
                            value={searchInput}
                            onChange={onChangeSearchInput}
                        />
                        <Button
                            buttonStyle="btn--normal"
                            type="button"
                            onClick={searchByTitle}
                        >
                            Search
                        </Button>
                    </div>
                </div>

                <div id="product">
                    {products.map((product) => (
                        <div className="card" key={product.id}>
                            <Link to={`/products/detail/${product.id}`}>
                                <img src="..\img\blob.jpg" alt="Product_Picture" />
                            </Link>
                            <div className="content">
                                <Link to={`/products/detail/${product.id}`}>
                                    <h3>{product.title}</h3>
                                </Link>
                                <span>CHF {product.price}.00</span>
                                <p>{product.description}</p>
                                <Link
                                    to={`/${extractNumberFromUrl(location.pathname)}/editProduct/${product.id}`}
                                >
                                    <button>Edit</button>
                                </Link>
                            </div>
                        </div>
                    ))}
                </div>
                <div style={{ margin: "35px" }}>
                    <Button buttonStyle="btn--delete" onClick={removeAllProducts}>
                        Remove All
                    </Button>
                </div>
            </div>
        );
    }
};

export default Products;
