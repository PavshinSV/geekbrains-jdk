package seminar01;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    Map(){
        setBackground(Color.BLUE);
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen){
        System.out.printf("Mode: %d\nSize: %d, %d; Length: %d\n-------\n", mode, fSzX, fSzY, wLen);
    }
}
