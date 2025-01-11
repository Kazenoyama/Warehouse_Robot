export class Map {

    constructor(grid) {
        this.grid = grid;
    }

    render(containerID){
        let container = document.getElementById(containerID);
        container.innerHTML = "";

        const grid = document.createElement("div");
        grid.className = "grid";

        grid.style.gridTemplateColumns = `repeat(${this.grid[0].length}, 50px)`;

        this.grid.forEach(row => {
            row.forEach(cell => {
                let cellElement = document.createElement("div");
                cellElement.className = "cell";
                cellElement.style.backgroundColor = cell === 1 ? "black" : "white";
                cellElement.textContent = cell === 1 ? "📦" : "";
                grid.appendChild(cellElement);
            });
        });

        container.appendChild(grid);
    }

    returnMapCells(){
        const cell = document.querySelectorAll(".cell");
        const map = [];

        for (let i = 0; i < this.grid.length; i++) {
            map[i] = [];
            for (let j = 0; j < this.grid[i].length; j++) {
                map[i][j] = cell[i * this.grid[i].length + j]
            }
        }

        return map;
    }

}