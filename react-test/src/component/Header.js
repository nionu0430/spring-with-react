import React from 'react';
import { Link } from 'react-router-dom';

function Header (){
    function toggleBurgerMenu() {
        document.querySelector('.navbar-menu').classList.toggle('is-active');
    }

    return (
        <nav className="navbar" role="navigation" aria-label="main navigation">
        <div className="navbar-brand">
          <span className="navbar-item" href="https://coocon.net">
            <img
              src="../asset/coocon_logo.png"
              width={112}
              height={28}
              alt='coocon logo'
            />
          </span>
          <span
            role="button"
            className="navbar-burger"
            aria-label="menu"
            aria-expanded="false"
            data-target="navbarBasicExample"
            onClick={toggleBurgerMenu}
          >
            <span aria-hidden="true" />
            <span aria-hidden="true" />
            <span aria-hidden="true" />
          </span>
        </div>
        <div id="navbarBasicExample" className="navbar-menu">
          <div className="navbar-start">
            <span className="navbar-item"><Link to="/">Main</Link></span>
            <span className="navbar-item"><Link to="/service">Service</Link></span>
            <span className="navbar-item"><Link to="/test">Test</Link></span>
            <div className="navbar-item has-dropdown is-hoverable">
              <span className="navbar-link">DevSample</span>
              <div className="navbar-dropdown">
                <span className="navbar-item"><Link to="/sample/prop-and-status">PropsAndStatus</Link></span>
                <span className="navbar-item"><Link to="/sample/prop-and-status-redux">PropsAndStatus-redux</Link></span>
                <span className="navbar-item"><Link to="/sample/component">Component</Link></span>
                <span className="navbar-item"><Link to="/sample/axios">Axios/connect to Spring</Link></span>
                <hr className="navbar-divider" />
                <span className="navbar-item">Report an issue</span>
              </div>
            </div>
          </div>
          <div className="navbar-end">
            <div className="navbar-item">
              <div className="buttons">
                <span className="button is-primary">
                  <strong>Sign up</strong>
                </span>
                <span className="button is-light">Log in</span>
              </div>
            </div>
          </div>
        </div>
      </nav>
    );
}

export default Header;
