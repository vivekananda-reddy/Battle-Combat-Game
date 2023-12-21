## Read Me

1. **About/Overview:**
This project is to build a turn based battle game. Each player has a set of abilities and can equip a set gears and weapon. Gears impact the players abilities in positive or negative way. A player wins the game if the opponent's health loses all the health.


2. **List of features:** The list of features supported by the program are mentioned below:
    - Players can create their character with their name of choice and can also name their weapon
    - Game randomly assigns each player 20 gears from a bag and one weapon.
    - The impact of gears on players attributes is random and impact of the weapon in the opponent is a random value with in the range of the weapon's damage
    - A player can successfully hit the other player and cause damage if attacking player's striking power is more than avoidance ability of the opponent. 
    - Actual damage is calculated by potential striking damage of the attacker minus the constitution of the opponent. IF damage is greater than zero, it is subtracted from the health of the opponent
    - If the player's health is zero or less, then the player loses the game. 
    - If there is no hit done for 10 consecutive moves then the game is declared a draw
   
3. **How to run:**
      Run command 'java -jar Project2Battle.jar' from command line

4. **How to Use the Program:** Following are the details on how to use the program: </br>
   - Create and Arena object
   - Create players by passing player names
   - Run equipGearsAndWeapon() by passing weapon names to equip both players with gears and one weapon each
   - Methods getPlayersAttributesInfo(), getPlayersGearInfo(), getWeaponInfo(), getHealthOfPlayers() will provide the details of the gears, abilities, weapon details and players' health 
   - By calling playRound() method a round will be played. Calling this in loop will execute multiple rounds in the game
   - isGameOver() method can be used to check if the game is over.
   - matchResult() can be used to know the result of the game
   - rematch() method is used to re-start the game with initial health of the players
   
5. **Description of Examples:** <br/> 
    <u> Run - ExampleRun.txt:</u> Following items are covered in the run<br/>
   - Displayed a welcome message
   - Players were created with names Player1 and Player 2 and initial info of both players was displayed
   - Players were equipped gears and weapons with names weapon1, weapon2. Info of all gears and their impact was displayed
   - Updated player attributes/abilities were displayed after gears impact
   - Players' weapons and health was displayed
   - Info of each round was displayed showing whether the hit happened, damage caused, attacking player name and players health
   - Match result was displayed. It was a draw initially
   - Rematch was called for by pressing Y
   - In rematch players reset health was displayed and game was played for multiple round and each round info was displayed
   - After 33 rounds, player1 won the game
   - Another rematch was called for. This rematch ended with a draw after 17 rounds.
   - N was presses so game has ended with a 'thank you' message

6. **Design/Model Changes:** No design changes were made compared to the initial design

7. **Assumptions:** Following assumptions are made based on the interpretations of the requirements.
   - Katana always come in pairs. Both Katanas attack at once and each Katana has a different random value while attacking
   - The effect of all gears is chosen from 1-6
   - Potion effects only one ability and last till the end of the game
   - Abilities of the player will not go below 0
 
8. **Limitations:**
   - No limitations were identified 
   
9. **Citations:**
   https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/ - Used this article to get help about printing coloured text in Java.