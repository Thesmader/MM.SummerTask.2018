package thesmader.com.mondaymorning;

public class AllNewsData {
    private String title;
    private String byLine;
    private String dateLine;
    private String categories;
    private String img_url = "http://mondaymorning.nitrkl.ac.in/uploads/post/big/";

    public AllNewsData(String title, String byLine, String dateLine, String img_url, String categories) {
        this.title = title;
        this.byLine = byLine;
        //System.arraycopy(byLine, 0, this.byLine, 0, byLine.length);
        this.dateLine = dateLine;
        this.img_url += img_url;
        //System.arraycopy(categories, 0, this.categories, 0, categories.length);
        this.categories = categories;
        //this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public String getByLine() {
        return byLine ;
    }

    public String getDateLine() {
        return dateLine;
    }

    /*public String getTag() {
        return tag;
    }*/
    public String getCategories() { return categories; }

    public String getImg_url() {
        return img_url;
    }


}
