package paint.components;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Controls extends JPanel {
    private final PaintArea paintArea;

    public Controls(PaintArea paintArea) {
        this.paintArea = paintArea;
        JFileChooser fileChooser = new JFileChooser(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg");
        fileChooser.setFileFilter(filter);

        setBackground(Color.DARK_GRAY);
        var saveButton = new PaintIconButton(new ImageIcon("resources/icons/save.png"));
        var loadButton = new PaintIconButton(new ImageIcon("resources/icons/load.png"));
        saveButton.addActionListener(e -> {
            try {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                    save(fileChooser.getSelectedFile().getName());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        loadButton.addActionListener(e -> {
            try {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                    load(fileChooser.getSelectedFile().getPath());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        add(saveButton);
        add(loadButton);
    }

    public void load(String path) throws IOException {
        var in = new FileInputStream(new File(path));
        var img = ImageIO.read(in);
        if (img != null)
            paintArea.loadImage(img);
        in.close();
    }

    public void save(String path) throws IOException {
        if (!path.matches(".*\\.jpg"))
            path += ".jpg";
        var out = new FileOutputStream(new File(path));
        ImageIO.write(paintArea.getImage(), "jpeg", out);
        out.close();
    }
}
