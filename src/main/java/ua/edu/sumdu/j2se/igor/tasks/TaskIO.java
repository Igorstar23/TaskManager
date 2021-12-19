package ua.edu.sumdu.j2se.igor.tasks;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TaskIO {
       /**
         * Write tasks from list to stream of binary format
       * */
       public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
              DataOutputStream data = new DataOutputStream(out);
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
              data.flush();
              data.close();
       }
       /**
         * Read tasks from stream to current list of tasks
         * @throws IllegalArgumentException if param tasks is null
       * */
       public static void read(AbstractTaskList tasks, InputStream inputStream) throws IOException, ClassNotFoundException {

              if (tasks == null) throw new IllegalArgumentException("Param tasks is null!");

              DataInputStream data = new DataInputStream(inputStream);
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
                                     active
                                )
                       );

                   } else {
                       tasks.add(new Task(
                                     String.valueOf(mas),
                                     LocalDateTime.ofInstant(Instant.ofEpochMilli(data.readLong()), ZoneId.systemDefault()),
                                     active
                                )
                       );
                   }
              }
              data.close();
       }
       /**
         * Write tasks from list to file
       * */
       public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
              FileOutputStream fos = new FileOutputStream(file);
              TaskIO.write(tasks, fos);
              fos.close();
       }
       /**
         * Read tasks from file to list of tasks
         * @throws IllegalArgumentException if param tasks is null
       * */
       public static void readBinary(AbstractTaskList tasks, File file) throws IOException, ClassNotFoundException {

              if (tasks == null) throw new IllegalArgumentException("Param tasks is null!");

              FileInputStream fis = new FileInputStream(file);
              TaskIO.read(tasks, fis);
              fis.close();
       }

       //GSON's methods
       /**
         * Write tasks from list to stream in gson format
       * */
       public static void write(AbstractTaskList tasks, Writer out) throws IOException {
              GsonBuilder gsonBuilder = new GsonBuilder();
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
         * @throws IllegalArgumentException if param tasks is null
       * */
       public static void read(AbstractTaskList tasks, Reader in) {

              if (tasks == null) throw new IllegalArgumentException("Param tasks is null!");
              GsonBuilder g = new GsonBuilder();
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
       * */
       public static void writeText(AbstractTaskList tasks, File file) throws IOException {
              FileWriter fw  = new FileWriter(file);
              TaskIO.write(tasks, fw);
              fw.close();
       }
       /**
         * Read tasks from file
         * @throws IllegalArgumentException if param tasks is null
       * */
       public static void readText(AbstractTaskList tasks, File file) throws IOException {

              if (tasks == null) throw new IllegalArgumentException("Param tasks is null!");
              FileReader fr = new FileReader(file);
              TaskIO.read(tasks, fr);
              fr.close();
       }
}
