# Simulation Aspect of Super Six

This part focuses on the simulation of the actual game. The technical part will be discussed in the [performance](performance.md) document.

## Rules

Super Six is a game for 2-6 players. Each player gets the same number of sticks. The goal of the game is to get rid of all the sticks. Beginning with start player, each player takes a turn and must throw a dice with number 1 through 6. The player then looks at the board and determines what to do:

1. if the player got a six, he can throw the stick inside the box, getting rid of it
2. if the player got any other number:
    1. if the field is not occupied, place the stick on the field at the given number
    2. if the field is already occupied, the player put the stick in his stack

If the player does not have any sticks left, he wins the game. If the player did **not** take a stick, he can choose to throw the dice again.

## Analysis

The field of play always consists of five fields which can have varying occupation levels. For this simulation, we are going to look at two risk types:

1. A risk-loving player, who will throw the dice when two out of five fields are filled.
2. A risk-averting player, who will not throw the dice when two ouf of five fields are filled.

For a number of players, there will always be a matrix to show the different types. All variations will be simulated.

## Set-up

In order to make this simulation comparable, multiple `Games` will be run in a `Session`. The games will be run sequentially in order to get the same results on every run. Each session will be initialized with a random seed. In order to rule out different seeds, there will also be multiple sessions with different seeds.

The high-level set-up looks like this:

For each risk matrix entry:
    - Generate a risk matrix for the different players
        - Run <s> Sessions
            - Run <g> Games

For each risk matrix, the wins per player are displayed afterwards. For the purpose of easy programming, seeds (<s>) are numbers starting at 1, the number of games (<g>) are numbers starting at 1.        

## Assumptions

1. This game heavily favors the starting player.
2. No matter how all players behave in a risk-loving or risk-averting way, the first player will always be favored with a significance level of:
    1. <= 5 %
    2. <= 0,1 %
    3. <= 0,1 %

## Arguments

The program needs the following arguments to be supplied:

- `-p`: number of players
- `-n`: number of sticks per player
- `-s`: number of max seed (the starting seed is 0)
- `-g`: number of games to be played per seed

## Results

No results yet.

## Verdict
