package iofiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public final class IOController {
    private static IOController instance = null;
    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private IOController() {

    }

    /**
     * @return instance of the class.
     */
    public static IOController getInstance() {
        if (instance == null) {
            instance = new IOController();
        }
        return instance;
    }

    /**
     * Used to read input.
     * @param in file to read from.
     * @return InputDataType instance initialised with the read values.
     * @throws IOException from readValue.
     */
    public InputDataType parseInput(final String in) throws IOException {
        return OBJECT_MAPPER.readValue(new File(in), InputDataType.class);
    }

    /**
     * Used to write output.
     * @param out file to write output to.
     * @param outputDataType instance of OutputDataType to be printed.
     * @throws IOException from writeValue.
     */
    public void writeFile(final String out, final OutputDataType outputDataType)
                                                                throws IOException {
        OBJECT_MAPPER.writeValue(new File(out), outputDataType);
    }
}
