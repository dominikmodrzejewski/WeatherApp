package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class Controller {
    @FXML
    private Label ThreeDaysDateLabel;
    @FXML
    private Label ThreeDaysTemperatureLabel;
    @FXML
    private ImageView Icon3DaysImageView;
    @FXML
    private ImageView Icon2DaysImageView;
    @FXML
    private Label TwoDaysDateLabel;
    @FXML
    private Label TwoDaysTemperatureLabel;
    @FXML
    private ImageView IconTomorrowImageView;
    @FXML
    private Label TomorrowDateLabel;
    @FXML
    private Label TemperatureTomorrowLabel;
    @FXML
    private ImageView IconImageView;
    @FXML
    private Label SunsetLabel;
    @FXML
    private Label SunriseLabel;
    @FXML
    private Label DescriptionLabel;
    @FXML
    private Label LongitudeLabel;
    @FXML
    private Label LatitudeLabel;
    @FXML
    private Label WindSpeedLabel;
    @FXML
    private javafx.scene.control.Label HumidityLabel;
    @FXML
    private javafx.scene.control.Label TemperatureLabel;
    @FXML
    private javafx.scene.control.Label PreasureLabel;
    @FXML
    private javafx.scene.control.Label CityLabel;
    @FXML
    private javafx.scene.control.Label CountryLabel;
    @FXML
    private TextField TextFieldCity;


    public void handleButtonRun() throws IOException {
        RequestApi requestApi = null;
        try {
            requestApi = new RequestApi(TextFieldCity.getText());
        } catch (IOException e) {
            e.printStackTrace();
            TextFieldCity.setText("Such a city doesn't exist");
        }

        // Centrowanie Labeli

        CityLabel.setAlignment(Pos.CENTER);
        CountryLabel.setAlignment(Pos.CENTER);
        TemperatureLabel.setAlignment(Pos.CENTER);
        DescriptionLabel.setAlignment(Pos.CENTER);
        SunriseLabel.setAlignment(Pos.CENTER);
        SunsetLabel.setAlignment(Pos.CENTER);
        LongitudeLabel.setAlignment(Pos.CENTER);
        LatitudeLabel.setAlignment(Pos.CENTER);
        TemperatureTomorrowLabel.setAlignment(Pos.CENTER);
        TwoDaysTemperatureLabel.setAlignment(Pos.CENTER);
        ThreeDaysTemperatureLabel.setAlignment(Pos.CENTER);
        TomorrowDateLabel.setAlignment(Pos.CENTER);
        TwoDaysDateLabel.setAlignment(Pos.CENTER);
        ThreeDaysDateLabel.setAlignment(Pos.CENTER);

        // Ustawienie tekstu na labelach wywołując request API

        assert requestApi!=null;
        CityLabel.setText(requestApi.getCityName());
        PreasureLabel.setText(requestApi.getPressure());
        CountryLabel.setText(requestApi.getCountry());
        HumidityLabel.setText((requestApi.getHumidity()));
        TemperatureLabel.setText(requestApi.getTemp());
        TemperatureTomorrowLabel.setText(requestApi.getTempTomorrow());
        TwoDaysTemperatureLabel.setText(requestApi.getTemp2Days());
        ThreeDaysTemperatureLabel.setText(requestApi.getTemp3Days());
        TomorrowDateLabel.setText(requestApi.getDateTomorrow());
        TwoDaysDateLabel.setText(requestApi.getDate2Days());
        ThreeDaysDateLabel.setText(requestApi.getDate3Days());
        WindSpeedLabel.setText(requestApi.getSpeed());
        SunriseLabel.setText(requestApi.getSunrise());
        SunsetLabel.setText(requestApi.getSunset());
        DescriptionLabel.setText(requestApi.getDescription());
        LongitudeLabel.setText(requestApi.getLongitude());
        LatitudeLabel.setText(requestApi.getLatitude());

        // Pobieranie z API oraz ustawienie ikon pokazujaca graficznie pogode

        Image imageToday = new Image(String.valueOf(requestApi.getIcon())); // pobieranie URL ikony
        IconImageView.setImage(imageToday); // ustawienie ikony
        Image imageTomorrow = new Image(String.valueOf(requestApi.getIconTomorrow()));
        IconTomorrowImageView.setImage(imageTomorrow);
        Image image2Days = new Image(String.valueOf(requestApi.getIcon2Days()));
        Icon2DaysImageView.setImage(image2Days);
        Image image3Days = new Image(String.valueOf(requestApi.getIcon3Days()));
        Icon3DaysImageView.setImage(image3Days);
    }
}






