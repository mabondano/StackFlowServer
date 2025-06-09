package com.merlab.stackflow.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class PlotlyInJavaFX extends Application {
    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        String plotlyHtml = "<html><body>" +
                "<div id='plot'></div>" +
                "<script src='https://cdn.plot.ly/plotly-latest.min.js'></script>" +
                "<script>Plotly.newPlot('plot', [{y:[1,2,3,2]}]);</script>" +
                "</body></html>";
        webView.getEngine().loadContent(plotlyHtml, "text/html");
        stage.setScene(new Scene(webView, 600, 400));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
