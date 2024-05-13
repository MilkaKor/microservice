import {useLocation} from "react-router-dom";
import URL from "../configurations/webpack.config.js";
import {useEffect, useState} from "react";
import {Button} from "react-bootstrap";
import AddFile from "../components/modal/AddFile.jsx";
import Cookies from "js-cookies/src/cookies.js";

const Event = () => {
    const location = useLocation();
    const [isLogedIn, setIsLogedIn] = useState(false);
    const name = location.state.name;
    const [data, setData] = useState({
        id: 0, name: "", files: []
    });
    useEffect(() => {
        const token = Cookies.getItem("token");
        if (token !== null && token !== undefined) {
            setIsLogedIn(true)
        }
        console.log(token);
        const requestOptions = {
            method: "GET", redirect: "follow"
        };

        fetch(URL + "event/find/" + name, requestOptions)
            .then(response => response.json())
            .then(json => setData(json[0]))
            .catch(error => console.error(error));
        console.log(location.state);
    }, []);
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const reload = () => {
        window.location.reload();
    }
    return (<>
        {isLogedIn && (<div className="d-flex justify-content-center">
                <Button variant="primary" onClick={handleShow}>
                    +
                </Button>
            </div>)}
        <AddFile handleClose={handleClose} show={show} reload={reload} event_id={data.id}/>
        <h1 className="text-center">{data.name}</h1>
        <div className="d-flex justify-content-center">
            <p>{data.description}</p>
        </div>
        <div className="list-group">
            {data.files.map((item, i) => (<div className="list-group-item" key={i}>
                <a href={URL + "file/download/" + item.name}>{item.name}</a>
            </div>))}
        </div>
    </>)
}
export default Event;