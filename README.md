# Game of Sticks

Create a version of the Game of Sticks where human and AI players can play
against each other.

In the Game of Sticks there is a heap of sticks on a board. On their turn,
each player picks up 1 to 3 sticks. The one who has to pick the final stick
will be the loser.

The following is an example of the game of sticks.

* The game starts with 20 sticks on the board.
* Marvin takes 3 sticks, there are 17 sticks remaining.
* Hal takes 2 sticks, there are 15 sticks remaining.
* Marvin takes 1 stick, there are 14 sticks remaining.
* Hal takes 3 sticks, there are 11 sticks remaining.
* Marvin takes 2 sticks, there are 9 sticks remaining.
* Hal takes 2 sticks, there are 7 sticks remaining.
* Marvin takes 3 sticks, there are 4 sticks remaining.
* Hal takes 1 stick, there are 3 sticks remaining.
* Marvin takes 2 sticks, there is 1 stick remaining.
* Hal has to take the final stick and loses.

This assignment is split into parts:

1. Implementing the game as a two-player game.
2. Adding an AI that can be played against.
3. Saving data to keep information persistent.

## Night 1

### Normal Mode

#### Player vs Player

Create a game where two players can play against each other. The two examples
below demonstrate how the game should behave.

Example 1:

```
Welcome to the Game of Sticks!
How many sticks are there on the table initially (10-100)? 10

There are 10 sticks on the board.
Player 1: How many sticks do you take (1-3)? 3

There are 7 sticks on the board.
Player 2: How many sticks do you take (1-3)? 3

There are 4 sticks on the board.
Player 1: How many sticks do you take (1-3)? 3

There is 1 stick on the board.
Player 2: How many sticks do you take (1-3)? 1
Player 2, you lose.
```

Example 2:

```
Welcome to the Game of Sticks!
How many sticks are there on the table initially (10-100)? 500
Please enter a number between 10 and 100.
How many sticks are there on the table initially (10-100)? 3
Please enter a number between 10 and 100.
How many sticks are there on the table initially (10-100)? 50

There are 50 sticks on the board.
Player 1: How many sticks do you take (1-3)? 3

There are 47 sticks on the board.
Player 2: How many sticks do you take (1-3)? 55
Please enter a number between 1 and 3
Player 2: How many sticks do you take (1-3)? 3

There are 44 sticks on the board.
Player 1: How many sticks do you take (1-3)? 3
...

There is 1 stick on the board.
Player 1: How many sticks do you take (1-3)? 1
Player 1, you lose.
```



## Night 2

### Normal Mode

#### Player vs StupidAI
We are low on friends to are willing to play with us so as software engineers we
will make our own friends.  Create an AIPlayer that randomly picks a number of
sticks.  It should pick a correct number so it should not pick more sticks than
are available and should not pick more sticks than the rules state.

To make sure that players are all consistent the AI player and the human player
should both implement an `Interface` which expects a `makeChoice` method.

Also, keep in mind that at least the AI player needs to know how many sticks
are left to make a decision.

#### AI vs AI
If everything is done correctly then we should have the ability to make both
players be AI and play against each other.

#### Record Keeping
We want to keep a running total of all wins/loses for each player who plays our
game.  This means we need to start keeping track so we can gloat.  Create a
`HashMap` of each player name and their win/loss rating.  When a user wins add
one to their record and when they lose remove one from their record.

#### Saving
At the end of the game we want to take the record hashmap and save it to a file.

#### Loading
When the program starts up open the saved file and load the hashmap back into
memory.

### Hard Mode

### Player vs LearningAI
Let's create an artificial intelligence player for the Game of Sticks. Instead
of creating an AI based off knowledge of the optimal strategy, we'll create
an AI that can learn from games it wins and loses, and then we can look at it
to figure out the best strategy.  Once this is implemented you will need to play
many games in order to make the AI any good.  A good idea to play it against
the StupidAI first.

Consider the functionality of the AI using the following description:

* An AI has a number of hats, one hat for each possible amount of sticks on the
table. Initially, each hat contains three balls that are numbered from 1 to 3.

* At every step of the game that the AI plays, the AI takes a random ball out of
the hat that matches the amount of sticks currently on the board. When the AI
takes a ball out of a hat, it places it next to the hat for waiting, reads the
number on the ball, and takes the amount of sticks that the ball indicates.

* If the AI wins the game, it puts two balls of the type to each hat that has a
ball next to it. Both balls have the same number. If the AI loses, it will
throw away the balls that are next to the hats (note: A hat must always have at
least one ball of each number, hence the last ball of a specific number cannot
be thrown away and must be put back to the hat).

