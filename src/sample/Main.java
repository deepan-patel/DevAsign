package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.Random;
import java.lang.Math;
import java.util.Scanner;


public class Main extends Application {
    Stage window;
    Random rand = new Random();


    //Question 4
    String pathName ="";
    String fileContent="";
    String pathNameNew="";
    int[] counts = new int[26];


    XYChart.Series series1 = new XYChart.Series();
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    BarChart<String,Number> histogram = new BarChart<String,Number>(xAxis,yAxis);

    //Question 3
    int correlation = 10;

    //This variable hold the new cordinates when ever a cordinate changess
    int xPosChange;
    int yPosChange;

    Circle circle = new Circle();
    Circle point1 = new Circle();
    Circle point2 = new Circle();
    Circle point3 = new Circle();


    //Lines and their angle measurements
    Line lineA = new Line();
    Line lineB = new Line();
    Line lineC = new Line();

    Line aLine = new Line();
    Line bLine = new Line();
    Line cLine = new Line();

    Text aAngle = new Text();
    Text bAngle = new Text();
    Text cAngle = new Text();


    int windowHeight = 500;
    int windowWidth = 500;
    //Circle origin cordinates
    int originX = windowWidth/2;
    int originY = windowHeight/2;
    double angle = Math.toRadians(rand.nextInt(361)*1.0);
    int radius = 200;
    //Question 3 (end)


    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        //question 1 (starts)
        //this will be our main layout for the cards
        HBox hbox = new HBox(10);

        //now we will create a random int using Random
        Random rand = new Random();
        int num = 1 + rand.nextInt(51);

        //CARD 1
        FileInputStream fileInOne = new FileInputStream(String.format("/Users/deepanpatel/IdeaProjects/Assignment/src/sample/Cards/%d.png",num));
        Image image = new Image(fileInOne,300.0,300.0,true,false);
        ImageView card1 = new ImageView(image);

        //CARD 2
        num = 1 + rand.nextInt(51);
        FileInputStream fileInTwo = new FileInputStream(String.format("/Users/deepanpatel/IdeaProjects/Assignment/src/sample/Cards/%d.png",num));
        Image image2 = new Image(fileInTwo,300.0,300.0,true,false);
        ImageView card2 = new ImageView(image2);

        //CARD 3
        num = 1 + rand.nextInt(51);
        FileInputStream fileInThree = new FileInputStream(String.format("/Users/deepanpatel/IdeaProjects/Assignment/src/sample/Cards/%d.png",num));
        Image image3 = new Image(fileInThree,300.0,300.0,true,false);
        ImageView card3 = new ImageView(image3);

        //CARD 4
        num = 1 + rand.nextInt(51);
        FileInputStream fileInFour = new FileInputStream(String.format("/Users/deepanpatel/IdeaProjects/Assignment/src/sample/Cards/%d.png",num));
        Image image4 = new Image(fileInFour,300.0,300.0,true,false);
        ImageView card4 = new ImageView(image4);



        //Displaying all the cards
        hbox.getChildren().addAll(card1,card2,card3,card4);
        Scene playingCards = new Scene(hbox,1000,300);

        //Question 1 ends here

        //Question 2
        //Investment calculator
        primaryStage.setTitle("Question 2");

        Label invest = new Label("Investment Account");
        Label years = new Label("Years");
        Label interest = new Label("Annual Interest Rate");
        Label future = new Label("Future Value");

        GridPane grid1 = new GridPane();
        grid1.setHgap(10);
        grid1.setVgap(10);

        GridPane.setConstraints(invest, 0, 0);
        GridPane.setConstraints(years, 0, 1);
        GridPane.setConstraints(interest, 0, 2);
        GridPane.setConstraints(future, 0, 3);

        TextField investText = new TextField();
        investText.setPromptText("$");
        investText.setAlignment(Pos.CENTER_RIGHT);

        TextField yearText = new TextField();
        yearText.setAlignment(Pos.CENTER_RIGHT);

        TextField interestText = new TextField();
        interestText.setPromptText("%");
        interestText.setAlignment(Pos.CENTER_RIGHT);

        TextField futureText = new TextField();
        futureText.setStyle("-fx-background-color: transparent;");
        futureText.setStyle("-fx-border-color: lightblue ; -fx-border-width: 1px ;");
        futureText.setAlignment(Pos.CENTER_RIGHT);

        GridPane.setConstraints(investText, 1, 0);
        GridPane.setConstraints(yearText, 1, 1);
        GridPane.setConstraints(interestText, 1, 2);
        GridPane.setConstraints(futureText, 1, 3);

        Button button = new Button("Calculate");
        //button.setAlignment(Pos.BOTTOM_RIGHT);
        GridPane.setConstraints(button, 1, 4);
        GridPane.setHalignment(button, HPos.RIGHT);

        button.setOnAction(e ->{
            if( (isDouble(investText)) && (isDouble(yearText)) && isDouble(interestText)){
                double futureVal = Double.parseDouble(investText.getText()) * Math.pow((1.0 +
                        (Double.parseDouble(interestText.getText())/100)/12), Integer.parseInt(yearText.getText())*12);
                futureText.setText(String.format("%.2f", futureVal));


            }

        });

