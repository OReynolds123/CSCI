package cs1302.gallery;

import javafx.event.ActionEvent;
import javafx.application.Application;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import java.util.stream.Collectors;
import javafx.scene.control.Dialog;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import java.util.List;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.geometry.Orientation;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.control.ProgressBar;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.stage.Stage;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextField;
import java.util.Timer;
import java.util.TimerTask;
import java.net.URL;
import javafx.scene.layout.VBox;

/**
 * Represents an iTunes GalleryApp.
 */
public class GalleryApp extends Application {

    /** The default stage for the app. */
    Stage stage;

    /** The menu bar at the top of the form. */
    MenuBar menuBar;

    /** The serarch hbox. */
    HBox searchHBox;

    /** The search query text box. */
    TextField txtField;

    /** The pause button. */
    Button pauseBtn;

    /** The vbox for the hboxes. */
    VBox imgViewVBox;

    /** The hboxes for the images. */
    HBox row1HBox;
    HBox row2HBox;
    HBox row3HBox;
    HBox row4HBox;

    /** The image viewers in an array. */
    ImageView [] ivArr;

    /** The image list with urls. */
    List<String> imgList;
    Boolean sameList;

    /** The progress bar hbox. */
    HBox progressHBox;

    /** The progress bar. */
    ProgressBar pgsBar;

    /** The next image to use for random replacement in the array. */
    int nextImgNum;

    /** The timer that changes the image every 2 seconds. */
    Timer t;
    TimerTask run2Sec;
    boolean timerState;

    /** The about stage. */
    Stage aboutStage;

    /** The error stage. */
    Stage errorStage;

    /**
     * The start method for the {@code GalleryApp}.
     * {@inheritdoc}
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;

        // Menu Bar
        setMenuBar();

        // Search HBOX
        setSearchHBox();

        // Picture Box
        setImgViewBox();

        // Timer
        setTimer();

        // ProgressBar HBox
        setProgressHBox();

        // VBox
        VBox vbox = new VBox();
        vbox.getChildren().addAll(menuBar, searchHBox, imgViewVBox, progressHBox);

        Scene scene = new Scene(vbox);
        stage.setResizable(false);
        stage.setMinWidth(520);
        stage.setTitle("Gallery App");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    } // start

    /**
     * The stop method for the java form.
     */
    @Override
    public void stop() {
        t.cancel(); // Stop the timer so the form can close
    } // stop

    /**
     * A method to show the error dialog box that pops up if less than 21 images are gathered from
     * a search.
     */
    private void showErrAlert() {
        errorStage = new Stage(); // Set the stage

        // Error Icon
        String errorImageURL = "file:resources/ErrorImg.jpg";
        ImageView errorImgView = new ImageView(new Image(errorImageURL));
        errorImgView.setFitWidth(50);
        errorImgView.setPreserveRatio(true);

        // The label
        String errorTxt = "Error: Less than 21 images were gathered from the search query.";
        Label errorLbl = new Label(errorTxt);

        // Button
        Button errorButton = new Button("Close");
        errorButton.setMaxWidth(80);
        errorButton.setOnAction(this::closeErrAlert);

        // HBox
        HBox errorHBox = new HBox();
        errorHBox.setSpacing(10);
        errorHBox.setAlignment(Pos.CENTER);
        errorHBox.getChildren().addAll(errorImgView, errorLbl);

        // VBox
        VBox errorVBox = new VBox();
        errorVBox.setSpacing(10);
        errorVBox.setAlignment(Pos.CENTER);
        errorVBox.setPadding(new Insets(10, 10, 10, 10));
        errorVBox.getChildren().addAll(errorHBox, errorButton);

        Scene errorScene = new Scene(errorVBox);
        errorStage.setResizable(false);
        errorStage.setMinHeight(200);
        errorStage.setTitle("Error");
        errorStage.setScene(errorScene);
        errorStage.sizeToScene();
        errorStage.initModality(Modality.APPLICATION_MODAL);
        errorStage.initOwner(stage);
        errorStage.showAndWait();
    } // setErrAlert

    /**
     * A method to close the error alert stage.
     * @param e An {@code ActionEvent} object.
     */
    private void closeErrAlert(ActionEvent e) {
        errorStage.close();
    } // closeErrAlert

