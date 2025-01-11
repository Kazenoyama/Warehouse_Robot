import { Map } from './Map.js';

const grid = [  [0, 0, 0, 1, 0, 1, 0, 0, 0, 0],
                [0, 0, 0, 1, 0, 1, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], 
                [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            ];
const warehouse = new Map(grid);
warehouse.render("container");

let map = warehouse.returnMapCells();
console.log(map);

