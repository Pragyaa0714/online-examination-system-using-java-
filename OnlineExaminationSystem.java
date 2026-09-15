import javax.swing.*;
import java.awt.*;

public class OnlineExaminationSystem extends JFrame {

    CardLayout cardLayout;
    JPanel mainPanel;

    String displayName = "Student";
    String currentPassword = "1234";

    boolean examRunning = false;
    boolean examSubmitted = false;
    boolean resultCardAdded = false;

    // ================= QUESTIONS =================

    String[] questions = {
        "Which language is used for Java GUI programming?",
        "Which keyword is used to create a class in Java?",
        "Which component is used for single-line text input?",
        "Which layout is used to switch between different screens?",
        "Which method is the starting point of a Java program?"
    };

    String[][] options = {
        {"Swing", "HTML", "Python", "SQL"},
        {"class", "create", "newclass", "object"},
        {"JTextField", "JLabel", "JButton", "JPanel"},
        {"CardLayout", "GridLayout", "FlowLayout", "BorderLayout"},
        {"start()", "main()", "run()", "begin()"}
    };

    int[] correctAnswers = {0, 0, 0, 0, 1};

    int[] userAnswers = {-1, -1, -1, -1, -1};

    int currentQuestion = 0;

    // ================= TIMER =================

    Timer timer;

    int totalExamTime = 30 * 60;
    int timeRemaining = 30 * 60;

    JLabel timerLabel;

    // ================= EXAM COMPONENTS =================

    JLabel questionNumberLabel;
    JLabel questionLabel;

    JRadioButton option1;
    JRadioButton option2;
    JRadioButton option3;
    JRadioButton option4;

    ButtonGroup optionGroup;

    // =================================================
    // CONSTRUCTOR
    // =================================================

    public OnlineExaminationSystem() {

        setTitle("Online Examination System");

        setSize(700, 500);

        setDefaultCloseOperation(
                JFrame.DO_NOTHING_ON_CLOSE
        );

        setLocationRelativeTo(null);

        // Window close button
        addWindowListener(
                new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(
                            java.awt.event.WindowEvent e) {

                        handleWindowClosing();
                    }
                }
        );

        cardLayout = new CardLayout();

        mainPanel = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel profilePanel = createProfilePanel();
        JPanel examPanel = createExamPanel();

        mainPanel.add(
                loginPanel,
                "LOGIN"
        );

        mainPanel.add(
                profilePanel,
                "PROFILE"
        );

        mainPanel.add(
                examPanel,
                "EXAM"
        );

        add(mainPanel);

        cardLayout.show(
                mainPanel,
                "LOGIN"
        );

