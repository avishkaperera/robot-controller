import React from "react";
import './Grid.css';
import EAST from '../Assets/EAST.svg'
import WEST from '../Assets/WEST.svg'
import NORTH from '../Assets/NORTH.svg'
import SOUTH from '../Assets/SOUTH.svg'

const Grid = ({data}) => {

    const getImage = (ImageName) => {
        if (ImageName === 'NORTH') {
            return NORTH;
        } else if (ImageName === 'EAST') {
            return EAST;
        } else if (ImageName === 'SOUTH') {
            return SOUTH;
        } else if (ImageName === 'WEST') {
            return WEST;
        }
    }

    const generateGrid = (x, y, direction) => {
        let rows = [];
        let cellIdx = 0;
        let rowIdx = 0
        for (let row = 0; row < 6; row++) {
            let cells = [];
            for (let column = 0; column < 6; column++) {
                if (column === 0 && row === 0) {
                    cells.push(<td className="grid-table-column-number" key={cellIdx}></td>)
                } else if (column === 0 && row >= 0) {
                    cells.push(<td className="grid-table-column-number" key={cellIdx}>{row - 1}</td>)
                } else if (row === 0 && column >= 0) {
                    cells.push(<td className="grid-table-column-number" key={cellIdx}>{column - 1}</td>)
                } else if (column === x + 1 && row === y + 1) {
                    cells.push(<td className="grid-table-column" key={cellIdx} data-testid={cellIdx}><img src={getImage(direction)} /></td>)
                } else {
                    cells.push(<td className="grid-table-column" key={cellIdx}></td>)
                }
                cellIdx++;
            }
            rows.push(<tr className="grid-table-row" key={rowIdx++}>{cells}</tr>)
        }
        return rows;
    }

    return (
        <div className="grid">
            <h1 className="title">Robot Controller</h1>
            <table className="grid-table">
                <tbody>
                    {generateGrid(data.x, data.y, data.direction)}
                </tbody>
            </table>
        </div>
    )
}

export default Grid