package model;

/**
 *
 * @author Gaboros
 */
public class FieldIsMineException extends Exception {

    /**
     *
     * @param msg the exception's reason
     */
    public FieldIsMineException(String msg) {
        super(msg);
    }
}