        grid1.getChildren().addAll(invest, years, interest, future, investText, yearText, interestText, futureText, button);
        Scene Investment = new Scene(grid1, 310, 190);


        //Question 3(start)
        Pane pane = new Pane();

        circle.setCenterX(originX);
        circle.setCenterY(originY);
        circle.setRadius(radius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1.5);

        //random generates new angle for so the next circle is placed somewhere different
        angle = Math.toRadians(rand.nextInt(361)*1.0);


        //using our knowlege of trig we can know where the the outer of the circle is using the formulas below
        int randX = (int) (radius * Math.sin(angle)+originX);
        int randY = (int) (radius * Math.cos(angle)+originY);


        point1.setCenterX(randX);
        point1.setCenterY(randY);
        point1.setRadius(5.0);
        point1.setFill(Color.RED);
        point1.setOnMouseDragged(e->{
            move(point1,e.getX(),e.getY());

            //update lineA and lineB
            lineA.setStartX(xPosChange);
            lineA.setStartY(yPosChange);
            lineB.setStartX(xPosChange);
            lineB.setStartY(yPosChange);

            //updates the angles after the lines have changed
            updateAngles();
        });

        angle = Math.toRadians(rand.nextInt(361)*1.0);

        randX = (int) (radius * Math.sin(angle)+originX);
        randY = (int) (radius * Math.cos(angle)+originY);

        point2.setCenterX(randX);
        point2.setCenterY(randY);
        point2.setRadius(5.0);
        point2.setFill(Color.RED);
        point2.setOnMouseDragged(e->{
            move(point2,e.getX(),e.getY());

            //update lineA and lineC
            lineA.setEndX(xPosChange);
            lineA.setEndY(yPosChange);
            lineC.setStartX(xPosChange);
            lineC.setStartY(yPosChange);

            //updates the angles after the lines have changed
            updateAngles();
        });
        angle = Math.toRadians(rand.nextInt(361)*1.0);


        randX = (int) (radius * Math.sin(angle)+originX);
        randY = (int) (radius * Math.cos(angle)+originY);

        point3.setCenterX(randX);
        point3.setCenterY(randY);
        point3.setRadius(5.0);
        point3.setFill(Color.RED);
        point3.setOnMouseDragged(e->{
            move(point3,e.getX(),e.getY());

            //update lineB and lineC
            lineB.setEndX(xPosChange);
            lineB.setEndY(yPosChange);

            lineC.setEndX(xPosChange);
            lineC.setEndY(yPosChange);


            //updates the angles after the lines have changed
            updateAngles();

        });

        //All final lines are initiated here for the initial state then are updates through the update function
        lineA = new Line(point1.getCenterX(),point1.getCenterY(),point2.getCenterX(),point2.getCenterY());
        lineA.setFill(Color.BLACK);

        lineB = new Line(point1.getCenterX(),point1.getCenterY(),point3.getCenterX(),point3.getCenterY());
        lineB.setFill(Color.BLACK);

        lineC = new Line(point2.getCenterX(),point2.getCenterY(),point3.getCenterX(),point3.getCenterY());
        lineC.setFill(Color.BLACK);

        //now we can calculate the angles and display them
        updateAngles();


        pane.getChildren().addAll(cAngle,circle,lineA,lineB,lineC,aAngle,bAngle,point1,point2,point3);
        Scene question3 = new Scene(pane,500,500);
        //question 3 (ends)

        //Question 4 starts
        //This is how we read a file
        //runGraph(pathName);

        //creates all the nodes needed at the bottom of the graph
        Label filename = new Label("Filename: ");
        TextField fileNameInput = new TextField();
        fileNameInput.setText("/Users/deepanpatel/IdeaProjects/Assignment/src/sample/");
        fileNameInput.setMinWidth(700);


        Button view = new Button("view");
        view.setOnAction(e->{
            pathName = fileNameInput.getText();
            runGraph(pathName);

        });

        //graph code
        histogram.setTitle("Letter Occurances in a Text File");
        xAxis.setLabel("Letters");
        yAxis.setLabel("Number of Occurances");
        series1.setName(pathName);


        //Hold the label the textfeild and the button
        HBox bottom = new HBox();
        bottom.getChildren().addAll(filename,fileNameInput,view);

        VBox Finalgraph = new VBox();
        Finalgraph.getChildren().addAll(histogram,bottom);


        Scene histograph = new Scene(Finalgraph,800,700);
        histogram.getData().addAll(series1);

