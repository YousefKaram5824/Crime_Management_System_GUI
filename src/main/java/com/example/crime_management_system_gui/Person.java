package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public abstract class Person extends Switching{
    @FXML
    TextField name;
    @FXML
    TextField id;
     abstract void view();
}
