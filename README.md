# Esraa's AI ChatBot

This is a simple desktop chatbot application built in Java using Swing for the GUI. The chatbot connects to a GPT-based AI API to generate responses in real time.

## Features

- Clean, scrollable chat window with multiline text support.
- Send messages by pressing Enter or clicking the Send button.
- Interacts with an AI language model through a custom API endpoint.
- Basic but extendable UI using Java Swing.
- Handles JSON responses to extract and display AI-generated replies.

## Getting Started

### Prerequisites

- Java JDK 8 or above.
- Internet connection (to connect with the AI API).
- `org.json` library included in your project to parse JSON responses.

### How to Run

1. Clone or download this repository.
2. Add the `org.json` library to your project build path.
3. Compile and run the `Bot` class.
4. Type messages into the chat box and receive AI-generated replies.

### Customization

- You can update the API endpoint, API key, and model in the `chatGPT` method to use different AI services.
- The UI layout and behavior can be further customized or extended with more features like voice input or file sharing.

## Notes

- Keep your API key secure and do not hard-code it for production use.
- The current API endpoint used in the code is a third-party hosted GPT API; ensure you have the right access.
- This project is designed primarily for educational and experimental use.

## License

This project is open for personal and educational use.

---

