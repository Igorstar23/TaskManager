package ua.edu.sumdu.j2se.igor.tasks.model;

import com.google.gson.*;
import ua.edu.sumdu.j2se.igor.tasks.controller.Inputer;

import java.io.*;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TaskIO {
       /**
         * Write tasks from list to stream of binary format
         * @param out must be not null
         * @throws IllegalArgumentException if param 'out' is null
       * */
       public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {

              if (out == null) throw new IllegalArgumentException("Param 'out' is null!");

              try (DataOutputStream data = new DataOutputStream(out)) {
                   data.writeInt(tasks.size());
                   for (var task : tasks) {
                        data.writeInt(task.getTitle().length());
                        data.writeUTF(task.getTitle());
                        data.writeBoolean(task.isActive());
                        data.writeBoolean(task.isRepeated());

                        if (task.isRepeated()) {
                            data.writeInt(task.getRepeatInterval());
                            ZonedDateTime zonedDateTime = ZonedDateTime.of(task.getStartTime(), ZoneId.systemDefault());
                            data.writeLong(zonedDateTime.toInstant().toEpochMilli());
                            zonedDateTime = ZonedDateTime.of(task.getEndTime(), ZoneId.systemDefault());
                            data.writeLong(zonedDateTime.toInstant().toEpochMilli());

                        } else {
                            ZonedDateTime zonedDateTime = ZonedDateTime.of(task.getTime(), ZoneId.systemDefault());
                            data.writeLong(zonedDateTime.toInstant().toEpochMilli());
                        }
                   }
              }
       }
       /**
         * Read tasks from stream to current list of tasks
         * @param tasks must be not null
         * @param inputStream must be not null
         * @throws IllegalArgumentException if param tasks or 'inputStream' is null
       * */
       public static void read(AbstractTaskList tasks, InputStream inputStream) throws IOException {

              if (tasks == null) throw new IllegalArgumentException("Param tasks is null!");

              if (inputStream == null) throw new IllegalArgumentException("Param 'inputStream' is null!");

              try (DataInputStream data = new DataInputStream(inputStream)) {
                   int size = data.readInt();
                   for (int i = 0; i < size; i++) {
                        data.readInt();
                        String mas = data.readUTF();
                        boolean active = data.readBoolean();
                        boolean repeated = data.readBoolean();

                        if (repeated) {
                            int interval = data.readInt();
                            long timeStart = data.readLong();
                            long timeEnd = data.readLong();

                            tasks.add(new Task(
                                          String.valueOf(mas),
                                          LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStart), ZoneId.systemDefault()),
                                          LocalDateTime.ofInstant(Instant.ofEpochMilli(timeEnd), ZoneId.systemDefault()),
                                          interval,
                                          active)
                            );

                        } else {
                            tasks.add(new Task(
                                          String.valueOf(mas),
                                          LocalDateTime.ofInstant(Instant.ofEpochMilli(data.readLong()), ZoneId.systemDefault()),
                                          active)
                            );
                        }
                   }
              }
       }
       /**
         * Write tasks from list to file
         * @param file must be not null
         * @throws IllegalArgumentException if param file is null
       * */
       public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {

              if (file == null) throw new IllegalArgumentException("Param file is null!");
              try (FileOutputStream fos = new FileOutputStream(file)) {
                   TaskIO.write(tasks, fos);
              }
       }
       /**
         * Read tasks from file to list of tasks
         * @param tasks must be not null
         * @param file must be not null
         * @throws IllegalArgumentException if param tasks or file is null
       * */
       public static void readBinary(AbstractTaskList tasks, File file) throws IOException {

              if (tasks == null) throw new IllegalArgumentException("Param tasks is null!");

              if (file == null) throw new IllegalArgumentException("Param file is null!");

              try (FileInputStream fis = new FileInputStream(file)) {
                   TaskIO.read(tasks, fis);
              }
       }

       //GSON's methods
       /**
         * Write tasks from list to stream in gson format
         * @param out must be not null
         * @throws IllegalArgumentException if param 'out' is null
       * */
       public static void write(AbstractTaskList tasks, Writer out) throws IOException {

              if (out == null) throw new IllegalArgumentException("Pram 'out' is null!");
              GsonBuilder gsonBuilder = new GsonBuilder(); // TODO : add Adapter to datatimeformatter
              gsonBuilder.setPrettyPrinting().registerTypeAdapter(DateTimeFormatter.class, new JsonSerializer<DateTimeFormatter>() {
                  @Override
                  public JsonElement serialize(DateTimeFormatter dateTimeFormatter, Type type, JsonSerializationContext jsonSerializationContext) {
                      return new JsonPrimitive(dateTimeFormatter.toString());
                  }
              });
              out.write(gsonBuilder.setPrettyPrinting().registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                         @Override
                         public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
                                return new JsonPrimitive(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant().toEpochMilli()
                                );
                         }
              }).create().toJson(tasks));
              out.flush();
       }
       /**
         * Read tasks from stream to list
         * @param tasks must be not null
         * @param in must be not null
         * @throws IllegalArgumentException if param tasks or 'in' is null
       * */
       public static void read(AbstractTaskList tasks, Reader in) {

              if (tasks == null) throw new IllegalArgumentException("Param tasks is null!");

              if (in == null) throw new IllegalArgumentException("Param 'in' is null!");
              GsonBuilder g = new GsonBuilder();
              g.registerTypeAdapter(DateTimeFormatter.class, new JsonDeserializer<DateTimeFormatter>() {
                        @Override
                        public DateTimeFormatter deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                               return DateTimeFormatter.ofPattern(jsonElement.getAsString());
                        }
              });
              g.registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {

                        @Override
                        public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                               return LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonElement.getAsLong()), ZoneId.systemDefault());
                        }
              });
              tasks.addAllFromList(g.create().fromJson(in, tasks.getClass()));
       }
       /**
         * Write tasks to file in gson format
         * @param file must be not null
         * @throws IllegalArgumentException if param file is null
       * */
       public static void writeText(AbstractTaskList tasks, File file) throws IOException {

              if (file == null) throw new IllegalArgumentException("Param file is null!");
              try (FileWriter fw  = new FileWriter(file)) {
                   TaskIO.write(tasks, fw);
              }
       }
       /**
         * Read tasks from file
         * @param tasks must be not null
         * @param file must be not null
         * @throws IllegalArgumentException if param tasks or file is null
       * */
       public static void readText(AbstractTaskList tasks, File file) throws IOException {

              if (tasks == null) throw new IllegalArgumentException("Param tasks is null!");

              if (file == null) throw new IllegalArgumentException("Param file is null!");

              try (FileReader fr = new FileReader(file)) {
                   TaskIO.read(tasks, fr);
              }
       }
}
