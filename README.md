Description:
-------------------------
* The game must accept two command-line parameters: 
(1) "frame-rate", which controls how often the screen is repainted, and (2) speed of the snake, describing how fast the snake moves in the game. E.g. java snake 30 5 describes the framerate of 30 and speed of 5.
*  `make run` should run with default arguments(it should run with the framerate of 30 and speed of 5 by default)
* Game start either by press 's' via the keyboard or press 'start' button on the splash screen;Game restart either by press 'r' via the keyboard or press 'restart' button;Game pause by press 'space' via the keyboard;Use Direction Arrows on the keyboard to direct snake.
* The food, poison, and bomb location will all be generated randomly.
* The snake is initial with a random head direction and an arbitrary start point.Avoid to hit the wall, eat yourself, eat the bomb or eat the poison.

Development Environment:
-------------------------

* Java: Java(TM) SE Runtime Environment
* Java Version: 1.8.0_92
* OS Name: Mac OS X
* OS Version: 10.11.4
* OS Architecture: x86_64
* Total Memory (MB): 245
* Max Memory (MB): 3641

Enhancements:
-----------

* Use sound effects for snake eats food or position and when the game is load, start, restart and over.
* Use texture graphics for the background, the snake, and food, bomb, poison.
*When scores < 100, each apple worth 10 scores; when scores > 100, there will be a random fixed poison appear on the screen and an apple starts worth 50 scores, if the snake eats the poison, game is over; when scores > 1000, there will be another bomb appear on the screen, and the position of the bomb will change when the snake eats an apple, each apple worth 1000 scores, if the snake eats the poison or the bomb, game is over.
* Support for start and restart buttons.

<img width="678" alt="screen shot 2016-05-18 at 9 25 05 pm" src="https://cloud.githubusercontent.com/assets/2667006/16358275/f5d4787c-3adc-11e6-8674-120e63e2a0e5.png">