    /**
     * A method to set and open the About stage.
     * @param e An {@code ActionEvent} object.
     */
    private void aboutStage(ActionEvent e) {
        aboutStage = new Stage(); // Set the stage

        // Image of me
        String aboutImageURL = "file:resources/OwenPicture.jpg";
        ImageView aboutImgView = new ImageView(new Image(aboutImageURL));
        aboutImgView.setFitWidth(300);
        aboutImgView.setPreserveRatio(true);

        // The labels
        Label aboutNameLbl = new Label("Name: Owen Reynolds");
        Label aboutEmailLbl = new Label("Email: ocr79994@uga.edu");
        Label aboutVersionLbl = new Label("Version: 1.01");

        // Button
        Button aboutButton = new Button("Close");
        aboutButton.setMaxWidth(80);
        aboutButton.setOnAction(this::closeAboutStage);

        // VBox
        VBox aboutVBox = new VBox();
        aboutVBox.setSpacing(10);
        aboutVBox.setAlignment(Pos.CENTER);
        aboutVBox.setPadding(new Insets(10, 10, 10, 10));
        aboutVBox.getChildren().addAll(aboutImgView, aboutNameLbl, aboutEmailLbl, aboutVersionLbl,
                                       aboutButton);

        Scene aboutScene = new Scene(aboutVBox);
        aboutStage.setResizable(false);
        aboutStage.setMinWidth(300);
        aboutStage.setTitle("About Owen");
        aboutStage.setScene(aboutScene);
        aboutStage.sizeToScene();
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.initOwner(stage);
        aboutStage.showAndWait();
    } // aboutStage

    /**
     * A method to close the about stage.
     * @param e An {@code ActionEvent} object.
     */
    private void closeAboutStage(ActionEvent e) {
        aboutStage.close();
    } // closeAboutStage

    /**
     * A method to set all of the properties for the {@code menuBar} at the top of the form.
     */
    private void setMenuBar() {
        menuBar = new MenuBar();

        // File Menu
        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        fileMenu.getItems().add(exitMenuItem);
        exitMenuItem.setOnAction(this::exitForm);

        // Help Menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
        helpMenu.getItems().add(aboutMenuItem);
        aboutMenuItem.setOnAction(this::aboutStage);

        // MenuBar Properties
        menuBar.setUseSystemMenuBar(true);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
    } // setMenuBar

    /**
     * A method to set all of the properties for the {@code searchHBox}.
     */
    private void setSearchHBox() {
        searchHBox = new HBox(10);

        // Pause/Unpause Button
        pauseBtn = new Button("Pause");
        pauseBtn.setOnAction(this::pauseImage);

        // Separator
        Separator separator = new Separator(Orientation.VERTICAL);

        // Label
        Label searchLbl = new Label("Search Query:");

        // Text Field
        txtField = new TextField("country");
        txtField.setPrefWidth(140);

        // Update Button
        Button updateBtn = new Button("Update Images");
        updateBtn.setOnAction(this::loadImage);

        // HBox Properties
        searchHBox.setAlignment(Pos.CENTER_LEFT);
        searchHBox.setPadding(new Insets(10));
        searchHBox.getChildren().addAll(pauseBtn, separator, searchLbl, txtField, updateBtn);
        searchHBox.setHgrow(txtField, Priority.ALWAYS);
    } // setSearchHBox

    /**
     * A method to set the {@code imgViewVBox} for the 20 {@code ImageView} objects.
     */
    private void setImgViewBox() {
        imgViewVBox = new VBox();

        // HBox Rows of ImageViews
        row1HBox = new HBox();
        row2HBox = new HBox();
        row3HBox = new HBox();
        row4HBox = new HBox();

        // Random Image Replacement Variable
        nextImgNum = 20;

        // Default Images
        imgList = getImgString("Country");
        ivArr = setImgViewers(imgList);

        // HBox Properties
        row1HBox.getChildren().addAll(ivArr[0], ivArr[1], ivArr[2], ivArr[3], ivArr[4]);
        row2HBox.getChildren().addAll(ivArr[5], ivArr[6], ivArr[7], ivArr[8], ivArr[9]);
        row3HBox.getChildren().addAll(ivArr[10], ivArr[11], ivArr[12], ivArr[13], ivArr[14]);
        row4HBox.getChildren().addAll(ivArr[15], ivArr[16], ivArr[17], ivArr[18], ivArr[19]);
        imgViewVBox.getChildren().addAll(row1HBox, row2HBox, row3HBox, row4HBox);
    } // setImgViewBox

    /**
     * A method to set the timer.
     */
    private void setTimer() {
        // Timer
        t = new Timer(true);

        // Timer Task
        run2Sec = new TimerTask() {
                @Override
                public void run() {
                    randomImgs(imgList); // Random Images
                } // run
            };
        t.schedule(run2Sec, (long) 2000, (long) 2000); // Every 2 seconds
        timerState = true;
    } // setTimer

    /**
     * A method to set the progress hbox.
     */
    private void setProgressHBox() {
        progressHBox = new HBox(10);

        // Progress Bar
        pgsBar = new ProgressBar(0);

        // Label
        Label pgsLabel = new Label("Images provided courtesy of iTunes");
        pgsLabel.setMaxWidth(Double.MAX_VALUE);

        // ProgressHBox Properties
        progressHBox.setAlignment(Pos.CENTER_LEFT);
        progressHBox.getChildren().addAll(pgsBar, pgsLabel);
    } // setProgressHBox

    /**
     * A method to exit from the form.
     * @param e An {@code ActionEvent} object.
     */
    private void exitForm(ActionEvent e) {
        t.cancel();
        System.exit(0);
    } // exitForm

