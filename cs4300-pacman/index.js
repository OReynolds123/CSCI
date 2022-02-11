import { DIRECTIONS, LEVEL, OBJECT_TYPE } from './setup';
import { randomMovement } from './ghostmoves';
// Classes
import GameBoard from './GameBoard';
import Pacman from './Pacman';
import Ghost from './Ghost';
// Sounds
import soundDot from './sounds/munch.wav';
import soundPill from './sounds/pill.wav';
import soundGameStart from './sounds/game_start.wav';
import soundGameOver from './sounds/death.wav';
import soundGhost from './sounds/eat_ghost.wav';
// Dom Elements
const gameGrid = document.querySelector('#game');
const scoreTable = document.querySelector('#score');
const startButton = document.querySelector('#start-button');
// Game constants
const POWER_PILL_TIME = 10000; // ms
const GLOBAL_SPEED = 40; // #2) Changed this from 80 to 40 to make game run smoother.
const gameBoard = GameBoard.createGameBoard(gameGrid, LEVEL);
// Initial setup
let score = 0;
let timer = null;
let gameWin = false;
let powerPillActive = false;
let powerPillTimer = null;

// --- AUDIO --- //
// This function takes in a variable with the location of an audio file.
// It creates an audio object from the url and plays the corresponding audio file.
function playAudio(audio) {
  // Creates an object from the supplied file
  const soundEffect = new Audio(audio);
  // Plays the corresponding audio file
  soundEffect.play();
}

// --- GAME CONTROLLER --- //
// This function takes in a pacman object.
// It is responsible for what happens at the end of a game
// It plays a game over sound, removes the keydown handler that controls the pacman,
// displays the status (if you won or not), resets the timer, and shows the start game button.
function gameOver(pacman, grid) {
  // Plays game over sound
  playAudio(soundGameOver);
  // Removes the keydown handler that controls the pacman
  document.removeEventListener('keydown', (e) =>
    pacman.handleKeyInput(e, gameBoard.objectExist.bind(gameBoard))
  );
  // Displays the gameWin/lose message
  gameBoard.showGameStatus(gameWin);
  // Resets the timer
  clearInterval(timer);
  // Shows the start game button
  startButton.classList.remove('hide');
}

// This function is responsible for checking if the pacman has collided with the ghosts
// It takes in the pacman object. If the powerPill is active on a collision, it plays a sound, removes the ghost, and adds to the score.
// If the powerPill is not active, it removes the pacman and ends the game
function checkCollision(pacman, ghosts) {
  // Creates a var with to check if the pacman has collided with the ghosts
  const collidedGhost = ghosts.find((ghost) => pacman.pos === ghost.pos);

  // If the collidedGhost variable is true then ...
  if (collidedGhost) {
    // If the powerPill is active then ...
    if (pacman.powerPill) {
      // Play a sound
      playAudio(soundGhost);
      // Remove the ghost object from the board
      gameBoard.removeObject(collidedGhost.pos, [
        OBJECT_TYPE.GHOST,
        OBJECT_TYPE.SCARED,
        collidedGhost.name
      ]);
      // Change the ghost current position to its starting position
      collidedGhost.pos = collidedGhost.startPos;
      // Add 100 to the score
      score += 100;
      // If the powerPill is no active
    } else {
      /* THIS WAS THE OLD CODE
      // Remove the pacman object
      gameBoard.removeObject(pacman.pos, [OBJECT_TYPE.PACMAN]);
      // Rotate the pacman object to its initial looking position
      gameBoard.rotateDiv(pacman.pos, 0);
      // Call the gameOver function to display the game over message
      gameOver(pacman, gameGrid);
      */
      // #4)
      // Change the score to 0
      score = 0;
      // Reverse the direction of the pacman
      reverseDirection(pacman);
      // Reverse the direction of the collided ghost
      reverseDirection(collidedGhost);
    }
  }
}
// This function was created for question 4
// It reverses the direction of an object
function reverseDirection(object) {
  if (object.dir == DIRECTIONS.ArrowLeft) {
    object.dir = DIRECTIONS.ArrowRight;
  } else if (object.dir == DIRECTIONS.ArrowRight) {
    object.dir = DIRECTIONS.ArrowLeft;
  } else if (object.dir == DIRECTIONS.ArrowUp) {
    object.dir = DIRECTIONS.ArrowDown;
  } else if (object.dir == DIRECTIONS.ArrowDown) {
    object.dir = DIRECTIONS.ArrowUp;
  }
}

