package seminar01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private final int WINDOW_HIGHT = 230;
    private final int WINDOW_WIDTH = 400;
    private GameWindow gameWindow;
    private int gameFieldSizeX = 3;
    private int gameFieldSizeY = 3;
    private int gameLengthSize = 3;
    private int gameMode = 0;

    JButton btnStart = new JButton("Start new Game!!!");

    SettingsWindow(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        setLocationRelativeTo(this.gameWindow);
        setSize(WINDOW_WIDTH,WINDOW_HIGHT);

        //Создаем панель для размещения надписей и кнопочек
        JPanel settingsPanel = new JPanel();

        //Создаем слой типа сетка с указанием того что все должно быть размещено в один столбец и с расстояниями между
        // элементами в 3 по вертикали и 3 по горизонтали
        GridLayout layout = new GridLayout(0,1,3,0);
        settingsPanel.setLayout(layout);

        //region ВЫБОР ТИПА ИГРЫ

        //Первый лэйбл. Выбор типа игры.
        JLabel greetingsLabel = new JLabel("Выберете режим игры:");
        //Разместим заголовок по центру
        greetingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //Зададим шрифт побольше и по жирнее чтобы было понятно что это отдельный раздел
        greetingsLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        settingsPanel.add(greetingsLabel);


        //Создадим две радиокнопки
        JRadioButton hThBtn = new JRadioButton("Человек против человека");
        JRadioButton hTcBtn = new JRadioButton("Человек против компьютера");
        //зададим им шрифт поменьше и по спокойнее. всетаки это варианты, а не заголовок
        hTcBtn.setFont(new Font("Verdana", Font.PLAIN, 10));
        hThBtn.setFont(new Font("Verdana", Font.PLAIN, 10));
        //Создадим группу кнопок чтобы можно было указать что эти кнопки относятся к одной группе
        ButtonGroup gameTypeGroup = new ButtonGroup();
        //Добавим кнопки в группу
        gameTypeGroup.add(hThBtn);
        gameTypeGroup.add(hTcBtn);
        //Создадим панель для размещения кнопок в строку (я так хочу, я художник - я так вижу)
        JPanel gameTypeGroupPanel = new JPanel(new GridLayout(1,0,3,0));
        //Добавим кнопки на панель
        gameTypeGroupPanel.add(hThBtn);
        gameTypeGroupPanel.add(hTcBtn);
        //Разместим панель с кнопками на главной панели окна настроек
        settingsPanel.add(gameTypeGroupPanel);
        //endregion

        //region ВЫБОР РАЗМЕРА ПОЛЯ
        //Лэйбл, по образу и подобию, как и в выборе типа игры.
        //Только разделитель добавим для наглядности
        settingsPanel.add(new JSeparator());
        JLabel fieldSizeLabel = new JLabel("Выберете режим игры:");
        //Разместим заголовок по центру
        fieldSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //Зададим шрифт побольше и по жирнее чтобы было понятно что это отдельный раздел
        fieldSizeLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        settingsPanel.add(fieldSizeLabel);

        JLabel fieldSizeCurrent = new JLabel("Размер игрового поля: "+gameFieldSizeX+" x "+gameFieldSizeY);
        //Разместим заголовок по центру
        fieldSizeCurrent.setHorizontalAlignment(SwingConstants.CENTER);
        //Зададим шрифт как радио-кнопкам, унификация :)
        fieldSizeCurrent.setFont(new Font("Verdana", Font.PLAIN, 10));
        settingsPanel.add(fieldSizeCurrent);

        JSlider gameSizeSlider = new JSlider(3,10,3);
        settingsPanel.add(gameSizeSlider);
        gameFieldSizeX = gameSizeSlider.getValue();
        gameFieldSizeY = gameSizeSlider.getValue();
        //endregion

        //region ВЫБОР ДИНЫ ЛИНИИ ПОБЕДЫ
        //Лэйбл, по образу и подобию, как и в выборе типа игры.
        //Только разделитель добавим для наглядности
        settingsPanel.add(new JSeparator());
        JLabel lengthValueLabel = new JLabel("Выберете длину линии для победы:");
        //Разместим заголовок по центру
        lengthValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //Зададим шрифт побольше и по жирнее чтобы было понятно что это отдельный раздел
        lengthValueLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        settingsPanel.add(lengthValueLabel);

        JLabel lengthSizeCurrentLabel = new JLabel("Длина линии: "+gameLengthSize);
        //Разместим заголовок по центру
        lengthSizeCurrentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //Зададим шрифт как радио-кнопкам, унификация :)
        lengthSizeCurrentLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
        settingsPanel.add(lengthSizeCurrentLabel);

        JSlider lengthValueSlider = new JSlider(3,10,3);
        settingsPanel.add(lengthValueSlider);
        //endregion

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startNewGame(gameMode,gameFieldSizeX,gameFieldSizeY,gameLengthSize);
                setVisible(false);
            }
        });
        settingsPanel.add(btnStart);

        add(settingsPanel);
    }
}