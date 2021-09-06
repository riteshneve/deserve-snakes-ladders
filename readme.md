This is a spring boot application.

<b>Dependencies - </b>Java 11

Run instructions - <code> ./gradlew bootRun</code> This will download dependencies and run the application at port 9050.

<b>Overview -</b><br>
1. In <code>SnakesAndLaddersApplication</code> class, game starts after spring boot application has started. It will create a game and run 10 times for single player. After each turn player's position is changed.
2. Snakes are created randomly at the time of board creation. When players come at head of snake, their position is changed to tail.
3. A dice can be crooked to only throw even numbers. Crooked dice can be marked with optional command line parameter <code>--isCrooked=true</code>.