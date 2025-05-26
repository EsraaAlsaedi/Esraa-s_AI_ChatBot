package chatbot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.*;

public class Bot extends JFrame {

    private JTextArea chatArea;
    private JTextField chatBox;
    private JButton sendButton;

    public Bot() {
        setTitle("Esraa's AI ChatBot 🤖");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        chatBox = new JTextField();
        sendButton = new JButton("Send");

        bottomPanel.add(chatBox, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        ActionListener sendAction = e -> sendMessage();
        chatBox.addActionListener(sendAction);
        sendButton.addActionListener(sendAction);

        setVisible(true);
    }

    private void sendMessage() {
        String userText = chatBox.getText().trim();
        if (userText.isEmpty()) return;

        chatArea.append("You: " + userText + "\n");
        chatBox.setText("");
        chatBox.setEnabled(false);
        sendButton.setEnabled(false);

        new Thread(() -> {
            String reply = chatGPT(userText);
            SwingUtilities.invokeLater(() -> {
                bot(reply);
                chatBox.setEnabled(true);
                sendButton.setEnabled(true);
                chatBox.requestFocusInWindow();
            });
        }).start();
    }

    private void bot(String response) {
        chatArea.append("Esraa's Bot: " + response + "\n\n");
    }

    public static String chatGPT(String message) {
        try {
            String apiKey = "c4c923aa61d991e1e014ab36168f2391";
            String model = "gpt-3.5-turbo";
            String apiUrl = "http://195.179.229.119/gpt/api.php?" +
                    "prompt=" + URLEncoder.encode(message, "UTF-8") +
                    "&api_key=" + URLEncoder.encode(apiKey, "UTF-8") +
                    "&model=" + URLEncoder.encode(model, "UTF-8");

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            return extractContentFromResponse(response.toString());

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static String extractContentFromResponse(String json) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            return jsonObj.getString("content").trim();
        } catch (JSONException e) {
            return "Failed to parse AI response.";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Bot::new);
    }
}
