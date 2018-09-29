# Highway simulator

This is a simple GUI application (in french) that simulates cars driving on a highway, one of my first school project!

The purpose of the project is the simulation of the car traffic on a highway, the program simulates
the evolution of speed, lanes and positions of a set of cars arriving at regular intervals, and on
the other hand produces information on this evolution.

Application has been built on **November 2006** using **Netbeans IDE 5.0**.

It uses **Swing** and the **Graphics2D** library, and was compiled with **Java 1.4**.

## Usage

Run the provided `.jar` file :

```bash
java -jar "highway-simulator.jar"
```

Then just use the UI :

![Main interface picture](doc/main_interface.png?raw=true "Main interface")

The application allows to :
- create a new **highway** with the following parameters :
  - number of **lanes** (`2` to `4`)
  - length (meters, from `100m` to `10000m`)
- add a **vehicle** with the following parameters :
  - color (`red`, `green`, `blue`, `yellow`, `purple`) 
  - cruising speed (max `200km/h`)
  - acceleration
  - safety distance
  - "control" car or not ("control" car is a special car that will be tracked independently for statistics, it will be `black` and identified with a `T` as shown below)
- visualize some parameters like :
  - number of cars currently running
  - number of overtakes
  - average speeds :
    - faster car
    - slower car
    - all cars
- view **statistics** from generated text files :
  - session statistics (`Session.txt`)
  - control car statistics (`VehiculeTemoin.txt`)
  
![Control car](doc/car_t.png?raw=true "Control car")

## Description

Cars drive by default on a 2-lane highway. They arrive on the highway in an initial position at different intervals,
and with an initial speed of `0`. The behavior of a car is simulated until it reaches an exit position.

There are several kinds of parameters that affect the behavior of a car :
- specific parameters to each car which do not vary during execution time (safety distance, cruising speed and acceleration)
- specific values to each car which evolve during execution time (current speed, track, position)

So the input data of the simulation program will therefore consist of :
- the safety distance
- the highway length
- the interval between each arrival of a car at the entrance
- a list of cars specifying, for each of them, its acceleration, cruising speed, and an identification color

We consider that a lane is free for a car if there is no other vehicle on the same lane between the
positions `P(c)` and `P(c) + D(c)`. A lane is said to be free for a car if there is no other vehicle
between the positions `P(c)-200m` and `P(c) + D(c)`.

There are two modes of traffic: _overtake_ mode and _standard_ mode.

1. In _standard_ mode, a car drives on the right lane. If this lane is free, the car runs at its
cruising speed or accelerates until it reaches its cruising speed. If the lane is not free, then
it takes care of the state of the left lane :
- if the left lane is free, the car begin overtaking
- otherwise the car slows down

2. In _overtaking_ mode, a car changes lane to go on the left lane (it is assumed that this change of lane is instantaneous).
It stays on this lane as long as it is in _overtaking_ mode. It leaves the _overtaking_ mode when the following conditions are met:
- its position is located after the one of the vehicle which was right in front of it at the beginning of the overtaking
- the right lane is free

During the entire duration of the overtaking, if the left lane is free, the car drives at its cruising speed or accelerates until it reaches cruising speed.
If this lane is not free, it slows down.

Finally, the program allows to identify a "control" car, and will record in a text file the positions and other parameters of this car.

## Technical details

The application consists of several classes :

Base classes :
- `Vehicule` : represent a car
- `Autoroute` : represent the highway
- `EcritDansFichier` : Take care of writing into the `Session.txt` statistics file
- `EcritVehiculeTemoin` : Take care of writing the `VehiculeTemoin.txt` statistics file

Frames :
- `Fenetre` : Main frame of the application, it contains the `PanelRoute` and `PanelCommandes` panels (see below) to display the whole GUI
- `FenetreFichier` : Frame representing the window displaying the "control" car statistics 
- `FenetreFichierSession` : Frame representing the window displaying the whole session statistics

Panels :
- `PanelCommandes` : This panel contains the different components allowing to change the settings that will affect the program. This class implements the `InterfaceCommande` interface which allows to communicate with the `PanelRoute` class using **listeners**
- `PanelRoute` : This Panel contains the drawing that displays the movement of vehicles on the highway. It is intended to be added in the Frame Window to be displayed on the screen next to the Panel containing the commands

The `Main` class is the entry point of the application.

The `InterfaceCommande` interface describes the actions that will need to be implemented by classes that need to listen (extends `EventListener`) to changes like car speed, position, etc.

The default display of **AWT** or **Swing** component uses a simple buffer (we see the image being drawn).
If we start to build quite complex animations, it may cause flickering of the image (clipping).
To solve this problem, one can use the double-buffering technique. Double buffering is having 2 buffer, an
in-memory buffer and another one who takes care of the display. Problems of refresh, image jump and other disruptive visuals effects are thus eliminated.

![Double buffering](doc/double_buffering.png?raw=true "Double buffering")

Using a double buffer is particularly recommended for displaying complex animations or large components.

## Licence

WTFPL license : http://www.wtfpl.net/

But a mention is always appreciated :)