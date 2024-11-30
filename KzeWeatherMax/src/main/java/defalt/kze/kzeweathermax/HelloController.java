package defalt.kze.kzeweathermax;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;



public class HelloController {
    @FXML
    private Label taraText;
    @FXML
    private Label tempText;
    @FXML
    private Label descText;
    @FXML
    private Button myButton;
    @FXML
    private TextField searchBar;
    @FXML
    private ImageView imageInfo;

    @FXML
    private TextArea input;
    WeatherService serviciu = new WeatherService();
    public void Afiseaza(ActionEvent event) throws Exception {
        String text=searchBar.getText();
        String deParsat=serviciu.getLocatie(text);
        parseWeather(deParsat);
    }
    public void parseWeather(String weather) throws Exception {
        String[] bucati = weather.split(" ");
        String tara = bucati[1];
        double temp = Double.parseDouble(bucati[3]);
        String description = bucati[6] + " " + bucati[7];
        //ADAUGA 3 TEXTFIELDS si pune valorile tara temp si desc si gata!!!!

        taraText.setText(tara);
        tempText.setText(String.valueOf(temp));
        descText.setText(description);

        if(description.contains("clouds")){
            imageInfo.setImage(new Image("D:\\IntelijProiecte\\KzeWeatherMax\\src\\main\\resources\\defalt\\kze\\kzeweathermax\\cloudpng.png"));}
        if(description.contains("clear")) {
            imageInfo.setImage(new Image("D:\\IntelijProiecte\\KzeWeatherMax\\src\\main\\resources\\defalt\\kze\\kzeweathermax\\icons.png"));
        }

    }

}