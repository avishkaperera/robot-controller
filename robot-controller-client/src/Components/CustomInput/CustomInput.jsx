import React from "react";
import './CustomInput.css'

const CustomInput = ({ handleInputChange }) => {

    return (
        <div>
            <h3 className="script-title">Input Script Below</h3>
            <div className="script-input">
                <textarea className="script-txt" onChange={handleInputChange}></textarea>
            </div>
        </div>

    )
}

export default CustomInput