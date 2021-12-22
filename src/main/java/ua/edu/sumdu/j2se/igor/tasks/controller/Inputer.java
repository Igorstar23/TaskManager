package ua.edu.sumdu.j2se.igor.tasks.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
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
              DEF { public String toString() { return "d-MM-yyyyThh:mm"; } },
              DEF_POINTS { public String toString() { return "d-MM-yyyyThh:mm"; } };
       }      public String name() { return this.toString(); }

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
       //TODO: problem with parse date from string
       public static LocalDateTime readDateFromString(String date) {

              if (date == null) throw new IllegalArgumentException("Param date is null!");

              DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormat.DEF_POINTS.name());
              return ZonedDateTime.parse(date, formatter).toLocalDateTime();
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
                           System.out.print("Wrong format date!\nEnter date again" + "(" + DateFormat.DEF_POINTS + "): "
                           );
                           date = ERROR_READ_STR;
                     }
              }
              return temp;
       }
}