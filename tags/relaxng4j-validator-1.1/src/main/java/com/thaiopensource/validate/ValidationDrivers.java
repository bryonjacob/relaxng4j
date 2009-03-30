package com.thaiopensource.validate;

import com.thaiopensource.util.SinglePropertyMap;
import com.thaiopensource.validate.rng.CompactSchemaReader;
import com.thaiopensource.validate.rng.SAXSchemaReader;
import com.thaiopensource.xml.sax.DraconianErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * static factory methods for doing basic tasks.
 *
 * @author <a href="mailto:bryon.jacob@gmail.com">Bryon Jacob</a>
 */
public class ValidationDrivers {
    private static final SinglePropertyMap DRACONIAN_ERROR_HANDLER_PROPERTIES =
            new SinglePropertyMap(ValidateProperty.ERROR_HANDLER, new DraconianErrorHandler());

    public static ValidationDriver newCompactSchemaDriver(InputSource schemaLocation)
            throws IOException, SAXException {
        return createSchemaDriver(schemaLocation, CompactSchemaReader.getInstance());
    }

    public static ValidationDriver newXmlSchemaDriver(InputSource schemaLocation)
            throws IOException, SAXException {
        return createSchemaDriver(schemaLocation, SAXSchemaReader.getInstance());
    }

    public static boolean compactValidate(InputSource schemaLocation, InputSource fileLocation)
            throws IOException, SAXException {
        return newCompactSchemaDriver(schemaLocation).validate(fileLocation);
    }

    public static boolean xmlValidate(InputSource schemaLocation, InputSource fileLocation)
            throws IOException, SAXException {
        return newXmlSchemaDriver(schemaLocation).validate(fileLocation);
    }

    private static ValidationDriver createSchemaDriver(InputSource schemaLocation,
                                                       SchemaReader reader)
            throws SAXException, IOException {
        ValidationDriver driver = new ValidationDriver(DRACONIAN_ERROR_HANDLER_PROPERTIES, reader);
        driver.loadSchema(schemaLocation);
        return driver;
    }
}
