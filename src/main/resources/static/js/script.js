const wordDisplay = document.querySelector(".word-display");
const hangmanImage = document.querySelector(".hangman-box img");
const guessesText = document.querySelector(".guesses-text b");
const keyboardDiv = document.querySelector(".keyboard");
// const gameModal = document.querySelector(".game-over");
// const playAgainButton = document.querySelector(".play-again");
let currentWord, correctLetters, wrongGuessedCount;
const maxGuesses = 6;
let spaceLetter = 0;
const resetGame = () => {
    correctLetters = [];
    wrongGuessedCount = 0;
    spaceLetter = 0;
    hangmanImage.src = `images/start.jpg`;
    guessesText.innerText = `${wrongGuessedCount} / ${maxGuesses}`;
    wordDisplay.innerHTML = currentWord.split("").map(letter => {
        if (letter === ' ') {
            spaceLetter++;
            return '<li class="space"></li>';
        } else {
            return '<li class="letter"></li>';
        }
    }).join("");
    // TODO: should add here func if having space to make length of the word -1
    keyboardDiv.querySelectorAll("button").forEach(btn => btn.disabled = false);
    // gameModal.classList.remove("show");
}
const gameOver = (isVictory) => {
    setTimeout(() => {
        const isGameOverOrVictory = isVictory ? `You found the word:` : `The correct word was:`;
        if (isVictory) {
            window.location.href = "/won"; // Redirect to URL for victory


        } else {
            window.location.href = "/lost"; // Redirect to URL for loss
            // gameModal.querySelector("h4").innerText = `${isVictory ? 'Congrats!' : 'Game Over!'}`;
            // gameModal.querySelector("p").innerHTML = `${isGameOverOrVictory} <b>${currentWord}</b>`;
            // gameModal.classList.add("show");

        }
    }, 300);
}

const getRandomWord = () => {
    currentWord = wordDisplay.textContent.trim();
    console.log(currentWord);
    resetGame();
}
const initGame = (button, clickedLetter) => {
    if (currentWord.includes(clickedLetter)) {
        [...currentWord].forEach((letter, index) => {
            if (letter === clickedLetter) {
                correctLetters.push(letter);
                wordDisplay.querySelectorAll("li")[index].innerText = letter;
                wordDisplay.querySelectorAll("li")[index].classList.add("guessed");
            }
        })
    } else {
        wrongGuessedCount++;
        hangmanImage.src = `images/mistake-${wrongGuessedCount}.jpg`;
    }
    if (wrongGuessedCount === maxGuesses) return gameOver(false);
    if (correctLetters.length === currentWord.length - spaceLetter) return gameOver(true);
    button.disabled = true;
    guessesText.innerText = `${wrongGuessedCount} / ${maxGuesses}`;

}
if (window.location.href.includes("localhost:8080/play")) {
    for (let i = 97; i <= 122; i++) {
        const button = document.createElement("button");
        button.innerText = String.fromCharCode(i);
        keyboardDiv.appendChild(button);
        button.addEventListener("click", e => initGame(e.target, String.fromCharCode(i)));
    }

    getRandomWord();
}
// playAgainButton.addEventListener("click", () => location.reload());

