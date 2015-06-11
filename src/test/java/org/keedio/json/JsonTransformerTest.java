package org.keedio.json;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JsonTransformerTest {

    @Test
    public void testExpandSimpleArray() {

        String input = "{\"header\" : \"some data\", \"array\": [1, 2, 3]}";

        List<String> output = JsonTransformer.expand("array", input);

        Assert.assertEquals("Result was expected to be of size 3", 3, output.size());
    }

    @Test
    public void testExpandObjectArray() {
        String input = "{\"header\" : \"some data\", \"array\": [{\"data1\": 1}, {\"data2\": 2}]}";

        List<String> output = JsonTransformer.expand("array", input);

        Assert.assertEquals("Result was expected to be of size 2", 2, output.size());
    }
}
