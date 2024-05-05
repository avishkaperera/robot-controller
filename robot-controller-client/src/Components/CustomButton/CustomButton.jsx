import React from "react";
import './CustomButton.css'

const CustomButton = ({ handleClick }) => {

    return (
        <div className="script-execute">
            <button className="execute" onClick={handleClick}>Execute Script</button>
        </div>
    )
}

export default CustomButton