package org.jeedevframework.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.jsp.JspWriter;

public class BufferedJspWriter extends JspWriter{
    
    StringWriter sw ;
    PrintWriter pw ;
    
    public BufferedJspWriter(int bufferSize, boolean autoFlush) {
        super(bufferSize, autoFlush);
        sw = new StringWriter(bufferSize);
        pw = new PrintWriter(sw);
    }

    @Override
    public void clear() throws IOException {
        sw.getBuffer().delete(0, sw.getBuffer().length());
    }

    @Override
    public void clearBuffer() throws IOException {
        clear();
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public int getRemaining() {
        return 0;
    }

    @Override
    public void newLine() throws IOException {
        pw.println();
    }

    @Override
    public void print(boolean arg0) throws IOException {
        pw.print(arg0);
    }

    @Override
    public void print(char arg0) throws IOException {
        pw.print(arg0);
    }

    @Override
    public void print(int arg0) throws IOException {
        pw.print(arg0);
    }

    @Override
    public void print(long arg0) throws IOException {
        pw.print(arg0);
    }

    @Override
    public void print(float arg0) throws IOException {
        pw.print(arg0);
    }

    @Override
    public void print(double arg0) throws IOException {
        pw.print(arg0);
    }

    @Override
    public void print(char[] arg0) throws IOException {
        pw.print(arg0);
    }

    @Override
    public void print(String arg0) throws IOException {
        pw.print(arg0);
    }

    @Override
    public void print(Object arg0) throws IOException {
        pw.print(arg0);
    }

    @Override
    public void println() throws IOException {
        pw.println();
    }

    @Override
    public void println(boolean arg0) throws IOException {
        pw.println(arg0);
    }

    @Override
    public void println(char arg0) throws IOException {
        pw.println(arg0);
    }

    @Override
    public void println(int arg0) throws IOException {
        pw.println(arg0);
    }

    @Override
    public void println(long arg0) throws IOException {
        pw.println(arg0);
    }

    @Override
    public void println(float arg0) throws IOException {
        pw.println(arg0);
    }

    @Override
    public void println(double arg0) throws IOException {
        pw.println(arg0);
    }

    @Override
    public void println(char[] arg0) throws IOException {
        pw.println(arg0);
    }

    @Override
    public void println(String arg0) throws IOException {
        pw.println(arg0);
    }

    @Override
    public void println(Object arg0) throws IOException {
        pw.println(arg0);
    }

    @Override
    public void write(char[] arg0, int arg1, int arg2) throws IOException {
        sw.write(arg0, arg1, arg2);
    }
    
    public void removeLast(String str) throws IOException {
       int index = sw.getBuffer().lastIndexOf(str);
       sw.getBuffer().delete(index, sw.getBuffer().length());
    }
    
    public String toString(){
        return sw.toString();
    }
}
