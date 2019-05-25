//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.xml.sax;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream.PutField;

public class SAXException extends Exception {
    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[]{new ObjectStreamField("exception", Exception.class)};
    static final long serialVersionUID = 583241635256073760L;

    public SAXException() {
    }

    public SAXException(String message) {
        super(message);
    }

    public SAXException(Exception e) {
        super(e);
    }

    public SAXException(String message, Exception e) {
        super(message, e);
    }

    public String getMessage() {
        String message = super.getMessage();
        Throwable cause = super.getCause();
        return message == null && cause != null ? cause.getMessage() : message;
    }

    public Exception getException() {
        return this.getExceptionInternal();
    }

    public Throwable getCause() {
        return super.getCause();
    }

    public String toString() {
        Throwable exception = super.getCause();
        if (exception != null) {
            String var10000 = super.toString();
            return var10000 + "\n" + exception.toString();
        } else {
            return super.toString();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        PutField fields = out.putFields();
        fields.put("exception", this.getExceptionInternal());
        out.writeFields();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        GetField fields = in.readFields();
        Exception exception = (Exception)fields.get("exception", (Object)null);
        Throwable superCause = super.getCause();
        if (superCause == null && exception != null) {
            try {
                super.initCause(exception);
            } catch (IllegalStateException var6) {
                throw new InvalidClassException("Inconsistent state: two causes");
            }
        }

    }

    private Exception getExceptionInternal() {
        Throwable cause = super.getCause();
        return cause instanceof Exception ? (Exception)cause : null;
    }
}
