package thesmader.com.mondaymorning;

public class ArticleModel {
    public static final int TEXT = 0;
    public static final int IMAGE = 1;

    public int type;
    public String imageURL = "";
    public String text;

    public ArticleModel(int type, String imageURL, String text) {
        this.type = type;
        this.imageURL = imageURL;
        this.text = text;
    }

    public ArticleModel(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getText() {
        return text;
    }
}
