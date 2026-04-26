import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TextureQuiltUI extends JFrame {

    private BufferedImage inputImage;
    private BufferedImage quiltedImage;

    private final JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
    private final TextureQuiltLogic quilter = new TextureQuiltLogic();

    private final JSlider patchSizeSlider = new JSlider(20, 100, 50);

    public TextureQuiltUI() {
        super("Texture Quilting Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        add(imageLabel, BorderLayout.CENTER);

        JPanel controls = new JPanel();
        JButton loadButton = new JButton("Load");
        JButton quiltButton = new JButton("Quilt");
        JButton saveButton = new JButton("Save");

        controls.add(new JLabel("Patch Size:"));
        controls.add(patchSizeSlider);
        controls.add(loadButton);
        controls.add(quiltButton);
        controls.add(saveButton);
        add(controls, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> onLoad());
        quiltButton.addActionListener(e -> onQuilt());
        saveButton.addActionListener(e -> onSave());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onLoad() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            try {
                inputImage = ImageIO.read(f);
                if (inputImage != null) {
                    imageLabel.setIcon(new ImageIcon(inputImage));
                    quilter.loadTexture(f.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(this, "Unsupported image.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to load image.");
            }
        }
    }

    private void onQuilt() {
        if (inputImage == null) {
            JOptionPane.showMessageDialog(this, "Load an image first.");
            return;
        }
        quilter.setPatchSize(patchSizeSlider.getValue());
        quiltedImage = quilter.executeQuilting();
        imageLabel.setIcon(new ImageIcon(quiltedImage));
    }

    private void onSave() {
        if (quiltedImage == null) {
            JOptionPane.showMessageDialog(this, "No quilted image to save.");
            return;
        }
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("quilted.png"));
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                quilter.saveResult(quiltedImage, fc.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Image saved.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to save image.");
            }
        }
    }
}





