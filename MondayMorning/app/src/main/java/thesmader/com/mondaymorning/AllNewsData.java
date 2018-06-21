package thesmader.com.mondaymorning;

public class AllNewsData {
    private String title, byLine, dateLine;
    private String img_url = "http://mondaymorning.nitrkl.ac.in/uploads/post/big/";

    public AllNewsData(String title, String byLine, String dateLine, String img_url) {
        this.title = title;
        this.byLine = byLine;
        this.dateLine = dateLine;
        this.img_url += img_url;
    }

    public String getTitle() {
        return title;
    }

    public String getByLine() {
        return byLine;
    }

    public String getDateLine() {
        return dateLine;
    }

    public String getImg_url() {
        return img_url;
    }


}
