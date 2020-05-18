package gabriel.oprinoiu.excol2;

class WeatherForecastInformation {
    private String temperature;
    private String wind_speed;
    private String general_state;
    private String pressure;
    private String humidity;

    WeatherForecastInformation(String t, String w, String g, String p, String h) {
        temperature = t;
        wind_speed = w;
        general_state = g;
        pressure = p;
        humidity = h;
    }

    public String getTemperature() {return  temperature;}
    public void setTemperature(String s) {temperature = s;}

    public String getWind_speed() {return wind_speed;}
    public void setWind_speed(String s) {wind_speed = s;}

    public String getGeneral_state(){return general_state;}
    public  void  setGeneral_state(String s) {general_state = s;}

    public String getPressure(){return pressure;}
    public void setPressure(String s){pressure = s;}

    public String getHumidity(){return humidity;}
    public void setHumidity(String s){humidity = s;}

}
