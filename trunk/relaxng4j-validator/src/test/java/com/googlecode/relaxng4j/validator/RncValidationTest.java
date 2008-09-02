/* Copyright Homeaway, Inc 2005-2007. All Rights Reserved.
* No unauthorized use of this software.
*/
package com.googlecode.relaxng4j.validator;

import com.thaiopensource.util.SinglePropertyMap;
import com.thaiopensource.validate.ValidateProperty;
import com.thaiopensource.validate.ValidationDriver;
import com.thaiopensource.validate.rng.CompactSchemaReader;
import junit.framework.TestCase;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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
        ValidationDriver validationDriver;
        validationDriver =
                new ValidationDriver(ERROR_HANDLER_PROPERTY_MAP,
                                     CompactSchemaReader.getInstance());

        validationDriver.loadSchema(new InputSource(
                getClass().getClassLoader().getResourceAsStream("com/googlecode/relaxng4j/validator/" + type + ".rnc")));

        File[] files = new File(getClass().getClassLoader().getResource(
                "com/googlecode/relaxng4j/validator/" + type).toURI()).listFiles();

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

    private static final SinglePropertyMap ERROR_HANDLER_PROPERTY_MAP =
            new SinglePropertyMap(
                    ValidateProperty.ERROR_HANDLER,
                    new ErrorHandler() {

                        public void warning(SAXParseException exception) throws SAXException {
                            throw exception;
                        }

                        public void error(SAXParseException exception) throws SAXException {
                            throw exception;
                        }

                        public void fatalError(SAXParseException exception) throws SAXException {
                            throw exception;
                        }
                    });

}
