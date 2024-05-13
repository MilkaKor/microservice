import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import 'bootstrap/dist/css/bootstrap.min.css'
import '../configurations/webpack.config.js'
import URL from "../configurations/webpack.config.js"
import AddEvent from "../components/modal/AddEvent.jsx";
import {Button} from "react-bootstrap";
import Cookies from "js-cookies/src/cookies.js";

const Home = () => {
    const [events, setEvents] = useState([]);
    const [show, setShow] = useState(false);
    const [isLogedIn, setIsLogedIn] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const reload = () => {
        window.location.reload();
    }

    useEffect(() => {
        const token = Cookies.getItem("token");
        if (token !== null && token !== undefined) {
            setIsLogedIn(true)
        }
        const requestOptions = {
            method: "GET", redirect: "follow"
        };

        fetch(URL + "event/all", requestOptions)
            .then(response => response.json())
            .then(json => setEvents(json))
            .catch(error => console.error(error));

    }, []);
    return (<>
        {isLogedIn && (<div className="d-flex justify-content-center">
            <Button variant="primary" onClick={handleShow}>
                +
            </Button>
        </div>)}
        <AddEvent handleClose={handleClose} show={show} reload={reload}/>
        <div className="list-group list-group-flush">
            {events.map((item, i) => (<div key={i} className="row">
                <div className="col">
                    <Link to={`/event`} state={item} key={i} className="text-decoration-none text-capitalize">
                        <h1>{item.name}</h1>
                    </Link>
                </div>
                {isLogedIn && (
                    <div className="col">
                        <a className="align-content-end" href={URL + "event/remove/" + item.id}>Удалить</a>
                    </div>)}
            </div>))}
        </div>
    </>)
}

export default Home