package HomeWork8;


    import javax.swing.*;
    import javax.swing.event.ChangeEvent;
    import javax.swing.event.ChangeListener;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    public class MyGameWindow extends JFrame {
        private ImageIcon image_X;
        private ImageIcon image_0;
        int elementSize = 50;
        private int size_X = 30;
        private int size_Y = 15;
        int winLineLength = 5;
        private JPanel gameFieldPanel;
        Field field;
        private boolean playerVsAi = true;

        public static void main(String[] args) {
            new MyGameWindow();
        }

        public MyGameWindow() {
            setTitle("Крестики-нолики");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setResizable(false);
            setSize(elementSize * size_X, elementSize * size_Y);
            setLocationRelativeTo(null);

            image_0 = new ImageIcon("src/images/smile3.png");
            image_X = new ImageIcon("src/images/smile4.png");
            gameFieldPanel = new JPanel();
            createMenu();
//        newGame();
//        drawField(true);
            add(gameFieldPanel);

            setVisible(true);
            new SettingWindow();
        }

        class SettingWindow extends JFrame {
            private JLabel labelFieldSize;
            private JLabel labelWinLength;
            private int temp_sizeX;
            private int temp_sizeY;
            private int temp_winLineLength;
            private JRadioButton humanVsAi;
            private JRadioButton humanVsHuman;

            public SettingWindow() {
                temp_sizeX = size_X;
                temp_sizeY = size_Y;
                temp_winLineLength = winLineLength;

                setTitle("Настройки");
                setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                setResizable(false);
                setSize(300, 300);
                setLocationRelativeTo(null);
                setLayout(new GridLayout(8, 1));

                addGameMode();
                addGameSize();


                add(new JLabel(""));
                JButton btnStart = new JButton("Start new game");
                btnStart.addActionListener(e -> submitSettings());
                add(btnStart);
                setVisible(true);
            }

            private void submitSettings() {
                playerVsAi = humanVsAi.isSelected();
                size_Y = temp_sizeY;
                size_X = temp_sizeX;
                winLineLength = temp_winLineLength;
                setVisible(false);
                newGame();
            }

            private void addGameSize() {
                labelFieldSize = new JLabel();
                labelWinLength = new JLabel();
                updateTextElements();
                JSlider sliderFieldWidth = new JSlider(3, 35, temp_sizeX);
                sliderFieldWidth.addChangeListener(e -> {
                    int currentValue = sliderFieldWidth.getValue();
                    if (currentValue == 3) {
                        temp_winLineLength = 3;
                    } else if (currentValue <= 5) {
                        temp_winLineLength = 4;
                        sliderFieldWidth.setValue(currentValue);
                    } else {
                        temp_winLineLength = 5;
                    }
                    temp_sizeY = currentValue;
                    temp_sizeX = currentValue;
                    if (temp_sizeY > 15) temp_sizeY = 15;
                    updateTextElements();
                });
                add(labelFieldSize);
                add(sliderFieldWidth);
                add(labelWinLength);
            }

            private void updateTextElements() {
                labelFieldSize.setText(String.format("Размеры поля: %d на %d", temp_sizeX, temp_sizeY));
                if (temp_winLineLength == 5)
                    labelWinLength.setText(String.format("Победа = %d клеток", temp_winLineLength));
                else
                    labelWinLength.setText(String.format("Победа = %d клетки", temp_winLineLength));
            }

            private void addGameMode() {
                Label labelMode = new Label("Режим игры:");
                labelMode.setFont(new Font("Arial", Font.BOLD, 20));
                humanVsAi = new JRadioButton("Против компьютера", playerVsAi);
                humanVsHuman = new JRadioButton("Два игрока", !playerVsAi);
                ButtonGroup gameMode = new ButtonGroup();

                gameMode.add(humanVsAi);
                gameMode.add(humanVsHuman);

                add(labelMode);
                add(humanVsAi);
                add(humanVsHuman);
            }
        }

        private void createMenu() {
            JMenuBar menuBar = new JMenuBar();

            JMenu new_game_menu = new JMenu("Новая игра");
            JMenuItem new_game = new JMenuItem("Новая игра");
            JMenuItem settings = new JMenuItem("Настройки");
            JMenu select_images = new JMenu("Images");
            JMenuItem exitItem = new JMenuItem("Exit");

            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            new_game.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    newGame();
                }
            });
            settings.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new SettingWindow();
                }
            });

            JMenuItem cats = new JMenuItem("Котики");
            cats.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    image_0 = new ImageIcon("src/images/dog.png");
                    image_X = new ImageIcon("src/images/cat.png");
                    refreshField(true);
                }
            });

            JMenuItem smiles = new JMenuItem("Смайлики");
            smiles.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    image_X = new ImageIcon("src/images/smile1.png");
                    image_0 = new ImageIcon("src/images/smile2.png");
                    refreshField(true);
                }
            });
            JMenuItem bugs = new JMenuItem("Жуки");
            bugs.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    image_X = new ImageIcon("src/images/smile4.png");
                    image_0 = new ImageIcon("src/images/smile3.png");
                    refreshField(true);
                }
            });

            select_images.add(cats);
            select_images.add(smiles);
            select_images.add(bugs);

            exitItem.setMaximumSize(new Dimension(100, 100));
            new_game_menu.add(new_game);
            new_game_menu.add(settings);
            menuBar.add(new_game_menu);
            menuBar.add(select_images);
            menuBar.add(exitItem);
            setJMenuBar(menuBar);
        }

        private void updateDot(int i, int j) {
            if (field.getArraySymbol(i, j) == '0')
                ((JButton) gameFieldPanel.getComponent(i * size_X + j)).setIcon(image_0);
            else if (field.getArraySymbol(i, j) == 'X') {
                ((JButton) gameFieldPanel.getComponent(i * size_X + j)).setIcon(image_X);
            }
        }

        public void refreshField(boolean isFullUpdate) {
            if (isFullUpdate) {
                gameFieldPanel.setVisible(false);
                for (int i = 0; i < field.getArrayLength(); i++) {
                    for (int j = 0; j < field.getArrayLength(i); j++) {
                        updateDot(i, j);
                        if (field.isWinDot(i, j))
                            ((JButton) gameFieldPanel.getComponent(i * size_X + j)).setBackground(new Color(0x94C2BE));
                    }
                }
                gameFieldPanel.setVisible(true);
            } else {
                updateDot(field.getLastStepI(), field.getLastStepJ());
            }
            //setVisible(true);
        }

        private void newGame() {
            field = new Field(size_X, size_Y, winLineLength);
            if (elementSize * size_X < 400) {
                setSize(400, 400);
            } else {
                setSize(elementSize * size_X, elementSize * size_Y);
            }
            setLocationRelativeTo(null);
            gameFieldPanel.setLayout(new GridLayout(size_Y, size_X));
            createJButtonsField();
            field.clear();
            refreshField(true);
            if (field.getCurrentState() == Field.BOT_STEP && playerVsAi) {
                JOptionPane.showMessageDialog(null, "Не против, если я буду ходить первым?");
                field.doStep();
                refreshField(false);
            } else if (playerVsAi)
                JOptionPane.showMessageDialog(null, "Ходи первый!");
            else JOptionPane.showMessageDialog(null, "Начинайте игру!");
        }

        private void createJButtonsField() {
            gameFieldPanel.removeAll();
            for (int i = 0; i < size_X * size_Y; i++) {
                JButton button = new JButton();
                int finalI = i;
                button.addActionListener(actionEvent -> click(finalI / size_X, finalI % size_X));
                gameFieldPanel.add(button);
            }
        }

        private void click(int i, int j) {
            if (field.isGameOver()) {
                new SettingWindow();
                return;
            }
            if (!field.doStep(i, j)) {
                return;
            }
            refreshField(false);
            if (field.isGameOver()) {
                drawResult();
                return;
            }
            if (!playerVsAi)
                return;
            field.doStep();
            refreshField(false);
            if (field.isGameOver()) {
                drawResult();
            }
        }

        private void drawResult() {
            refreshField(true);
            if (field.getCurrentState() == Field.STANDOFF)
                JOptionPane.showMessageDialog(null, "Больше линий здесь не нарисовать! Ничья!");
            else if (!playerVsAi)
                JOptionPane.showMessageDialog(null, "Победа!!");
            else if (field.getCurrentState() == Field.botWin)
                JOptionPane.showMessageDialog(null, "ХА! Комп Победил!");
            else if (field.getCurrentState() == Field.playerWin)
                JOptionPane.showMessageDialog(null, "Мои поздравления! Ты выиграл!");
        }
    }

