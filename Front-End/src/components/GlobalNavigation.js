import React, { useEffect } from "react";
import Menu from "./svg/bars-solid.svg";
import Close from "./svg/times-solid.svg";
import CartIcon from "./svg/shopping-cart-solid.svg";
import User from "./svg/user.svg";
import { Link, useLocation } from "react-router-dom";
import "./css/GlobalNavigation.css";

const GlobalNavigation = () => {
  const location = useLocation();

  useEffect(() => {
    const number = extractNumberFromUrl(location.pathname);
    console.log(number);
  }, [location]);

  const extractNumberFromUrl = (url) => {
    const regex = /\/(\d+)/;
    const match = url.match(regex);
    if (match && match.length > 1) {
      return parseInt(match[1], 10);
    }
    return null;
  };

  const menuToggle = () => {
    this.setState({ toggle: !this.state.toggle });
  };

  const number = extractNumberFromUrl(location.pathname);

  return (
      <header>
        <div className="menu" onClick={menuToggle}>
          <img src={Menu} alt="" width="20" />
        </div>
        <div className="logo">
          <h1>
            <Link to={`/${number}/home`}>Shop.ch</Link>
          </h1>
        </div>
        <nav>
          <ul>
            <li>
              <Link to={`/${number}/home`}>Home</Link>
            </li>
            <li>
              <Link to={`/${number}/products`}>my_Products</Link>
            </li>
            <li>
              <Link to={`/${number}/contact`}>Contact</Link>
            </li>
            <li>
              <Link to={`/${number}/about`}>About</Link>
            </li>
            <li>
              <Link to={`/${number}/add`}>Create</Link>
            </li>
            <li className="close" onClick={menuToggle}>
              <img src={Close} alt="" width="20" />
            </li>
          </ul>
          <div className="nav-user">
            <Link to={`/user/${number}`}>
              <img src={User} alt="user" width="25" />
            </Link>
          </div>
          <div className="nav-cart">
            <span>0</span>
            <Link to={`/${number}/cart`}>
              <img src={CartIcon} alt="" width="20" />
            </Link>
          </div>
        </nav>
      </header>
  );
};

export default GlobalNavigation;
