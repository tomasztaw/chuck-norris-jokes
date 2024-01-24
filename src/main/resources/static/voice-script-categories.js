let speech = new SpeechSynthesisUtterance();

window.speechSynthesis.onvoiceschanged = () => {
    const selectedVoice = window.speechSynthesis.getVoices().find(voice => voice.name === "Google UK English Male");

    if (selectedVoice) {
        speech.voice = selectedVoice;
    } else {
        console.warn("'Google UK English Male' voice is not available.");
    }
};

document.addEventListener("DOMContentLoaded", function() {
    const speakButton = document.querySelector(".speak-button");
    const nextJokeButton = document.querySelector(".next-joke-button");
    const homePageButton = document.querySelector(".home-page-button");
    const changeCategoryButton = document.querySelector(".change-category-button");

    speakButton.addEventListener("click", () => {
        speech.text = document.querySelector("p").textContent;
        window.speechSynthesis.speak(speech);
    });

    nextJokeButton.addEventListener("click", () => {
        location.reload();
    });

    homePageButton.addEventListener("click", () => {
        window.location.href = "/";
    });

    changeCategoryButton.addEventListener("click", () => {
        window.location.href = "/random/categories";
    });
});