        window.setScene(Investment);
        //window.setScene(histograph);
        //window.setScene(playingCards);
        //window.setScene(question3);//uncomment this to see question3
        window.show();
    }

    public boolean isDouble(TextField input){
        try{
            //checking if the value is a double
            double val = Double.parseDouble(input.getText());
            return true;
        }catch(NumberFormatException e){
            System.out.println("enter a number");

            return false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    //THIS IS ALL FOR QUESTION 3

    //this function returns the angle based off where the mouse is.
    public double getAngleForPoint(double xPos, double yPos){

        //Caluculates angle and connect the lines to each other
        double a = radius;
        System.out.println("a: "+a);
        aLine.setFill(Color.RED);
        aLine.setStartX(originX);
        aLine.setStartY(originY);
        aLine.setEndX(originX);
        aLine.setEndY(originY+radius);

        double b = len(originX,originY,xPos,yPos);
        System.out.println("b: "+b);

        bLine.setFill(Color.RED);
        bLine.setStartX(originX);
        bLine.setStartY(originY);
        bLine.setEndX(xPos);
        bLine.setEndY(yPos);


        double c = len(originX,originY+radius,xPos,yPos);
        cLine.setFill(Color.RED);
        cLine.setStartX(originX);
        cLine.setStartY(originY+radius);
        cLine.setEndX(xPos);
        cLine.setEndY(yPos);

        System.out.println("c: "+c);

        double top = Math.pow(a,2.0) + Math.pow(b,2.0) - Math.pow(c,2.0);

        double angle = Math.acos(top/(2*a*b));
        //uncomment to help debugg
//        System.out.println("xpos: "+xPos);
//        System.out.println("ypos: "+yPos);
//        System.out.println("angle: "+Math.toDegrees(angle));

        //return normal angle if the mouse is in Q I or IV or return special case if not
        if((xPos<=(originX))){
            //The point is on the left side of the angle
            return 180 - Math.toDegrees(angle) + 180;
        }

        return Math.toDegrees(angle);
    }

    public void updateAngles(){
        DecimalFormat df = new DecimalFormat("##");

        //angle for a
        double angle = (Math.pow(len(lineA),2.0) - Math.pow(len(lineB),2.0) - Math.pow(len(lineC),2.0) )/ (-2*len(lineB)*len(lineC));
        aAngle.setText(df.format(Math.toDegrees(Math.acos(angle)))+"");
        aAngle.setX(point3.getCenterX()+correlation);
        aAngle.setY(point3.getCenterY());


        //angle for b
        angle = (Math.pow(len(lineB),2.0) - Math.pow(len(lineA),2.0) - Math.pow(len(lineC),2.0) )/ (-2*len(lineA)*len(lineC));
        bAngle.setText(df.format(Math.toDegrees(Math.acos(angle)))+"");
        bAngle.setX(point2.getCenterX()+correlation);
        bAngle.setY(point2.getCenterY());

        //angle for c
        angle = (Math.pow(len(lineC),2.0) - Math.pow(len(lineB),2.0) - Math.pow(len(lineA),2.0) )/ (-2*len(lineA)*len(lineB));
        cAngle.setText(df.format(Math.toDegrees(Math.acos(angle)))+"");
        cAngle.setX(point1.getCenterX()+correlation);
        cAngle.setY(point1.getCenterY());


    }

    public double len(double x1,double y1,double x2,double y2){
        double x= Math.pow((x1-x2),2) + Math.pow((y1-y2),2);
        return Math.sqrt(x);

    }

    public double len(Line line){
        double x= Math.pow((line.getStartX()-line.getEndX()),2) + Math.pow((line.getStartY()-line.getEndY()),2);
        return Math.sqrt(x);

    }

    public void move(Circle point,double xPos,double yPos){
        //Move the points along the circle
        double newAngle = getAngleForPoint(xPos,yPos);
        int x = (int) (radius * Math.sin(Math.toRadians(newAngle))+originX);
        int y = (int) (radius * Math.cos(Math.toRadians(newAngle))+originY);
        System.out.println("x:"+x+" y:"+y);
        point.setCenterX(x);
        point.setCenterY(y);

        //This changes are stored
        xPosChange = x;
        yPosChange = y;

    }

    //Question 3 ends here

    //Question 4 starts here
    public void runGraph(String pathNameIn){

        File readFile = new File(pathNameIn);
        try{
            Scanner scan = new Scanner(readFile);
            counts = counting(scan);


            //Add the counts of each letter to the graph using asci
            int start = 97;

            for(int i=0;i<counts.length;i++){
                String letter = Character.toString((char)start);
                series1.getData().add(new XYChart.Data<String,Integer>(letter,counts[i]));
                start++;
            }

        }
        catch(Exception e){
            System.out.println("File Not Found!");

        }
    }
    public int[] counting(Scanner scan){
        //we have to reset count incase we run it muliple times
        int[] newCounts = new int[26];
        fileContent = "";

        while(scan.hasNext()){
            fileContent = fileContent.concat(scan.nextLine());
        }


        //this stored all the text in seperate letters and with no spaces
        fileContent = fileContent.replace(" ","");
        fileContent = fileContent.toLowerCase();
        String[] words = fileContent.split("");


        for(int i=0;i < words.length;i++){
            Character letter = words[i].charAt(0);
            int index = ((int)letter)%97;
            if((index >=0)&&(index<=26)){
                newCounts[index] = newCounts[index]+1;
            }

        }

        return newCounts;

    }


}

