import React, { useState } from "react";
import './Home.css'
import Grid from "../Grid/Grid";
import CustomInput from "../CustomInput/CustomInput";
import CustomButton from "../CustomButton/CustomButton";
import axios from "axios";

const Home = () => {

    const [script, setScript] = useState("");
    const [data, setData] = useState({ "x": 0, "y": 0, "direction": "EAST" });
    const [errorMessage, setErrorMessage] = useState("");

    const handleInputChange = (event) => {
        setScript(event.target.value);
    }

    const fetchNewPosition = async () => {
        const request = JSON.stringify({
            "script": script
        });

        try {
            setErrorMessage("");
            const response = await axios.post("http://localhost:8080/api/v1/position", request, { headers: { 'Content-Type': 'application/json' } });
            setData(response.data)
        } catch (error) {
            if(error.response && error.response.data && error.response.data.message){
                setErrorMessage(error.response.data.message);
            } else {
                setErrorMessage(error.message);
            }
        }
    };

    const handleClick = async (event) => {
        if (script === null || script === "" || script.length === 0) {
            setErrorMessage("Script cannot be blank!");
        } else {
            setErrorMessage("");
            await fetchNewPosition();
        }
    }

    return (
        <div className="error-message">
            <span>{errorMessage}</span>
            <div className="container">
                <Grid data={data} />
                <div className="script">
                    <CustomInput handleInputChange={handleInputChange} />
                    <CustomButton handleClick={handleClick} />
                </div>
            </div>
        </div>
    )
}

export default Home