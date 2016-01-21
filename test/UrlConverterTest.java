import org.junit.Test;

import org.junit.Assert;

public class UrlConverterTest {

    @Test
    public void convertPass() {
        System.out.println("\nCONVERT TASK TEST:");
        String converted = services.UrlConverter.convert("https://www.youtube.com/watch?v=ugHsIj60VfQ");
        Assert.assertEquals(converted, "ugHsIj60VfQ");
    }
}
