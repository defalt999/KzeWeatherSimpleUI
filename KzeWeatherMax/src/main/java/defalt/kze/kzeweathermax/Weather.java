package defalt.kze.kzeweathermax;

public class Weather {
    private String numeTara;
    private int temp;
    private String description;

    Weather(String numeTara, int temp, String description) {
        this.numeTara = numeTara;
        this.temp = temp;
        this.description = description;
    }
    Weather(){
        numeTara = "";
        temp = 0;
        description = "";
    }
    public String getTara() {
        return numeTara;
    }

    public double getTemp() {
        return temp;
    }

    public String getDesc() {
        return description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "numeTara='" + numeTara + '\'' +
                ", temp=" + temp +
                ", description='" + description + '\'' +
                '}';
    }


}
