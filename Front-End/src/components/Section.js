import React from "react";
import { Routes, Route } from "react-router-dom";

import NotFound from "./Page/NotFound";
import AddProduct from "./Page/AddProduct";
import EditProduct from "./Page/EditProduct";
import Products from "./Page/Products";
import ProductDetails from "./Page/ProductDetails";
import AboutUs from "./Page/AboutUs";
import Cart from "./Page/Cart";
import Home from "./Page/Home";
import Payment from "./Page/Payment";
import UserProfil from "./Page/UserProfil";
import Login from "./Page/Login";
import SignIn from "./Page/SignIn";


class Section extends React.Component {
  render() {
    return (
        <section>
          <Routes>
            <Route path="/" >
              <Route path="" index element={<Login />} />
              <Route path="sign-In" element={<SignIn/>} />
              <Route path=":userId/home" element={<Home />} />
              <Route path=":userId/products" element={<Products />} />
              <Route path=":userId/editProduct/:id" element={<EditProduct />} />
              <Route path="products/detail/:id" element={<ProductDetails />} />
              <Route path=":userId/cart" element={<Cart />} />
              <Route path="payment" element={<Payment />} />
              <Route path=":userId/add" element={<AddProduct />} />
              <Route path=":userId/about" element={<AboutUs />} />
              <Route path="user/:id" element={<UserProfil />} />
            </Route>
            <Route path="*" element={<NotFound />} />
          </Routes>
        </section>
    );
  }
}

export default Section;