        setVisible(true);
    }

    // =================================================
    // LOGIN SCREEN
    // =================================================

    private JPanel createLoginPanel() {

        JPanel panel = new JPanel(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        JLabel titleLabel =
                new JLabel(
                        "ONLINE EXAMINATION SYSTEM"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        JLabel usernameLabel =
                new JLabel("Username:");

        JTextField usernameField =
                new JTextField(20);

        JLabel passwordLabel =
                new JLabel("Password:");

        JPasswordField passwordField =
                new JPasswordField(20);

        JButton loginButton =
                new JButton("Login");

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(
                titleLabel,
                gbc
        );

        // Username
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(
                usernameLabel,
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                usernameField,
                gbc
        );

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                passwordLabel,
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                passwordField,
                gbc
        );

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        panel.add(
                loginButton,
                gbc
        );

        // Login action
        loginButton.addActionListener(e -> {

            String username =
                    usernameField.getText();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            if (username.equals("student")
                    && password.equals(currentPassword)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!"
                );

                cardLayout.show(
                        mainPanel,
                        "PROFILE"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password!",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        return panel;
    }

    // =================================================
    // PROFILE SCREEN
    // =================================================

    private JPanel createProfilePanel() {

        JPanel panel = new JPanel(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        JLabel titleLabel =
                new JLabel(
                        "PROFILE UPDATE"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        JLabel nameLabel =
                new JLabel(
                        "Display Name:"
                );

        JTextField nameField =
                new JTextField(
                        displayName,
                        20
                );

        JLabel passwordLabel =
                new JLabel(
                        "New Password:"
                );

        JPasswordField newPasswordField =
                new JPasswordField(20);

        JButton updateButton =
                new JButton(
                        "Update Profile"
                );

        JButton startExamButton =
                new JButton(
                        "Start Exam"
                );

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(
                titleLabel,
                gbc
        );

        // Name
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(
                nameLabel,
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                nameField,
                gbc
        );

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                passwordLabel,
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                newPasswordField,
                gbc
        );

        // Update
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        panel.add(
                updateButton,
                gbc
        );

        // Start exam
        gbc.gridy = 4;

        panel.add(
                startExamButton,
                gbc
        );

        // Update profile
        updateButton.addActionListener(e -> {

            String newName =
                    nameField.getText();

            String newPassword =
                    new String(
                            newPasswordField.getPassword()
                    );

            if (!newName.isEmpty()) {
                displayName = newName;
            }

            if (!newPassword.isEmpty()) {
                currentPassword = newPassword;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Profile Updated Successfully!"
            );
        });

        // Start exam
        startExamButton.addActionListener(e -> {

            currentQuestion = 0;

            for (int i = 0;
                 i < userAnswers.length;
                 i++) {

                userAnswers[i] = -1;
            }

            examSubmitted = false;
            timeRemaining = totalExamTime;
            examRunning = true;

            loadQuestion();

            startTimer();

            cardLayout.show(
                    mainPanel,
                    "EXAM"
            );
        });

        return panel;
    }

    // =================================================
    // EXAM SCREEN
    // =================================================

    private JPanel createExamPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        // Top
        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        questionNumberLabel =
                new JLabel(
                        "Question 1 of 5"
                );

        questionNumberLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        timerLabel =
                new JLabel(
                        "Time: 30:00"
                );

        timerLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        topPanel.add(
                questionNumberLabel,
                BorderLayout.WEST
        );

        topPanel.add(
                timerLabel,
                BorderLayout.EAST
        );

        panel.add(
                topPanel,
                BorderLayout.NORTH
        );

        // Center
        JPanel centerPanel =
                new JPanel();

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        questionLabel =
                new JLabel();

        questionLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        centerPanel.add(
                questionLabel
        );

        centerPanel.add(
                Box.createVerticalStrut(20)
        );

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        optionGroup =
                new ButtonGroup();

        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);

        centerPanel.add(option1);
        centerPanel.add(option2);
        centerPanel.add(option3);
        centerPanel.add(option4);

        panel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // Bottom
        JPanel bottomPanel =
                new JPanel();

        JButton previousButton =
                new JButton(
                        "Previous"
                );

        JButton nextButton =
                new JButton(
                        "Next"
                );

        JButton submitButton =
                new JButton(
                        "Submit Exam"
                );

        bottomPanel.add(
                previousButton
        );

        bottomPanel.add(
                nextButton
        );

        bottomPanel.add(
                submitButton
        );

        panel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // Previous
        previousButton.addActionListener(e -> {

            saveAnswer();

            if (currentQuestion > 0) {

                currentQuestion--;

                loadQuestion();
            }
        });

        // Next
        nextButton.addActionListener(e -> {

            saveAnswer();

            if (currentQuestion
                    < questions.length - 1) {

                currentQuestion++;

                loadQuestion();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "This is the last question."
                );
            }
        });

        // Submit
        submitButton.addActionListener(e -> {

            saveAnswer();

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to submit the exam?",
                            "Confirm Submission",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                submitExam();
            }
        });

        return panel;
    }

    // =================================================
    // LOAD QUESTION
    // =================================================

    private void loadQuestion() {

        questionNumberLabel.setText(
                "Question "
                        + (currentQuestion + 1)
                        + " of "
                        + questions.length
        );

        questionLabel.setText(
                questions[currentQuestion]
        );

        option1.setText(
                "A. "
                        + options[currentQuestion][0]
        );

        option2.setText(
                "B. "
                        + options[currentQuestion][1]
        );

        option3.setText(
                "C. "
                        + options[currentQuestion][2]
        );

        option4.setText(
                "D. "
                        + options[currentQuestion][3]
        );

        optionGroup.clearSelection();

        if (userAnswers[currentQuestion] == 0) {

            option1.setSelected(true);

        } else if (
                userAnswers[currentQuestion] == 1) {

            option2.setSelected(true);

        } else if (
                userAnswers[currentQuestion] == 2) {

            option3.setSelected(true);

        } else if (
                userAnswers[currentQuestion] == 3) {

            option4.setSelected(true);
        }
    }

    // =================================================
    // SAVE ANSWER
    // =================================================

    private void saveAnswer() {

        if (option1.isSelected()) {

            userAnswers[currentQuestion] = 0;

        } else if (option2.isSelected()) {

            userAnswers[currentQuestion] = 1;

        } else if (option3.isSelected()) {

            userAnswers[currentQuestion] = 2;

        } else if (option4.isSelected()) {

            userAnswers[currentQuestion] = 3;
        }
    }

    // =================================================
    // TIMER
    // =================================================

    private void startTimer() {

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(
                1000,
                e -> {

                    if (timeRemaining <= 0) {
                        if (timer != null) {
                            timer.stop();
                        }
                        submitExam();
                        return;
                    }

                    timeRemaining--;
                    updateTimerDisplay();

                    if (timeRemaining <= 0) {

                        if (timer != null) {
                            timer.stop();
                        }

                        examRunning = false;

                        JOptionPane.showMessageDialog(
                                this,
                                "Time is over!\nYour exam will be submitted automatically."
                        );

                        submitExam();
                    }
                }
        );

        timer.start();

        updateTimerDisplay();
    }

    // =================================================
    // TIMER DISPLAY
    // =================================================

    private void updateTimerDisplay() {

        int minutes =
                timeRemaining / 60;

        int seconds =
                timeRemaining % 60;

        timerLabel.setText(
                String.format(
                        "Time: %02d:%02d",
                        minutes,
                        seconds
                )
        );
    }

    // =================================================
    // SUBMIT EXAM
    // =================================================

    private void submitExam() {

        if (examSubmitted) {
            return;
        }

        examSubmitted = true;
        saveAnswer();

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        examRunning = false;

        int score = 0;

        for (int i = 0;
             i < questions.length;
             i++) {

            if (userAnswers[i]
                    == correctAnswers[i]) {

                score++;
            }
        }

        int safeRemaining = Math.max(0, timeRemaining);
        int timeTaken = totalExamTime - safeRemaining;

        showResult(
                score,
                timeTaken
        );
    }

    // =================================================
    // RESULT SCREEN
    // =================================================

    private void showResult(
            int score,
            int timeTaken) {

        JPanel resultPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        JLabel titleLabel =
                new JLabel(
                        "EXAM RESULT",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        resultPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        JPanel infoPanel =
                new JPanel();

        infoPanel.setLayout(
                new BoxLayout(
                        infoPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel studentLabel =
                new JLabel(
                        "Student: "
                                + displayName
                );

        JLabel scoreLabel =
                new JLabel(
                        "Score: "
                                + score
                                + " out of "
                                + questions.length
                );

        int minutes =
                timeTaken / 60;

        int seconds =
                timeTaken % 60;

        JLabel timeLabel =
                new JLabel(
                        String.format(
                                "Time Taken: %02d:%02d",
                                minutes,
                                seconds
                        )
                );

        studentLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );

        scoreLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        timeLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );

        infoPanel.add(
                studentLabel
        );

        infoPanel.add(
                Box.createVerticalStrut(10)
        );

        infoPanel.add(
                scoreLabel
        );

        infoPanel.add(
                Box.createVerticalStrut(10)
        );

        infoPanel.add(
                timeLabel
        );

        infoPanel.add(
                Box.createVerticalStrut(20)
        );

        // Breakdown
        JTextArea breakdown =
                new JTextArea();

        breakdown.setEditable(false);

        breakdown.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        breakdown.append(
                "Answer Breakdown:\n\n"
        );

        for (int i = 0;
             i < questions.length;
             i++) {

            if (userAnswers[i]
                    == correctAnswers[i]) {

                breakdown.append(
                        "Question "
                                + (i + 1)
                                + ": Correct\n"
                );

            } else {

                breakdown.append(
                        "Question "
                                + (i + 1)
                                + ": Incorrect\n"
                );
            }
        }

        infoPanel.add(
                new JScrollPane(
                        breakdown
                )
        );

        resultPanel.add(
                infoPanel,
                BorderLayout.CENTER
        );

        // Bottom
        JPanel bottomPanel =
                new JPanel();

        JButton logoutButton =
                new JButton(
                        "Logout"
                );

        bottomPanel.add(
                logoutButton
        );

        resultPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // Logout action
        logoutButton.addActionListener(e -> {

            if (timer != null) {
                timer.stop();
            }

            examRunning = false;
            examSubmitted = false;

            currentQuestion = 0;

            for (int i = 0;
                 i < userAnswers.length;
                 i++) {

                userAnswers[i] = -1;
            }

            timeRemaining = totalExamTime;

            cardLayout.show(
                    mainPanel,
                    "LOGIN"
            );
        });

        if (!resultCardAdded) {
            mainPanel.add(
                    resultPanel,
                    "RESULT"
            );
            resultCardAdded = true;
        }

        cardLayout.show(
                mainPanel,
                "RESULT"
        );
    }

    // =================================================
    // WINDOW CLOSE HANDLING
    // =================================================

    private void handleWindowClosing() {

        if (examRunning) {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to quit the exam?",
                            "Quit Exam",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                if (timer != null) {
                    timer.stop();
                }

                examRunning = false;

                dispose();
            }

        } else {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to exit?",
                            "Exit",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                dispose();
            }
        }
    }

    // =================================================
    // MAIN METHOD
    // =================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new OnlineExaminationSystem();

        });
    }
}