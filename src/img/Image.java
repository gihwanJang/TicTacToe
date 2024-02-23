package img;

import javax.swing.ImageIcon;

public enum Image {
    CAT(new ImageIcon(Image.class.getResource("/img/cat.png"))),
    DOG(new ImageIcon(Image.class.getResource("/img/dog.png")));

    private ImageIcon image;

    private Image(ImageIcon icon) {
        this.image = icon;
    }

    public ImageIcon getImage() {
        return image;
    }
}