// This is the main gameLoop for the pacman game
// It is responsible for moving the pacman and ghosts, checking for collisions, removing dots/powerPills, and checking if the game is won
function gameLoop(pacman, ghosts) {
  // 1. Move Pacman
  gameBoard.moveCharacter(pacman);
  // 2. Check Ghost collision on the old positions
  checkCollision(pacman, ghosts);
  // 3. Move ghosts
  ghosts.forEach((ghost) => gameBoard.moveCharacter(ghost));
  // 4. Do a new ghost collision check on the new positions
  checkCollision(pacman, ghosts);
  // 5. Check if Pacman eats a dot
  if (gameBoard.objectExist(pacman.pos, OBJECT_TYPE.DOT)) {
    // Play a sound
    playAudio(soundDot);
    // Remove the dot object
    gameBoard.removeObject(pacman.pos, [OBJECT_TYPE.DOT]);
    // Decrease dot count
    gameBoard.dotCount--;
    // Add Score
    score += 10;
  }
  // 6. Check if Pacman eats a power pill
  if (gameBoard.objectExist(pacman.pos, OBJECT_TYPE.PILL)) {
    // Play a sound
    playAudio(soundPill);
    // Remove the powerPill object
    gameBoard.removeObject(pacman.pos, [OBJECT_TYPE.PILL]);
    // Set the powerPill var to true
    pacman.powerPill = true;
    // Add score
    score += 50;
    // Reset the powerPill timer
    clearTimeout(powerPillTimer);
    // Make the powerPill var false after POWER_PILL_TIME ms has gone by
    powerPillTimer = setTimeout(
      () => (pacman.powerPill = false),
      POWER_PILL_TIME
    );
  }
  // 7. Change ghost scare mode depending on powerpill
  if (pacman.powerPill !== powerPillActive) {
    // Change the powerPillActive var to match the powerPill status
    powerPillActive = pacman.powerPill;
    // Make each ghost scared/not scared depending on if the powerPill is active or not
    ghosts.forEach((ghost) => (ghost.isScared = pacman.powerPill));
  }
  // 8. Check if all dots have been eaten
  if (gameBoard.dotCount === 0) {
    // Sets the gameWin var to true
    gameWin = true;
    // Calls the gameOver function to display the winning message
    gameOver(pacman, gameGrid);
  }
  // 9. Show new score
  scoreTable.innerHTML = score;
}

// A function that starts the game
// It plays audio, resets all of the variables, redraws the objects, and resets the gameloop timer
function startGame() {
  // Play a sound
  playAudio(soundGameStart);
  // Set gameWin var to false
  gameWin = false;
  // Set powerPillActive var to false
  powerPillActive = false;
  // Set score to 0
  score = 0;
  // Hide the start game button
  startButton.classList.add('hide');
  // Draw the grid
  gameBoard.createGrid(LEVEL);
  // Create the pacman object
  const pacman = new Pacman(2, 287);
  // Add the pacman object to the grid
  gameBoard.addObject(287, [OBJECT_TYPE.PACMAN]);
  // Create a keydown handler to move the pacman based on inputs
  document.addEventListener('keydown', (e) =>
    pacman.handleKeyInput(e, gameBoard.objectExist.bind(gameBoard))
  );
  // Create the different ghosts
  const ghosts = [
    new Ghost(5, 188, randomMovement, OBJECT_TYPE.BLINKY),
    new Ghost(4, 209, randomMovement, OBJECT_TYPE.PINKY),
    new Ghost(3, 230, randomMovement, OBJECT_TYPE.INKY),
    new Ghost(2, 251, randomMovement, OBJECT_TYPE.CLYDE)
  ];

  // Gameloop
  timer = setInterval(() => gameLoop(pacman, ghosts), GLOBAL_SPEED);
}

// Initialize game if the start button is clicked
startButton.addEventListener('click', startGame);