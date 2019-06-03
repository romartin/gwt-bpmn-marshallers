//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.sax;

public class SAXException extends Exception {
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

    private Exception getExceptionInternal() {
        Throwable cause = super.getCause();
        return cause instanceof Exception ? (Exception)cause : null;
    }
}
