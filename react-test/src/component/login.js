import React, {useState} from 'react'
import axios from 'axios'

function LogIn (row){

    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    const LoginFunc = () => {
        const userInfo = { 
            userName : 'tt'
        };

        axios({
            method:"pose",
            baseURL : "http://localhost:8080",
            url:"/auth/login",
            data: userInfo
        })
        .then(function (response){
            console.log('[success]\n' +response.data)
        })
        .catch(function (error){
            alert('[error]\n' + error);
        });
    }

    return (
        <section>
        <div className="hero-body">
            <div className="container has-text-centered">
                <div className="column is-4 is-offset-4 has-background-white">
                    <h3 className="title has-text-black">Login</h3>
                    <hr className="login-hr"/>
                    <div className="box">
                        <form onSubmit={LoginFunc}>
                            <div className="field">
                                <div className="control">
                                    <input className="input" type="text" placeholder="id" autoFocus=""/>
                                </div>
                            </div>

                            <div className="field">
                                <div className="control">
                                    <input className="input" type="password" placeholder="password"/>
                                </div>
                            </div>
                            <div className="field">
                                <label className="checkbox">
                  <input type="checkbox"/>
                  Remember me
                </label>
                            </div>
                            <button className="button is-block is-info is-large is-fullwidth" type='submit'>Login <i className="fa fa-sign-in" aria-hidden="true"></i></button>
                        </form>
                    </div>
                    <p className="has-text-grey">
                        <a href="../">Sign Up</a> &nbsp;·&nbsp;
                        <a href="../">Forgot Password</a> &nbsp;·&nbsp;
                        <a href="../">Need Help?</a>
                    </p>
                </div>
            </div>
        </div>
    </section>
    );
}

export default LogIn;
