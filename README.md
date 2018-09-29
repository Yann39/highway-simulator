# Highway simulator

This is a simple GUI application (in french) to simulate cars driving on a highway, one of my first school project!

Application has been built on **November 2006** using **Netbeans IDE 5.0**.

It uses the **Swing** library and was compiled with **Java 1.4**.

## Usage

Run the provided `.jar` file :

```bash
java -jar "highway-simulator.jar"
```

Then just use the UI :

![Main interface picture](doc/main_interface.png?raw=true "Main interface")

The application allows to :
- create a new highway with the following parameters :
  - number of lanes
  - length
- add a vehicle with the following parameters :
  - color
  - cruising speed
  - acceleration
  - safety distance
  - control car or not
- visualize some parameters like :
  - number of cars currently running
  - number of overtakes
  - average speeds
    - faster car
    - slower car
    - all cars
- view statistics from generated text files
  - session statistics
  - control car statistics

## Technical details

## Licence

WTFPL license : http://www.wtfpl.net/

But a mention is always appreciated :)