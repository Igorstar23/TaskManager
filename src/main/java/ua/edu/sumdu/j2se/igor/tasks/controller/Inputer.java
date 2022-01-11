package ua.edu.sumdu.j2se.igor.tasks.controller;

//import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class Inputer {
       private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       public static final String ERROR_READ_STR = "NONE_STR";
       public static final int ERROR_READ_INT = -1;
       public enum DateFormat {
              DEF {
                     public String toString() { return "dd-MM-yyyy|HH:mm"; }
                     @Override
                     public DateTimeFormatter getFormat() {
                            return DateTimeFormatter.ofPattern(DateFormat.DEF.toString())
                                    .withZone(ZoneId.systemDefault());
                     }
              },
              DEF_POINTS {
                     public String toString() { return "dd.MM.yyyy|HH:mm"; }
                     @Override
                     public DateTimeFormatter getFormat() {
                            return DateTimeFormatter.ofPattern(DateFormat.DEF_POINTS.toString())
                                    .withZone(ZoneId.systemDefault());
                     }
              };
              public abstract DateTimeFormatter getFormat();
       }

       /**
        * @return int from read l;ine or ERROR_READ_INT if it has problem with read line from System.in
        * */
       public static int readInt()  {
              int buf = ERROR_READ_INT;

              try {
                   buf = Integer.parseInt(Inputer.reader.readLine());

              } catch (IOException e) {
                   buf =  new Scanner(System.in).nextInt();

              } catch (Exception e ) { return ERROR_READ_INT; }

              return buf;
       }
       /**
        * @return string from read line or NONE_STR if it has problem with read line from System.in
        * */
       public static String readLine() {
              String buf = ERROR_READ_STR;

              try {
                    buf = Inputer.reader.readLine();

              } catch (IOException e) {
                    buf = new Scanner(System.in).nextLine();

              } catch (Exception e) { return ERROR_READ_STR; }

              return buf;
       }

       public static boolean readBool() throws IOException {
              return Inputer.reader.ready();
       }
       /**
        * @return false if don't read value
        * */
       public static boolean readBoolFromLine() {
              String bool = Inputer.readLine();

              bool = (bool == null)? ERROR_READ_STR : bool.toUpperCase(Locale.ROOT);

              while (ERROR_READ_STR.equals(bool)) {
                     System.out.print("Problem with read line.\nEnter value again : ");
                     bool = Inputer.readLine();

                     bool = (bool == null)? ERROR_READ_STR : bool.toUpperCase(Locale.ROOT);
              }

              if (bool.equals("yes".toUpperCase(Locale.ROOT))) return true;

              if (bool.equals("y".toUpperCase(Locale.ROOT))) return true;

              if (bool.equals("true".toUpperCase(Locale.ROOT))) return true;

              return false;
       }
       /**
        * @param date must be not null
        * @throws IllegalArgumentException if param date is null
        * */
       public static LocalDateTime readDateFromString(String date) {

              if (date == null) {
                  //Logger.getLogger(Inputer.class).debug("String date is null");
                  throw new IllegalArgumentException("Param date is null!");
              }
              return ZonedDateTime.parse(date, DateFormat.DEF_POINTS.getFormat()).toLocalDateTime();
       }

       /**
        * @return null if isn't read date correct from System.in
        * */
       public static LocalDateTime readDateFromLine() {
              LocalDateTime temp = null;

              String date = ERROR_READ_STR;

              while (ERROR_READ_STR.equals(date)) {

                     date = Inputer.readLine();
                     date = (date == null)? ERROR_READ_STR : date;

                     if (ERROR_READ_STR.equals(date)) continue;

                     try {
                           temp = readDateFromString(date);

                     } catch (DateTimeParseException e) {
                           System.out.print("Wrong format date!\nEnter date again" + "(" + DateFormat.DEF_POINTS + "): ");
                           //Logger.getLogger(Inputer.class).debug("didn't read date from string " + date + " :" + e);
                           date = ERROR_READ_STR;
                     }
              }
              return temp;
       }
}
