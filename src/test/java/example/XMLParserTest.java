package example;

import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class XMLParserTest {
    @Test
    public void testGetTextByTagName() throws Exception {
        String xmlDoc = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        XMLParser xmlParser = new XMLParser(new StringReader(xmlDoc));
        assertEquals("fromUser",xmlParser.getTextByTagName("FromUserName"));
    }
}
