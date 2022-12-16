package com.example.tpbda;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbc {
    public static final String url="jdbc:mysql://localhost/world?serverTimezone=UTC";
    public static final String user="root";
    public static final String password="younesSql";
    public static String connection(String sql,String columnlabel,int n){
        StringBuilder str= new StringBuilder();
        String sql1,sql2,sql3,sql4,sql5,sql6,sql7,sql8,sql9;
        //String sql="SELECT * FROM country";
        try {
            Connection c = DriverManager.getConnection(
                    url,
                    user,
                    password
            );
//            sql2="SELECT country.NAME,city.Name,countrylanguage.Language FROM world.country,world.city,world.countrylanguage WHERE city.ID= country.Capital and country.Code= countrylanguage.CountryCode ";
//            sql3="SELECT Name FROM world.city WHERE CountryCode=\"DZA\"";
//            sql4="SELECT Name FROM world.city WHERE Population IN (SELECT MIN(Population) FROM world.city WHERE CountryCode=\"DZA\")";
//            sql5="select  Language from world.countrylanguage GROUP BY Language  order by  count(*) desc limit 1";
//            sql6="SELECT Name, LifeExpectancy FROM world.country WHERE LifeExpectancy < 50;";
//            sql7="SELECT Name FROM world.country WHERE Continent = (select  Continent from world.country GROUP BY Continent  order by  count(*) desc limit 1);";
//            sql8="SELECT Name,Continent,Capital FROM world.country WHERE Capital IS NULL ;";
//            sql9="SELECT AVG(Population) FROM world.country WHERE Continent=\"Europe\";";
            System.out.println("Connected !");
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet r=s.executeQuery(sql);

            //System.out.println(r.getInt(""));
            r.next();

            System.out.println(str);
            switch (n){
                case 1 : {
                    str = new StringBuilder(r.getString(columnlabel));
                    break;
                }
                case 2 : {
                    for(r.first();!r.isAfterLast();r.next()){
                        str.append(r.getString("NAME"));
                        str.append(r.getString("Language"));

                        //str.append(r.getString("Region"));
                    }
                    break;
                }
                case 3 : {
                    r.getString(columnlabel);
                    break;
                }
                case 4 : {
                    break;
                }
                case 5 : {
                    break;
                }
                case 6 : {
                    break;
                }
                case 7 : {
                    break;
                }
                case 8 : {
                    break;
                }
                case 9 : {
                   str = new StringBuilder(r.getString(columnlabel));
                    break;
                }
                default: break;
            }
           // r.getString("");


        } catch (SQLException exp) {
            exp.printStackTrace();
        }
        return str.toString();
    }
}
