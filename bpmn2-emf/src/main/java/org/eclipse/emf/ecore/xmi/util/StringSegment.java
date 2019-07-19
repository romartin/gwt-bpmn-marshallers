/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: StringSegment.java,v 1.7 2007/03/20 13:54:02 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.util;


import java.util.Iterator;
import java.util.ListIterator;

/**
 * A String Buffer that never reallocates
 */
public class StringSegment extends BasicEList<StringSegment.Element>
{
  private static final long serialVersionUID = 1L;

  protected final static int LIST_SIZE = 100;

  protected static final int ELEMENT_SIZE = 1000;

  protected static final int BUFFER_SIZE = 8192;

  protected int segmentCapacity;

  protected byte[] outputbytes;

  protected char[] outputchars;

  protected char[] buffer;

  protected Element cursor;

  protected int cursorIndex = 0;

  protected String lineSeparator = System.getProperty("line.separator", "\n");

  protected int bufferPosition;

  public StringSegment()
  {
    this(LIST_SIZE);
  }

  public StringSegment(int minimumCapacity)
  {
    this(minimumCapacity, ELEMENT_SIZE);
  }

  public StringSegment(int minimumCapacity, int segmentCapacity)
  {
    super(minimumCapacity);
    add(cursor = new Element(this.segmentCapacity = segmentCapacity));
    outputchars = new char [BUFFER_SIZE];
  }

  @Override
  protected Object[] newData(int capacity)
  {
    return new Element [capacity];
  }

  public void reset()
  {
    bufferPosition = 0;
    cursor = (Element)data[0];
    cursorIndex = 0;
    for (int i = 0; i < size; i++)
    {
      ((Element)data[i]).size = 0;
    }
  }

  public void add(String newString)
  {
    // System.err.println("add = ["+newString+"]");

    // This is the cheapest and most common case.
    //
    if (cursor.size < segmentCapacity)
    {
      cursor.add(newString);
      return;
    }

    Element oldCursor = cursor;
    int index = size - 1;
    if (cursorIndex < index)
    {
      cursor = (Element)data[++cursorIndex];
      if (cursor.size == 0)
      {
        cursor.add(newString);
        return;
      }
    }

    cursor = new Element(segmentCapacity);
    cursor.add(newString);

    // The first case is the most common case.
    // It is slightly cheaper to call add without an index since an index will be range checked.
    //  
    if (data[index] == oldCursor)
    {
      super.add(cursor);
      cursorIndex = ++index;
    }
    else
    {
      // This case can only happen if we are reset to a mark and we've got lots of XMLNS attributes to write.
      //
      int counter = 0;
      while (counter < index)
      {
        if (data[counter++] == oldCursor)
        {
          cursorIndex = counter;
          super.add(cursorIndex, cursor);
          break;
        }
      }
    }
  }

  public void addLine()
  {
    add(lineSeparator);
  }

  public Object mark()
  {
    Element result = cursor;
    if (cursor.size == 0)
    {
      result.add("");
    }
    int i = size - 1;
    if (cursorIndex < i)
    {
      cursor = (Element)data[++cursorIndex];
    }
    else
    {
      cursorIndex++;
      cursor = new Element(segmentCapacity);
      super.add(cursor);
    }
    return result;
  }

  public void resetToMark(Object mark)
  {
    cursor = (Element)mark;
    for (int i = 0; i < data.length; i++)
    {
      if (data[i] == cursor)
      {
        cursorIndex = i;
        return;
      }
    }
  }

  public int getLength()
  {
    Element[] elements = (Element[])data;
    int length = 0;
    for (int i = 0; i < size; ++i)
    {
      Element element = elements[i];
      int segmentSize = element.size;
      for (int j = 0; j < segmentSize; ++j)
      {
        String s = element.data[j];
        length += s.length();
      }
    }
    return length;
  }

  public int getChars(char[] destination, int position)
  {
    Element[] elements = (Element[])data;
    for (int i = 0; i < size; ++i)
    {
      Element element = elements[i];
      int segmentSize = element.size;
      for (int j = 0; j < segmentSize; ++j)
      {
        String string = element.data[j];
        int length = string.length();
        string.getChars(0, length, destination, position);
        position += length;
      }
    }
    return position;
  }


  protected static class Element
  {
    int size;

    String[] data;

    Element(int capacity)
    {
      data = new String [capacity];
    }

    void add(String newString)
    {
      data[size++] = newString;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public Iterator<Element> iterator()
  {
    // TODO This is really quite attrocious since there is code that will assume an iterator that returns strings!
    return (ListIterator<Element>)(ListIterator<?>)new SegmentIterator();
  }

  @SuppressWarnings("unchecked")
  @Override
  public ListIterator<Element> listIterator()
  {
    // TODO This is really quite attrocious since there is code that will assume an iterator that returns strings!
    return (ListIterator<Element>)(Iterator<?>)new SegmentIterator();
  }

  public Iterator<String> stringIterator()
  {
    return new SegmentIterator();
  }

  protected class SegmentIterator implements ListIterator<String>
  {
    protected int outerIndex = 0;

    protected int innerIndex = 0;

    SegmentIterator()
    {
      super();
    }

    public boolean hasNext()
    {
      return outerIndex < size - 1 || (outerIndex == size - 1 && innerIndex < ((Element)data[outerIndex]).size);
    }

    public boolean hasPrevious()
    {
      return outerIndex > 0 || innerIndex > 0;
    }

    public String next()
    {
      Element element = (Element)data[outerIndex];
      if (innerIndex < element.size)
      {
        return element.data[innerIndex++];
      }
      else
      {
        innerIndex = 1;
        return ((Element)data[++outerIndex]).data[0];
      }
    }

    public String previous()
    {
      if (innerIndex > 0)
      {
        return ((Element)data[outerIndex]).data[--innerIndex];
      }
      else
      {
        Element element = (Element)data[--outerIndex];
        innerIndex = element.size - 1;
        return element.data[innerIndex];
      }
    }

    public void add(String newElement)
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }

    public void remove()
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }

    public void set(String newElement)
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }

    public int nextIndex()
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }

    public int previousIndex()
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }
  }
}
