package com.relaxng4j.validator;

import com.thaiopensource.validate.ValidationDriver;
import com.thaiopensource.validate.ValidationDrivers;
import junit.framework.TestCase;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class RncValidationTest extends TestCase {
    public static final Logger log = Logger.getLogger(RncValidationTest.class.getSimpleName());


    protected void setUp() throws Exception {
    }

    public void testWidgetDocuments() throws Exception {
        runValidationTest("widgets");
    }

    private void runValidationTest(String type) throws Exception {
        ValidationDriver validationDriver = ValidationDrivers.newCompactSchemaDriver(
                new InputSource(getClass().getClassLoader().getResourceAsStream(
                        "com/relaxng4j/validator/" + type + ".rnc")));

        File[] files = new File(getClass().getClassLoader().getResource(
                "com/relaxng4j/validator/" + type).toURI()).listFiles();

        for (int i = 0; i < files.length; i++) {
            log.info("validating " + files[i].getName());
            String expectedErrorMessage = new BufferedReader(
                    new InputStreamReader(new FileInputStream(files[i]))).readLine()
                    .replaceAll("^\\<\\!\\-\\-|\\-\\-\\>$", "").trim();
            try {
                validationDriver.validate(new InputSource(new FileInputStream(files[i])));
                assertEquals("expected error: " + expectedErrorMessage,
                             "OK", expectedErrorMessage);
            } catch (SAXException e) {
                assertEquals(expectedErrorMessage, e.getMessage());
            }
        }
    }

}
