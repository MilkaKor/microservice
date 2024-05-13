import Modal from "react-bootstrap/Modal";
import {Button} from "react-bootstrap";
import {useState} from "react";
import URL from "../../configurations/webpack.config.js";

const AddFile = ({handleClose, show, reload, event_id}) => {
    const [file, setFile] = useState(null);
    const [fileName, setFileName] = useState("");
    const clear = () => {
        setFile('')
        handleClose()
    }
    const handleChange = (e) => {
        setFile(e.target.files[0])
        setFileName(e.target.value)
    }
    const sendFileData = () => {
        let name = fileName.replace(/^.*[\\/]/, '')
        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        const raw = JSON.stringify({
            "id": 0, "event": {
                "id": event_id
            }, "name": name
        });

        const requestOptions = {
            method: "POST", headers: myHeaders, body: raw, redirect: "follow"
        };
        fetch(URL + "file/uploadFileData", requestOptions)
            .then((response) => response.text())
            .then((result) => console.log(result))
    }
    const makeRequest = () => {
        try {
            let name = fileName.replace(/^.*[\\/]/, '')
            const formdata = new FormData();
            formdata.append("file", file, name);
            const requestOptions = {
                method: "POST", body: formdata, redirect: "follow"
            };

            fetch(URL + "file/uploadFile", requestOptions)
                .then((response) => response.text())
            sendFileData();
        } catch (error) {
            alert("Файл имеет большой объем");
        }
        clear()
    }
    return (<>
        <Modal show={show}
               onHide={clear}
               backdrop="static"
               keyboard={false}>
            <Modal.Header closeButton>
                <Modal.Title>Добавить файл</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="form-group">
                    <label htmlFor="name">Файл</label>
                    <input type="file" className="form-control border-dark"
                           onChange={(e) => handleChange(e)}/>
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
export default AddFile