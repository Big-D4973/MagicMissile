package duel;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public class MenuPanel extends JPanel {
    private Image bgMenu;
    private final JComboBox<MapType> mapBox = new JComboBox<>(MapType.values());

    public MenuPanel(BiConsumer<GameMode, MapType> onStart) {
        setPreferredSize(new Dimension(GamePanel.W, GamePanel.H));
        setLayout(new GridBagLayout());

        // โหลดพื้นหลังเมนู
        try {
            bgMenu = Assets.img("/duel/resources/Menu1.png");
        } catch (Throwable t) {
            bgMenu = new ImageIcon("src/duel/resources/Menu1.png").getImage();
        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0; c.fill=GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 10, 0);

        JLabel title = new JLabel("Magic Missile", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 40f));

        JLabel mapLabel = new JLabel("Select Map", SwingConstants.CENTER);
        mapLabel.setForeground(Color.WHITE);
        mapLabel.setFont(mapLabel.getFont().deriveFont(Font.BOLD, 16f));
        mapBox.setSelectedItem(MapType.CLASSIC);

        JButton pvp = new JButton("Play vs Player");
        JButton pvc = new JButton("Play vs COM");

        pvp.addActionListener(e -> onStart.accept(GameMode.PVP, selectedMap()));
        pvc.addActionListener(e -> onStart.accept(GameMode.PVC, selectedMap()));

        c.gridy=0; add(title, c);
        c.gridy=1; add(mapLabel, c);
        c.gridy=2; add(mapBox, c);
        c.gridy=3; add(pvp, c);
        c.gridy=4; add(pvc, c);

        setOpaque(false); // ให้พื้นหลังวาดเอง
    }

    public MenuPanel(App app) {
        this((mode, map) -> app.showGame(mode, map));
    }

    private MapType selectedMap() {
        MapType m = (MapType) mapBox.getSelectedItem();
        return (m == null) ? MapType.CLASSIC : m;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // วาดพื้นหลังเมนูให้เต็มจอ
        if (bgMenu != null) {
            g.drawImage(bgMenu, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(new Color(18,20,26));
            g.fillRect(0,0,getWidth(),getHeight());
        }
        super.paintComponent(g);
    }
}
