package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.ExpressionEvaluator;

public class MainController {
    @FXML //estos son los que hacen referencia a un elemento de javafx. se conectan por fxid
    private HBox titleBar;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnToggleMaximize;

    @FXML
    private Button btnClose;

    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;
    private Image iconMin;
    private Image iconMax;
    private Image iconRestore;
    private Image iconClose;

    //atributos de la calculadora
    @FXML
    private Label expressionLabel;
    @FXML
    private Label resultLabel;

    private final StringBuilder expression =  new StringBuilder();
    private double lastAns;
    private boolean justEvaluated = false;
    private String lastExpression;

    //funciones de la calculadora
    private void setupCalculator(){
        updateDisplays();
    }
    private void updateDisplays() {
        if (justEvaluated) {
            expressionLabel.setText(lastExpression);
            resultLabel.setText(formatResult(lastAns));
        } else {
            expressionLabel.setText("");
            if (expression.isEmpty()) {
                resultLabel.setText("0");
            } else {
                resultLabel.setText(expression.toString());
            }
        }
    }

    private String formatResult(double value){
        String s =  Double.toString(value);
        if (s.contains(".")){
            s = s.replaceAll("0+$","").replaceAll("\\.$","");
        }
        return s;
    }

    @FXML
    private void onDigitClick(javafx.event.ActionEvent event){
        startNewExpressionIfJustEvaluated();

        Button btn = (Button) event.getSource();
        String txt = btn.getText();

        expression.append(txt);
        updateDisplays();
    }
    @FXML
    private void onOperatorClick(javafx.event.ActionEvent event) {
        startNewExpressionIfJustEvaluated();

        Button btn = (Button) event.getSource();
        String opSymbol = btn.getText();

        char opChar;
        switch (opSymbol) {
            case "×" -> opChar = '*';
            case "÷" -> opChar = '/';
            case "−" -> opChar = '-';
            default  -> opChar = opSymbol.charAt(0); // +
        }

        expression.append(opChar);
        updateDisplays();
    }


    @FXML
    private void onOpenParen(){
        startNewExpressionIfJustEvaluated();
        expression.append('(');
        updateDisplays();
    }

    @FXML
    private void onCloseParen() {
        expression.append(')');
        updateDisplays();
    }

    @FXML
    private void onDel() {
        if (!expression.isEmpty()) {
            expression.deleteCharAt(expression.length() - 1);
        }
        updateDisplays();
    }


    @FXML
    private void onAns() {
        startNewExpressionIfJustEvaluated();
        String ansText = formatResult(lastAns);
        expression.append(ansText);
        updateDisplays();
    }


    @FXML
    private void onEquals() {
        if (expression.isEmpty()) return;

        try {
            lastAns = ExpressionEvaluator.evaluate(expression.toString());
            lastExpression = expression.toString() + " =";
            justEvaluated = true;
            expression.setLength(0);
            updateDisplays();
        } catch (Exception e) {
            lastExpression = expression.toString();
            justEvaluated = true;
            resultLabel.setText("Error");
            expressionLabel.setText(lastExpression);
        }
    }


    private void startNewExpressionIfJustEvaluated() {
        if (justEvaluated) {
            expression.setLength(0);
            justEvaluated = false;
            lastExpression = "";
        }
    }

    //Funciones del titlebar
    public void init(Stage stage){
        this.stage = stage;
        setupCalculator();
        loadIcons();
        setupButtons();
        initDrag();
    }

    private void loadIcons(){
        iconMin = new Image(getClass().getResource("/icons/menos.png").toExternalForm());
        iconMax = new Image(getClass().getResource("/icons/cuadrado.png").toExternalForm());
        iconRestore = new Image(getClass().getResource("/icons/cuadricula.png").toExternalForm());
        iconClose = new Image(getClass().getResource("/icons/cerrar.png").toExternalForm());
    }

    private void setupButtons(){
        btnMinimize.setGraphic(createIconView(iconMin));
        btnToggleMaximize.setGraphic(createIconView(iconMax));
        btnClose.setGraphic(createIconView(iconClose));
    }

    private ImageView createIconView(Image img){
        ImageView iv = new ImageView(img);
        iv.setFitWidth(12);
        iv.setFitHeight(12);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        return iv;
    }

    private void initDrag() {
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    @FXML
    private void onClose() {
        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    private void onMinimize() {
        if (stage != null) {
            stage.setIconified(true); // minimiza a la barra de tareas
        }
    }

    @FXML
    private void onToggleMaximize() {
        if (stage != null) {
            boolean maximized = !stage.isMaximized();
            stage.setMaximized(!stage.isMaximized());
            if (maximized){
                btnToggleMaximize.setGraphic(createIconView(iconRestore));
            }else {
                btnToggleMaximize.setGraphic(createIconView(iconMax));
            }
        }


    }

}
