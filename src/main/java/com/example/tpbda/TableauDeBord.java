package com.example.tpbda;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class TableauDeBord implements Initializable {
    String sql1, sql2, sql3, sql4, sql5, sql6, sql7, sql8, sql9;
   @FXML
    Label Q;
    @FXML
    private TableView<Query> Bord;
    @FXML
    TableColumn<Query,Integer> Number;
    @FXML
    TableColumn<Query,String> pays;

    @FXML
    TableColumn<Query,String> wilaya;
    @FXML
    TableColumn<Query,String> language;
    @FXML
    TableColumn<Query,Integer> c5;
    @FXML
    TableColumn<Query,Integer> c6;
    ObservableList<Query> list= FXCollections.observableArrayList(
            new Query(0,"---","---","---")
    );

    @FXML
   protected void Q1() {
        Q.setText("Le Nombre de Pays : ");
        Number.setText("Nombre");
        pays.setText("---");
        wilaya.setText("---");
        language.setText("---");
            try
            {
                int number;
                sql1="SELECT count(Name) as Number FROM country;";
                Connection c = DriverManager.getConnection(
                        jdbc.url,
                        jdbc.user,
                        jdbc.password
                );
                Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet r=s.executeQuery(sql1);
                r.next();
                number = r.getInt("Number");
                ObservableList<Query> sqlR1= FXCollections.observableArrayList(
                        new Query(number,"---","---","---")
                );
                Bord.setItems(sqlR1);

        } catch (SQLException exp) {
        exp.printStackTrace();
    }

    }
    @FXML
    protected void Q2() {
        Q.setText("Le Noms des pays, Capitale, langues : ");
        Number.setText("---");
        pays.setText("pays");
        wilaya.setText("Capitale");
        language.setText("Langues");
        try
        {
        sql2="SELECT country.NAME,city.Name,countrylanguage.Language FROM world.country,world.city,world.countrylanguage WHERE city.ID= country.Capital and country.Code= countrylanguage.CountryCode ";
        Connection c = DriverManager.getConnection(
                jdbc.url,
                jdbc.user,
                jdbc.password
        );
        Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet r=s.executeQuery(sql2);
        int index=0;
            String NAME="";
            String Name="";
            String Lang="";
            ObservableList<Query> sqlR2 = FXCollections.observableArrayList();
            for(r.first();!r.isAfterLast();r.next()){
                NAME=r.getString("country.NAME");
                Name=r.getString("city.Name");
                //System.out.println("Name"+Name);
                Lang =r.getString("Language");
                 sqlR2.add(new Query(index,NAME,Name,Lang));
                index++;
            }
            c.close();
            Bord.setItems(sqlR2);

    } catch (SQLException exp) {
        exp.printStackTrace();
    }

}
    @FXML
    protected void Q3() {
        Q.setText("Les Villes du pays << Algeria >> : ");
        Number.setText("---");
        pays.setText("---");
        wilaya.setText("Wilaya");
        language.setText("---");
        try
        {
            sql3="SELECT Name FROM world.city WHERE CountryCode=\"DZA\"";
            Connection c = DriverManager.getConnection(
                    jdbc.url,
                    jdbc.user,
                    jdbc.password
            );
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet r=s.executeQuery(sql3);
            int index=0;
            String NAME="---";
            String Name="";
            String Lang="--";
            //System.out.println("Name"+Name);
            ObservableList<Query> sqlR3 = FXCollections.observableArrayList();
            for(r.first();!r.isAfterLast();r.next()){
                Name=r.getString("Name");
                sqlR3.add(new Query(index,NAME,Name,Lang));
                index++;
            }
            c.close();
            Bord.setItems(sqlR3);

        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }
    @FXML
    protected void Q4() {
        Q.setText("La Ville la moins peuplée du pays << Algeria >> : ");
        Number.setText("---");
        pays.setText("---");
        language.setText("---");
        wilaya.setText("Wilaya");
        try
        {
            sql4="SELECT Name FROM world.city WHERE Population IN (SELECT MIN(Population) FROM world.city WHERE CountryCode=\"DZA\")";
            Connection c = DriverManager.getConnection(
                    jdbc.url,
                    jdbc.user,
                    jdbc.password
            );
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet r=s.executeQuery(sql4);
            int index=0;
            String NAME="---";
            String Name="";
            String Lang="---";
            //System.out.println("Name"+Name);
            ObservableList<Query> sqlR4 = FXCollections.observableArrayList();
            for(r.first();!r.isAfterLast();r.next()){
                Name=r.getString("Name");
                sqlR4.add(new Query(index,NAME,Name,Lang));
                index++;
            }
            c.close();
            Bord.setItems(sqlR4);

        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }
    @FXML
    protected void Q5() {
        Q.setText("La Langue la plus parlée dans le monde : ");
        Number.setText("---");
        pays.setText("---");
        wilaya.setText("---");
        language.setText("Langue");
        sql5="select  Language from world.countrylanguage GROUP BY Language  order by  count(*) desc limit 1";
        try
        {
            sql5="select  Language from world.countrylanguage GROUP BY Language  order by  count(*) desc limit 1";
            Connection c = DriverManager.getConnection(
                    jdbc.url,
                    jdbc.user,
                    jdbc.password
            );
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet r=s.executeQuery(sql5);
            int index=0;
            String NAME="---";
            String Name="---";
            String Lang="";
            //System.out.println("Name"+Name);
            ObservableList<Query> sqlR5 = FXCollections.observableArrayList();
            for(r.first();!r.isAfterLast();r.next()){
                Lang=r.getString("Language");
                sqlR5.add(new Query(index,NAME,Name,Lang));
                index++;
            }
            c.close();
            Bord.setItems(sqlR5);

        } catch (SQLException exp) {
            exp.printStackTrace();
        }

    }
    @FXML
    protected void Q6() {
        Q.setText("Les noms des pays ayant une expérience de vie inférieur a 50 ans : ");
        Number.setText("---");
        pays.setText("Pays");
        wilaya.setText("---");
        language.setText("---");
        try
        {
            sql6="SELECT Name FROM world.country WHERE LifeExpectancy < 50";
            Connection c = DriverManager.getConnection(
                    jdbc.url,
                    jdbc.user,
                    jdbc.password
            );
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet r=s.executeQuery(sql6);
            int index=0;
            String NAME="---";
            String Name="";
            String Lang="---";
            //System.out.println("Name"+Name);
            ObservableList<Query> sqlR6 = FXCollections.observableArrayList();
            for(r.first();!r.isAfterLast();r.next()){
                NAME=r.getString("Name");
                sqlR6.add(new Query(index,NAME,Name,Lang));
                index++;
            }
            c.close();
            Bord.setItems(sqlR6);

        } catch (SQLException exp) {
            exp.printStackTrace();
        }

    }
    @FXML
    protected void Q7() {
        Q.setText("Les noms des pays du continent qui a le plus grand nombre de pays : ");
        Number.setText("---");
        pays.setText("Pays");
        wilaya.setText("---");
        language.setText("---");
        try
        {
            sql7="SELECT Name FROM world.country WHERE Continent = (select  Continent from world.country GROUP BY Continent  order by  count(*) desc limit 1)";
            Connection c = DriverManager.getConnection(
                    jdbc.url,
                    jdbc.user,
                    jdbc.password
            );
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet r=s.executeQuery(sql7);
            int index=0;
            String NAME="---";
            String Name="";
            String Lang="---";
            //System.out.println("Name"+Name);
            ObservableList<Query> sqlR7 = FXCollections.observableArrayList();
            for(r.first();!r.isAfterLast();r.next()){
                NAME=r.getString("Name");
                sqlR7.add(new Query(index,NAME,Name,Lang));
                index++;
            }
            c.close();
            Bord.setItems(sqlR7);

        } catch (SQLException exp) {
            exp.printStackTrace();
        }

    }
    @FXML
    protected void Q8() {
        Q.setText("Les noms des ville qui ne sont pas des capitales et qui appartiennet au continent asiatique : ");
        Number.setText("---");
        pays.setText("---");
        wilaya.setText("Ville");
        language.setText("---");
        try
        {
            sql8="SELECT Name,Continent,Capital FROM world.country WHERE Capital IS NULL AND Continent=\"Asia\"";
            Connection c = DriverManager.getConnection(
                    jdbc.url,
                    jdbc.user,
                    jdbc.password
            );
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet r=s.executeQuery(sql8);
            int index=0;
            String NAME="---";
            String Name="---";
            String Lang="---";
            //System.out.println("Name"+Name);
            ObservableList<Query> sqlR8 = FXCollections.observableArrayList();
            sqlR8.add(new Query(index,NAME,Name,Lang));
          //  System.out.println("Name"+Name);
//            for(r.first();!r.isAfterLast();r.next()){
//                index=r.getInt("Capital");
//                if (index!=0){
//                    break;
//                }
//                Name=r.getString("Name");
//                NAME=r.getString("Continent");
//                sqlR8.add(new Query(index,NAME,Name,Lang));
//            }
            c.close();
            Bord.setItems(sqlR8);

        } catch (SQLException exp) {
            exp.printStackTrace();
        }

    }
    @FXML
    protected void Q9() {
        Q.setText("La moyenne de la population du continent européen : ");
        Number.setText("La moyenne");
        pays.setText("---");
        wilaya.setText("---");
        language.setText("---");
        try
        {
            sql9="SELECT AVG(Population) FROM world.country WHERE Continent=\"Europe\"";
            Connection c = DriverManager.getConnection(
                    jdbc.url,
                    jdbc.user,
                    jdbc.password
            );
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet r=s.executeQuery(sql9);
            int index=0;
            String NAME="---";
            String Name="---";
            String Lang="---";
            //System.out.println("Name"+Name);
            ObservableList<Query> sqlR9 = FXCollections.observableArrayList();
            r.next();
                index=r.getInt("AVG(Population)");
                sqlR9.add(new Query(index,NAME,Name,Lang));
            c.close();
            Bord.setItems(sqlR9);

        } catch (SQLException exp) {
            exp.printStackTrace();
        }

    }
    @FXML
    protected void Delete() {
        int index=0;
        Q.setText("");
        String NAME="---";
        String Name="---";
        String Lang="---";
        ObservableList<Query> Delete = FXCollections.observableArrayList();
        Delete.add(new Query(0,"","",""));
        Bord.setItems(Delete);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c5.setText("---");
        c6.setText("---");
        Number.setText("Nombre");
        pays.setText("Pays");
        wilaya.setText("Wilaya");
        language.setText("Language");
        Number.setCellValueFactory(new PropertyValueFactory<Query,Integer>("Number"));
        pays.setCellValueFactory(new PropertyValueFactory<Query,String>("pays"));
        wilaya.setCellValueFactory(new PropertyValueFactory<Query,String>("wilaya"));
        language.setCellValueFactory(new PropertyValueFactory<Query,String>("language"));
        Bord.setItems(list);
    }
}
//            sql2="SELECT country.NAME,city.Name,countrylanguage.Language FROM world.country,world.city,world.countrylanguage WHERE city.ID= country.Capital and country.Code= countrylanguage.CountryCode ";
//            sql3="SELECT Name FROM world.city WHERE CountryCode=\"DZA\"";
//            sql4="SELECT Name FROM world.city WHERE Population IN (SELECT MIN(Population) FROM world.city WHERE CountryCode=\"DZA\")";
//            sql5="select  Language from world.countrylanguage GROUP BY Language  order by  count(*) desc limit 1";
//            sql6="SELECT Name, LifeExpectancy FROM world.country WHERE LifeExpectancy < 50;";
//            sql7="SELECT Name FROM world.country WHERE Continent = (select  Continent from world.country GROUP BY Continent  order by  count(*) desc limit 1);";
//            sql8="SELECT Name,Continent,Capital FROM world.country WHERE Capital IS NULL ;";
//            sql9="SELECT AVG(Population) FROM world.country WHERE Continent=\"Europe\";";