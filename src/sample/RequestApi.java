package sample;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class RequestApi {
    private URL url;
    private JSONObject obj;
    private String CityName;

    // RequestAPI korzysta z API Forecast i musimy mu przekazać nazwę miasta oraz odczytać zawartość zwracanego API z podanego URL'a

    public RequestApi(String cityName) throws IOException {
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&APPID=35369546d7d07fd5a1d2e987269cac56&units=metric&lang=en");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    // używamy metody openStream do otworzenia strumienia, z którego url będzie czytał, aby odczytać tekst używamy Scannera

        Scanner scan = new Scanner(url.openStream());
        String apiString = ""; // Tworzymy stringa do przechowywania odczytanego tekstu
        while (scan.hasNext()) // hasNext sprawdza czy jest coś do odczytania.
            apiString += scan.nextLine();  // kontatenacja danych wejsciowych
        scan.close(); // Po skończeniu odczytywania zamykamy strumień

        this.obj = new JSONObject(apiString);
        System.out.println(apiString); // wyświetlenie API w formacie JSON
        CityName = cityName;
    }

    public String getCityName() {
        return CityName;
    }

    public String getPressure() {
        JSONArray listArray = obj.getJSONArray("list"); // odnosimy się do listy
        JSONObject listObject = listArray.getJSONObject(0); // pierwszy element listy
        JSONObject mainObject = listObject.getJSONObject("main"); // pobieramy obiekt main
        return mainObject.getInt("pressure") + " hPa"; // zwracamy pressure z main obiektu
    }

    public String getTemp() {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(0);
        JSONObject mainObject = listObject.getJSONObject("main");
        return mainObject.getDouble("temp") + " °C";
    }

    public String getTempTomorrow() {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(7);
        JSONObject mainObject = listObject.getJSONObject("main");
        return mainObject.getDouble("temp") + " °C";
    }

    public String getTemp2Days() {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(15);
        JSONObject mainObject = listObject.getJSONObject("main");
        return mainObject.getDouble("temp") + " °C";
    }

    public String getTemp3Days() {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(22);
        JSONObject mainObject = listObject.getJSONObject("main");
        return mainObject.getDouble("temp") + " °C";
    }


    public String getDateTomorrow() {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(7);
        long unixSecondsSunrise = listObject.getLong("dt"); // data jest w formacie unixowym, dlatego trzeba go przekonwertować
        Date date = new Date(unixSecondsSunrise * 1000L); // konwertowanie sekund do milisekund
        SimpleDateFormat jdf = new SimpleDateFormat("dd-MM-yyyy"); // ustawienie formatu daty
        return jdf.format(date);
    }

    public String getDate2Days() {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(15);
        long unixSecondsSunrise = listObject.getLong("dt");
        Date date = new Date(unixSecondsSunrise * 1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("dd-MM-yyyy");
        return jdf.format(date);
    }

    public String getDate3Days() {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(22);
        long unixSecondsSunrise = listObject.getLong("dt");
        Date date = new Date(unixSecondsSunrise * 1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("dd-MM-yyyy");
        return jdf.format(date);
    }

    public URL getIconTomorrow() throws MalformedURLException {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(7);
        JSONArray weatherArray = listObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        return new URL("http://openweathermap.org/img/w/" + weatherObject.getString("icon") + ".png");
    }

    public URL getIcon2Days() throws MalformedURLException {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(15);
        JSONArray weatherArray = listObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        return new URL("http://openweathermap.org/img/w/" + weatherObject.getString("icon") + ".png");
    }

    public URL getIcon3Days() throws MalformedURLException {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(22);
        JSONArray weatherArray = listObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        return new URL("http://openweathermap.org/img/w/" + weatherObject.getString("icon") + ".png");
    }

    public String getCountry() {
        return String.valueOf(obj.getJSONObject("city").get("country"));
    }

    public String getHumidity() {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(0);
        JSONObject mainObject = listObject.getJSONObject("main");
        return mainObject.getInt("humidity") + " %";
    }

    public String getSpeed() {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(0);
        JSONObject windObject = listObject.getJSONObject("wind");
        return windObject.getDouble("speed") + " m/s";
    }

    public String getSunrise() {
        long unixSecondsSunrise = (Integer) obj.getJSONObject("city").get("sunrise");
        Date date = new Date(unixSecondsSunrise * 1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("HH:mm:ss z");
        jdf.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
        return jdf.format(date);
    }

    public String getSunset() {
        long unixSecondsSunset = (Integer) obj.getJSONObject("city").get("sunset");
        Date date = new Date(unixSecondsSunset * 1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("HH:mm:ss z");
        jdf.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
        return jdf.format(date);
    }

    public String getDescription() {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(0);
        JSONArray weatherArray = listObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        return weatherObject.getString("description");
    }

    public URL getIcon() throws IOException {
        JSONArray listArray = obj.getJSONArray("list");
        JSONObject listObject = listArray.getJSONObject(0);
        JSONArray weatherArray = listObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        return new URL("http://openweathermap.org/img/w/" + weatherObject.getString("icon") + ".png");
    }

    public String getLongitude() {
        JSONObject cityObject = obj.getJSONObject("city");
        return String.valueOf(cityObject.getJSONObject("coord").get("lon"));
    }

    public String getLatitude() {
        JSONObject cityObject = obj.getJSONObject("city");
        return String.valueOf(cityObject.getJSONObject("coord").get("lat"));
    }
}

