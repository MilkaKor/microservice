import {HomeTwoTone, EditTwoTone, CheckCircleTwoTone} from '@ant-design/icons';
import {Menu} from 'antd';
import {useEffect, useState} from 'react';
import {Outlet, Link} from 'react-router-dom';
import Cookies from "js-cookies/src/cookies.js";


const Header = () => {
    const [current, setCurrent] = useState('h');
    const [isLogedIn, setIsLogedIn] = useState(false);
    useEffect(() => {
        const token = Cookies.getItem("token");
        if (token !== null && token !== undefined) {
            setIsLogedIn(true)
        }
    }, []);
    const onClick = (e) => {
        console.log('click ', e);
        setCurrent(e.key);
    };
    return (<>
            <Menu onClick={onClick} selectedKeys={[current]} mode="horizontal">
                <Menu.Item key="h" icon={<HomeTwoTone/>}>
                    <Link to="/">Home</Link>
                </Menu.Item>
                {!isLogedIn && (<Menu.Item key="m" icon={<CheckCircleTwoTone/>}>
                        <Link to="/login">Login</Link>
                    </Menu.Item>)}
                {/*<Menu.Item key="r" icon= {<EditTwoTone />}>*/}
                {/*    <Link to="/register">Register</Link>*/}
                {/*</Menu.Item>*/}
                {/*<Menu.Item key="l" icon= {<CheckCircleTwoTone />}>*/}
                {/*    <Link to="/login">Login</Link>*/}
                {/*</Menu.Item>*/}
            </Menu>
            <Outlet/>
        </>

    )
};
export default Header;