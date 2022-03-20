# Energy-System
This Project is a simulation of an Energy System in which the entities are: consumers and distributors. Both entities have well-defined properties and try to remain present on the market, avoiding bankruptcy. Consumer and Distributor are made to interact, that's why I created the following structure: (a <- b means "b extends/implements a")

Business <- Client <- Consumer
    ^
    |
  Seller
    ^
    |
Distributor
