module project.wgusoftware2project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens project.wgusoftware2project to javafx.fxml;
    opens project.wgusoftware2project.model to javafx.base;
    exports project.wgusoftware2project;
    exports project.wgusoftware2project.controllers;
    opens project.wgusoftware2project.controllers to javafx.fxml;
}