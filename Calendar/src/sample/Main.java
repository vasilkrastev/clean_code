package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group group = new Group();
        Scene scene = new Scene(group, 1400, 750);
        int year = dialogInput("Input year", "Year");
        Text yearText = new Text(650, 30, String.format("%d", year));
        yearText.setStyle("-fx-font-weight: bold; -fx-font: 30 arial");
        group.getChildren().add(yearText);

        int width=70, height=120;

        for(int month = 1; month<=12; month++) {

            if(month<5){
                height=120;
            }

            else if(month>=5 && month <9){
                height=320;
            }

            else if(month>=9){
                height=520;
            }

            if(month==5 || month==9){
                width=70;
            }

            for (int i = 0, j = width+0; i < 7; i++, j += 40) {
                DayOfWeek dayOfWeek = DayOfWeek.MONDAY.plus(i);
                Text dayWeekText = new Text(j,height-20, dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
                dayWeekText.setStyle("-fx-font-weight: bold");
                if(i==5 || i==6){
                    dayWeekText.setFill(Color.RED);
                }
                group.getChildren().add(dayWeekText);
            }

            YearMonth yearMonth = YearMonth.of(year, month);
            int daysOfYearMonth = yearMonth.lengthOfMonth();
            Text monthText = new Text(width+100,height-40, String.format("%s",Month.JANUARY.plus(month - 1)));
            monthText.setStyle("-fx-font-weight: bold");
            group.getChildren().add(monthText);

            for (int i = 1; i <= daysOfYearMonth; i++) {
                LocalDate date = LocalDate.of(year, month, i);
                Text day;

                switch (date.getDayOfWeek()) {
                    case MONDAY:
                        day = new Text(width, height, String.format("%d", date.getDayOfMonth()));

                        group.getChildren().add(day);
                        break;
                    case TUESDAY:
                        day = new Text(width+40, height, String.format("%d", date.getDayOfMonth()));

                        group.getChildren().add(day);
                        break;
                    case WEDNESDAY:
                        day = new Text(width+80, height, String.format("%d", date.getDayOfMonth()));

                        group.getChildren().add(day);
                        break;
                    case THURSDAY:
                        day = new Text(width+120, height, String.format("%d", date.getDayOfMonth()));

                        group.getChildren().add(day);
                        break;
                    case FRIDAY:
                        day = new Text(width+160, height, String.format("%d", date.getDayOfMonth()));

                        group.getChildren().add(day);
                        break;
                    case SATURDAY:
                        day = new Text(width+200, height, String.format("%d", date.getDayOfMonth()));
                        day.setFill(Color.RED);
                        group.getChildren().add(day);
                        break;
                    case SUNDAY:
                        day = new Text(width+240, height, String.format("%d", date.getDayOfMonth()));
                        day.setFill(Color.RED);
                        group.getChildren().add(day);
                        height += 20;
                        break;
                }

            }
            width+=320;

        }

        primaryStage.setTitle("Calendar for " + year);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static int dialogInput(String infoMessage, String titleBar) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(titleBar);
        dialog.setHeaderText(null);
        dialog.setContentText(infoMessage);

        return Integer.parseInt(dialog.showAndWait().get());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
