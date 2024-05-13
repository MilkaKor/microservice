import 'bootstrap/dist/css/bootstrap-grid.min.css'
import Modal from 'react-bootstrap/Modal';
import {Button} from "react-bootstrap";
import {useState} from "react";
import URL from "../../configurations/webpack.config.js";

const AddEvent = ({handleClose, show, reload}) => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const clear = () => {
        setName("")
        setDescription("")
        handleClose()
    }
    const makeRequest = () => {
        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        const raw = JSON.stringify({
            "id": 0,
            "name": name,
            "description": description,
            "files": []
        });

        const requestOptions = {
            method: "POST",
            headers: myHeaders,
            body: raw,
            redirect: "follow"
        };

        fetch(URL+"event/new", requestOptions)
            .then((response) => response.text())
            .then((result) => console.log(result))
            .catch((error) => console.log(error));
        clear()
    }
    return (<>
        <Modal show={show}
               onHide={clear}
               backdrop="static"
               keyboard={false}>
            <Modal.Header closeButton>
                <Modal.Title>Добавить мероприятие</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="form-group">
                    <label htmlFor="name">Название мероприятия</label>
                    <input type="text" className="form-control border-dark" value={name}
                           onChange={(e) => setName(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label htmlFor="description">Информация о мероприянии</label>
                    <textarea className="form-control border-dark" value={description}
                              onChange={(e) => setDescription(e.target.value)}/>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={clear}>
                    Закрыть
                </Button>
                <Button variant="primary" onClick={makeRequest}>
                    Добавить
                </Button>
            </Modal.Footer>
        </Modal>
    </>)
}
export default AddEvent;