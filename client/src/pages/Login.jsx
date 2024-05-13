import 'bootstrap/dist/css/bootstrap.min.css'
import '../configurations/webpack.config.js'
import URL from "../configurations/webpack.config.js"
import {useState} from "react";
import Cookies from "js-cookies/src/cookies.js";
import {useNavigate} from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();
    const [login, setLogin] = useState("")
    const [password, setPassword] = useState("")
    const submitLogin = (e) => {
        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        myHeaders.append("Cookie", "JSESSIONID=3D237AD6845480380F998F36AF97ABDE");

        const raw = JSON.stringify({
            "login": login,
            "password": password
        });

        const requestOptions = {
            method: "POST",
            headers: myHeaders,
            body: raw,
            redirect: "follow"
        };

        fetch("http://localhost:8080/auth/login", requestOptions)
            .then((response) => response.json())
            .then((json) => Cookies.setItem("token", json["token"]))
            .catch((error) => alert(error));
        navigate("/");
    }
    return (<>
        <div className="container">
            <div className="form-group">
                <label htmlFor="login">Login</label>
                <input className="input-group" value={login} type="text" placeholder="Enter login"
                       onChange={e => setLogin(e.target.value)}/>
            </div>
            <div className="form-group">
                <label htmlFor="password">Password</label>
                <input className="input-group" value={password} type="password" placeholder="Enter password"
                       onChange={e => setPassword(e.target.value)}/>
            </div>
            <div className="form-group align-content-center align-items-center">
                <button className="btn btn-primary" onClick={e => submitLogin(e)}>Login</button>
            </div>
        </div>
    </>)
}
export default Login