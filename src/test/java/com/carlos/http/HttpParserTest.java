package com.carlos.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {

    private HttpParser httpParser;

    @BeforeAll
    public void beforeClass() {
        httpParser = new HttpParser();
    }

    @Test
    void parseHttpRequest() {
        httpParser.parseHttpRequest(generateValidTestCase());
    }

    private InputStream generateValidTestCase() {
        String rawData = "G E T   /   H T T P / 1 . 1\r\n" +
                "H o s t :   l o c a l h o s t : 8 0 8 0\r\n" +
                "C o n n e c t i o n :   k e e p - a l i v e\r\n" +
                "s e c - c h - u a :   \" C h r o m i u m \" ; v = \" 1 3 4 \" ,   \" N o t : A - B r a n d \" ; v = \" 2 4 \" ,   \" B r a v e \" ; v = \" 1 3 4 \"\r\n" +
                "s e c - c h - u a - m o b i l e :   ? 0\r\n" +
                "s e c - c h - u a - p l a t f o r m :   \" L i n u x \"\r\n" +
                "U p g r a d e - I n s e c u r e - R e q u e s t s :   1\r\n" +
                "U s e r - A g e n t :   M o z i l l a / 5 . 0   ( X 1 1 ;   L i n u x   x 8 6 _ 6 4 )   A p p l e W e b K i t / 5 3 7 . 3 6   ( K H T M L ,   l i k e   G e c k o )   C h r o m e / 1 3 4 . 0 . 0 . 0   S a f a r i / 5 3 7 . 3 6\r\n" +
                "A c c e p t :   t e x t / h t m l , a p p l i c a t i o n / x h t m l + x m l , a p p l i c a t i o n / x m l ; q = 0 . 9 , i m a g e / a v i f , i m a g e / w e b p , i m a g e / a p n g , * / * ; q = 0 . 8    S e c - G P C :   1\r\n" +
                "A c c e p t - L a n g u a g e :   e n - U S , e n ; q = 0 . 8\r]n" +
                "S e c - F e t c h - S i t e :   n o n e\r\n" +
                "S e c - F e t c h - M o d e :   n a v i g a t e\r\n" +
                "S e c - F e t c h - U s e r :   ? 1    \r\n" +
                "S e c - F e t c h - D e s t :   d o c u m e n t    A c c e p t - E n c o d i n g :   g z i p ,   d e f l a t e ,   b r ,   z s t d\r\n";
        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }
}