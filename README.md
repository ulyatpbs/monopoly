# Monopoly

A beginner Java programming project written in **April 2022** as Assignment 2 when first learning inheritance and polymorphism.

## Purpose
Non-graphical Monopoly game implementation. Focuses on OOP concepts: abstract classes, inheritance hierarchies (Property→Land/Company/Railroad, Square→Action/Tax/Jail), polymorphism, JSON file parsing, and game state management with rent calculation rules.

## Usage
```bash
javac -cp ".:./json-simple.jar" *.java
java -cp ".:./json-simple.jar" Main command.txt
```

Reads `property.json`, `cards.json`, processes `command.txt` (player;dice format). Outputs game results to `monitoring.txt`.

**Rules:** 2 players + banker, buy properties, pay rent, draw cards, handle jail/tax/parking squares, game ends on bankruptcy.