    /**
     * A method to randomly replace an image with the next one in the list.
     * @param lst The string list full of image urls.
     */
    private void randomImgs(List<String> lst) {
        int randomImg = (int) (Math.random() * 20); // Get a random number

        // Replaces a random image view object with the next distinct image in the list
        Platform.runLater(() -> ivArr[randomImg].setImage(new Image(lst.get(nextImgNum))));
        nextImgNum++;
        if (nextImgNum == imgList.size()) {
            nextImgNum = 0; // Loop back through the images
        } // nextImgNum
    } // randomImgs

    /**
     * A method to set the 20 image viewers with the default images.
     * @param lst The string list full of image urls.
     * @return ImageView [] An image view array with the 20 {@code ImageView} objects.
     */
    private ImageView [] setImgViewers(List<String> lst) {
        ImageView [] tempImgViewArr = new ImageView[20]; // An array with 20 ImageView Objects
        for (int i = 0; i < 20; i++) {
            ImageView tempImgView = imageViewer(lst.get(i)); // Set the default parameters
            tempImgViewArr[i] = tempImgView;
            // Set the progress bar
            double prog = 0.5 + ((double) ((i + 1) / 20.00) * 0.5);
            changeProgress(prog);
        } // for
        return tempImgViewArr;
    } // setImgViewers

    /**
     * A method to set the default parameters for an {@code ImageView}. The height and width are
     * set to 100.
     * @param str The url for the image to set the {@code ImageView} to.
     * @return ImageView The {@code ImageView} object with the new parameters.
     */
    private ImageView imageViewer(String str) {
        ImageView iv = new ImageView(new Image(str));
        iv.setFitHeight(100);
        iv.setFitWidth(100);
        return iv;
    } // imageViewer

    /**
     * A method to update the image viewers with the new images.
     * @param lst The string list full of image urls.
     */
    private void updateImgViewers(List<String> lst) {
        for (int i = 0; i < 20; i++) {
            try {
                ivArr[i].setImage(new Image(lst.get(i))); // Set the new image
                // Set the progress bar
                double prog = 0.5 + ((double) ((i + 1) / 20.00) * 0.5);
                changeProgress(prog);
            } catch (Exception e) {
                System.out.println("Error: List is empty at index " + i);
            } // try
        } // for
    } // updateImgViewers

    /**
     * A method to load an image upon click of the 'Update' button.
     * @param e An {@code ActionEvent} object.
     */
    private void loadImage(ActionEvent e) {
        Thread thread = new Thread(new Runnable() {
                public void run() {
                    imgList = getImgString(txtField.getText()); // Get the new list based on input
                    if (sameList == false) {
                        updateImgViewers(imgList); // Update the images
                    } // if
                } // run
            }); // thread
        nextImgNum = 20;
        thread.setDaemon(true);
        thread.start();
        stage.sizeToScene();
    } // loadImage

    /**
     * A method to pause the random updating images upon click of the 'Pause' button.
     * @param e An {@code ActionEvent} object.
     */
    private void pauseImage(ActionEvent e) {
        if (timerState == true) {
            timerState = false;
            pauseBtn.setText("Play");
            t.cancel();
        } else {
            setTimer();
            pauseBtn.setText("Pause");
        } // if
        stage.sizeToScene();
    } // pauseImage

    /**
     * A method to change the progress on the progress bar.
     * @param prog The progress to set the progress bar to.
     */
    private void changeProgress(double prog) {
        Platform.runLater(() -> pgsBar.setProgress(prog));
    } // changeProgress

    /**
     * A method to parse the iTunes library for urls from a given input.
     * @param genre The genre to search the iTunes library for.
     * @return List A list with image urls.
     */
    private List<String> getImgString(String genre) {
        // Some needed variables
        sameList = false;
        int i = 1;
        List<String> urlList = new ArrayList<>();
        genre = genre.replaceAll(" ", "+");
        String url = "https://itunes.apple.com/search?term=" + genre + "&limit=100";

        // Parsing the query
        try {
            Scanner xmlScanner = new Scanner(new URL(url).openStream()).useDelimiter(",");
            while (xmlScanner.hasNext()) { // Read the file
                String line = xmlScanner.next();
                if (line.startsWith(" \"artworkUrl100\"")) { // Check for 'artworkUrl100'
                    String artworkUrl = line.substring(18); // Parse the url
                    artworkUrl = artworkUrl.substring(0, artworkUrl.length() - 1);
                    urlList.add(artworkUrl); // Add it to the list
                    // Change the progress on the progress bar
                    double prog = ((double) (i / 100.00)) * 0.5;
                    changeProgress(prog);
                    i++;
                } // if
            } // while
            xmlScanner.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } // try
        urlList = urlList.stream().distinct().collect(Collectors.toList()); // Filter duplicates
        if (urlList.size() < 21) { // Error if less than 21 urls
            System.out.println("Error: Less than 21 images were gathered");
            Platform.runLater(() -> showErrAlert());
            sameList = true;
            changeProgress(100);
            return imgList; // Return the original list
        } // if
        return urlList;
    } // getImgString

} // GalleryApp