* As more and more games are played, there will be more balls that indicate a
good number of sticks to take. This means that as balls are taken at random, it
becomes more likely that the AI is able to play well.

**Example**:

Let us consider an example where there are 10 sticks at the beginning. The hat
contents for the AI are as follows:

        |       |       |       |       |       |       |       |       |       |
------- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | -----
hat     | 1     | 2     | 3     | 4     | 5     | 6     | 7     | 8     | 9     | 10
content | 1,2,3 | 1,2,3 | 1,2,3 | 1,2,3 | 1,2,3 | 1,2,3 | 1,2,3 | 1,2,3 | 1,2,3 | 1,2,3

The game may proceed as follows:

1. Player takes 3 sticks, there are 7 sticks remaining.
2. AI randomly picks up ball 2 from the hat 7. This means that the AI takes 2 sticks, and there are 5 sticks remaining.
3. Player takes 1 stick, there are 4 sticks remaining.
4. AI randomly picks up ball 3 from hat 4. This means that AI takes 3 sticks, and there is 1 stick remaining.
5. Player has to take the final stick and loses.

Now, the situation with the AI is as follows:

        |       |       |       |     |       |       |     |       |       |
------- | ----- | ----- | ----- | --- | ----- | ----- | --- | ----- | ----- | -----
hat     | 1     | 2     | 3     | 4   | 5     | 6     | 7   | 8     | 9     | 10
content | 1,2,3 | 1,2,3 | 1,2,3 | 1,2 | 1,2,3 | 1,2,3 | 1,3 | 1,2,3 | 1,2,3 | 1,2,3
beside  |       |       |       | 3   |       |       | 2   |       |       |

As the AI wins the game, it will put the balls that are next to the hats back to the hats with extra balls. The situation is now as follows:

        |       |       |       |         |       |       |         |       |       |
------- | ----- | ----- | ----- | ------- | ----- | ----- | ------- | ----- | ----- | -----
hat     | 1     | 2     | 3     | 4       | 5     | 6     | 7       | 8     | 9     | 10
content | 1,2,3 | 1,2,3 | 1,2,3 | 1,2,3,3 | 1,2,3 | 1,2,3 | 1,2,2,3 | 1,2,3 | 1,2,3 | 1,2,3

Now the AI will more likely take 3 sticks in the case of four sticks remaining on the board, and 2 sticks in case there are 7 sticks remaining on the board.

Your task is to modify the human vs. human version of the game so that the player can choose to play against an AI that works as described above. After each game, the AI will update the contents of its hats. The AI will play relatively randomly at first, but you will notice that it will start to learn a strategy as you play against it.

The following example displays how the program should behave after you have finished this step.

```
Welcome to the Game of Sticks!
How many sticks are there on the table initially (10-100)? 10
Options:
 Play against a friend (1)
 Play against the computer (2)
Which option do you take (1-2)? 2

There are 10 sticks on the board.
Player 1: How many sticks do you take (1-3)? 3

There are 7 sticks on the board.
AI selects 2.

There are 5 sticks on the board.
Player 1: How many sticks do you take (1-3)? 3

There are 2 sticks on the board.
AI selects 2.
AI loses.
Play again (y/n)? y

There are 10 sticks on the board.
Player 1: How many sticks do you take (1-3)? 1

There are 9 sticks on the board.
AI selects 1.

There are 8 sticks on the board.
Player 1: How many sticks do you take (1-3)? 3

There are 5 sticks on the board.
AI selects 3.

There are 2 sticks on the board.
Player 1: How many sticks do you take (1-3)? 2
You lose.
Play again (y/n)? y

There are 10 sticks on the board.
Player 1: How many sticks do you take (1-3)? 3

There are 7 sticks on the board.
AI selects 2.

There are 5 sticks on the board.
Player 1: How many sticks do you take (1-3)? 3

There are 2 sticks on the board.
AI selects 2.
AI loses.
Play again (y/n)? n
```

#### Keeping the AI smart
Our learning AI gets really smart after a long time playing.  We don't want it
to always have to go through that to be good so we are going to keep it trained.
Add methods to the AI that will save and load from a file containing its brain (hats/counts hashmap).  The main method should call these before kicking off
the games.

## External Links
* [Github Repo](https://github.com/tiy-lv-java-2016-06/game-of-sticks)

## Credit

This assignment is taken from [Stanford's Nifty Assignments 2014](http://nifty.stanford.edu/2014/laaksonen-vihavainen-game-of-sticks/).
