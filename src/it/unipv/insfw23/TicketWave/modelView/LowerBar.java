package it.unipv.insfw23.TicketWave.modelView;

import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class LowerBar extends HBox {

    private static LowerBar istance;

    private LowerBar(){

        DropShadow ombraInf = new DropShadow();
        ombraInf.setColor(Color.GRAY);
        setEffect(ombraInf);
        setMinHeight(30);
        setBackground(new Background(new BackgroundFill(Color.web("#80C1E2"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public static LowerBar getInstance(){
        if(istance == null){
            istance = new LowerBar();
        }
        return istance;
    }

}
