package example.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by sponge on 15-3-11.
 */
public class AvroDemo {

    private static final String AVROFILE = "file.avro";
    private static final String AVRONOGENFILE = "filenogen.avro";
    private static final String SCHEMA = "./src/main/resources/avro/user.avsc";

    public static void WriteAvroGen() throws IOException {
        User user1 = new User();
        user1.setName("Tom");
        user1.setFavoriteNumber(256);
        user1.setFavoriteColor("RED");

        User user2 = new User();
        user2.setName("Jack");
        user2.setFavoriteNumber(100);
        user2.setFavoriteColor("GREEN");

        User user3 = new User("Alyssa", 4, "YELLO");

        DatumWriter<User> userDatuWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatuWriter);
        dataFileWriter.create(user1.getSchema(), new File(AVROFILE));
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();
    }

    public static void ReadAvroGen() throws IOException {
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(new File(AVROFILE), userDatumReader);
        User user = null;
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            System.out.println(user);
        }
    }

    public static void WriteAvro() throws IOException {
        Schema schema = new Schema.Parser().parse(new File(SCHEMA));

        GenericRecord user1 = new GenericData.Record(schema);
        user1.put("name", "Alyssa");
        user1.put("favorite_number", 256);
        // Leave favorite color null

        GenericRecord user2 = new GenericData.Record(schema);
        user2.put("name", "Ben");
        user2.put("favorite_number", 7);
        user2.put("favorite_color", "red");

        File file = new File(AVRONOGENFILE);
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.close();

    }

    public static void ReadAvro() throws IOException {
        Schema schema = new Schema.Parser().parse(new File(SCHEMA));

        // Deserialize users from disk
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File(AVRONOGENFILE), datumReader);
        GenericRecord user = null;
        while (dataFileReader.hasNext()) {
            // Reuse user object by passing it to next(). This saves us from
            // allocating and garbage collecting many objects for files with
            // many items.
            user = dataFileReader.next(user);
            System.out.println(user);
        }


    }
}