package example.avro;

import org.junit.Test;

public class AvroDemoTest {

    @org.junit.Test
    public void testAvroGen() throws Exception {
        System.out.println("Test serde with code gen");
        AvroDemo.WriteAvroGen();
        AvroDemo.ReadAvroGen();
    }

    @Test
    public void testAvro() throws  Exception {
        System.out.println("Test serde without code gen");
        AvroDemo.WriteAvro();
        AvroDemo.ReadAvro();
    }
}