import javax.swing.JFrame;

public class StarterFace extends JFrame {

    public StarterFace()
    {
        add(new BoardFace());
        setTitle("Board");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new StarterFace();
    }
}