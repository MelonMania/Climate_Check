package ClimateCheck;

public class Region {
    private String sx;
    private String sy;
    public Region(String x, String y){
        sx = x;
        sy = y;
    }
    public String getSx() {
        return sx;
    }
    public void setSx(String sx) {
        this.sx = sx;
    }
    public String getSy() {
        return sy;
    }
    public void setSy(String sy) {
        this.sy = sy;
    }
}