/*
 * $Id: JSONWriter.java 99 2009-07-29 22:39:57Z musachy $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jeedevframework.web;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.CharacterIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * <p>Serializes an object into JavaScript Object Notation (JSON). If cyclic references are detected
 * they will be nulled out. </p>
 */
@SuppressWarnings("unchecked")
public class Json {
    private static final Log log = LogFactory.getLog(Json.class);
    final static String RFC3339_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    /**
     * By default, enums are serialzied as name=value pairs
     */
    public static final boolean ENUM_AS_BEAN_DEFAULT = false;
    private static DateFormat formatter = new SimpleDateFormat(RFC3339_FORMAT);
    private static char[] hex = "0123456789ABCDEF".toCharArray();
    private static final boolean enumAsBean = ENUM_AS_BEAN_DEFAULT;
    private final static boolean excludeNullProperties = true ;
    private final static boolean ignoreHierarchy = true;
    
    private StringBuilder buf = new StringBuilder();
    private Stack stack = new Stack();
    private Object root;

    private boolean looped = false;  // 给writeSimple方法是用， 标记是否已经进入过一次 java bean.
    private int lastEndIndex = -1;   // 给writeSimple方法是用，标记上次输出到 buf 中的位置， 用于在发现第二次进入java bean的时候,回退已经放入到buf中的name.

    public static String simple(Object object){
    	try {
			return new Json().writeSimple(null == object ? "" : object);
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
    }
    
    public static String all(Object object){
    	try {
			return new Json().write(object);
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
    }
    
    private void clear(){
    	buf = new StringBuilder();
    	stack = new Stack();
    	root = null;
    	looped = false; 
    	lastEndIndex = -1; 
    }
    
    /**
     * @param object Object to be serialized into JSON
     * @return JSON string for object
     * @throws JSONException
     */
    public String write(Object object)
            throws JSONException {
    	clear();
    	
        this.buf.setLength(0);
        this.root = object;
        this.value(object, null);

        return this.buf.toString();
    }

    /**
     * @param object Object to be serialized into JSON
     * @return JSON string for object
     * @throws JSONException
     */
    public String writeSimple(Object object)
            throws JSONException {
    	clear();
    	
        this.buf.setLength(0);
        this.root = object;
        this.valueSimple(object, null);

        return this.buf.toString();
    }
    
    /**
     * Detect cyclic references
     */
    private void value(Object object, Method method) throws JSONException {
        if (object == null) {
            this.add("null");

            return;
        }

        if (this.stack.contains(object)) {
            Class clazz = object.getClass();

            //cyclic reference
            if (clazz.isPrimitive() || clazz.equals(String.class)) {
                this.process(object, method);
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("Cyclic reference detected on " + object);
                }

                this.add("null");
            }

            return;
        }

        this.process(object, method);
    }
    
    /**
     * Detect cyclic references
     */
    private void valueSimple(Object object, Method method) throws JSONException {
        if (object == null) {
            this.add("null");

            return;
        }

        if (this.stack.contains(object)) {
            Class clazz = object.getClass();

            //cyclic reference
            if (clazz.isPrimitive() || clazz.equals(String.class)) {
                this.valueSimple(object, method);
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("Cyclic reference detected on " + object);
                }

                this.add("null");
            }

            return;
        }

        this.processSimple(object, method);
    }

    /**
     * Serialize object into json
     */
    private void process(Object object, Method method) throws JSONException {
        this.stack.push(object);

        if (object instanceof Class) {
            this.string(object);
        } else if (object instanceof Boolean) {
            this.bool(((Boolean) object).booleanValue());
        } else if (object instanceof Number) {
            this.add(object);
        } else if (object instanceof String) {
            this.string(object);
        } else if (object instanceof Character) {
            this.string(object);
        } else if (object instanceof Map) {
            this.map((Map) object, method);
        } else if (object.getClass().isArray()) {
            this.array(object, method);
        } else if (object instanceof Iterable) {
            this.array(((Iterable) object).iterator(), method);
        } else if (object instanceof Date) {
            this.date((Date) object, method);
        } else if (object instanceof Calendar) {
            this.date(((Calendar) object).getTime(), method);
        } else if (object instanceof Locale) {
            this.string(object);
        } else if (object instanceof Enum) {
            this.enumeration((Enum) object);
        } else {
            this.bean(object);
        }

        this.stack.pop();
    }

    private void processSimple(Object object, Method method) throws JSONException {
        this.stack.push(object);

        if (object instanceof Class) {
            this.string(object);
        } else if (object instanceof Boolean) {
            this.bool(((Boolean) object).booleanValue());
        } else if (object instanceof Number) {
            this.add(object);
        } else if (object instanceof String) {
            this.string(object);
        } else if (object instanceof Character) {
            this.string(object);
        } else if (object instanceof Map) {
            //this.map((Map) object, method);
        } else if (object.getClass().isArray()) {
            //this.array(object, method);
        } else if (object instanceof Iterable) {
            //this.array(((Iterable) object).iterator(), method);
        } else if (object instanceof Date) {
            this.date((Date) object, method);
        } else if (object instanceof Calendar) {
            this.date(((Calendar) object).getTime(), method);
        } else if (object instanceof Locale) {
            this.string(object);
        } else if (object instanceof Enum) {
            this.enumeration((Enum) object);
        } else {
            this.beanSimple(object);
        }

        this.stack.pop();
    }
    
    /**
     * Instrospect bean and serialize its properties
     */
    private void beanSimple(Object object) throws JSONException {
    	if(looped){
    		this.buf.delete(lastEndIndex, this.buf.length());
    		return;
    	}
    	looped = true;
        //this.add("{");

        BeanInfo info;

        try {
            Class clazz = object.getClass();

            info = ((object == this.root) && this.ignoreHierarchy) ? Introspector
                    .getBeanInfo(clazz, clazz.getSuperclass()) : Introspector
                    .getBeanInfo(clazz);

            PropertyDescriptor[] props = info.getPropertyDescriptors();

            boolean hasData = false;
            for (int i = 0; i < props.length; ++i) {
                PropertyDescriptor prop = props[i];
                String name = prop.getName();
                Method accessor = prop.getReadMethod();
                Method baseAccessor = null;
                if (clazz.getName().indexOf("$$EnhancerByCGLIB$$") > -1 || clazz.getName().indexOf("$$_javassist") > -1 ) {
                    try {
                        baseAccessor = Class.forName(
                                clazz.getName().substring(0, clazz.getName().indexOf("$$")))
                                .getMethod(accessor.getName(), accessor.getParameterTypes());
                    } catch (Exception ex) {
                        log.debug(ex.getMessage(), ex);
                    }
                } else
                    baseAccessor = accessor;

                if (baseAccessor != null) {

//                    JSON json = baseAccessor.getAnnotation(JSON.class);
//                    if (json != null) {
//                        if (!json.serialize())
//                            continue;
//                        else if (json.name().length() > 0)
//                            name = json.name();
//                    }

                    //ignore "class" and others
                    if (this.shouldExcludeProperty(clazz, prop)) {
                        continue;
                    }
                   

                    Object value = accessor.invoke(object, new Object[0]);
                    boolean propertyPrinted = this.addSimple(name, value, accessor, hasData);
                    hasData = hasData || propertyPrinted;
                   
                }
            }

            // special-case handling for an Enumeration - include the name() as a property */
            if (object instanceof Enum) {
                Object value = ((Enum) object).name();
                this.addSimple("_name", value, object.getClass().getMethod("name"), hasData);
            }
        } catch (Exception e) {
            throw new JSONException(e);
        }

        //this.add("}");
    }
    
    /**
     * Instrospect bean and serialize its properties
     */
    private void bean(Object object) throws JSONException {
        this.add("{");

        BeanInfo info;

        try {
            Class clazz = object.getClass();

            info = ((object == this.root) && this.ignoreHierarchy) ? Introspector
                    .getBeanInfo(clazz, clazz.getSuperclass()) : Introspector
                    .getBeanInfo(clazz);

            PropertyDescriptor[] props = info.getPropertyDescriptors();

            boolean hasData = false;
            for (int i = 0; i < props.length; ++i) {
                PropertyDescriptor prop = props[i];
                String name = prop.getName();
                Method accessor = prop.getReadMethod();
                Method baseAccessor = null;
                if (clazz.getName().indexOf("$$EnhancerByCGLIB$$") > -1 || clazz.getName().indexOf("$$_javassist") > -1 ) {
                    try {
                        baseAccessor = Class.forName(
                                clazz.getName().substring(0, clazz.getName().indexOf("$$")))
                                .getMethod(accessor.getName(), accessor.getParameterTypes());
                    } catch (Exception ex) {
                        log.debug(ex.getMessage(), ex);
                    }
                } else
                    baseAccessor = accessor;

                if (baseAccessor != null) {

//                    JSON json = baseAccessor.getAnnotation(JSON.class);
//                    if (json != null) {
//                        if (!json.serialize())
//                            continue;
//                        else if (json.name().length() > 0)
//                            name = json.name();
//                    }

                    //ignore "class" and others
                    if (this.shouldExcludeProperty(clazz, prop)) {
                        continue;
                    }
                    String expr = null;
                   

                    Object value = accessor.invoke(object, new Object[0]);
                    boolean propertyPrinted = this.add(name, value, accessor, hasData);
                    hasData = hasData || propertyPrinted;
                    
                }
            }

            // special-case handling for an Enumeration - include the name() as a property */
            if (object instanceof Enum) {
                Object value = ((Enum) object).name();
                this.add("_name", value, object.getClass().getMethod("name"), hasData);
            }
        } catch (Exception e) {
            throw new JSONException(e);
        }

        this.add("}");
    }

    /**
     * Instrospect an Enum and serialize it as a name/value pair or as a bean including all its own properties
     */
    private void enumeration(Enum enumeration) throws JSONException {
        if (enumAsBean) {
            this.bean(enumeration);
        } else {
            this.string(enumeration.name());
        }
    }

    /**
     * Ignore "class" field
     */
    private boolean shouldExcludeProperty(Class clazz, PropertyDescriptor prop)
            throws SecurityException, NoSuchFieldException {
        String name = prop.getName();

        if (name.equals("class") || name.equals("declaringClass")
                || name.equals("cachedSuperClass") || name.equals("metaClass")) {
            return true;
        }

        return false;
    }

    /**
     * Add name/value pair to buffer
     */
    private boolean addSimple(String name, Object value, Method method, boolean hasData) throws JSONException {
        if (!excludeNullProperties || value != null) {
        	lastEndIndex = this.buf.length();
            if (hasData) {
                this.add(',');
            }
            this.add('"');
            this.add(name);
            this.add("\":");
            this.valueSimple(value, method);
            return true;
        }

        return false;
    }
    
    /**
     * Add name/value pair to buffer
     */
    private boolean add(String name, Object value, Method method, boolean hasData) throws JSONException {
        if (!excludeNullProperties || value != null) {
            if (hasData) {
                this.add(',');
            }
            this.add('"');
            this.add(name);
            this.add("\":");
            this.value(value, method);
            return true;
        }

        return false;
    }

    /**
     * Add map to buffer
     */
    private void map(Map map, Method method) throws JSONException {
        this.add("{");

        Iterator it = map.entrySet().iterator();

        boolean warnedNonString = false; // one report per map
		boolean hasData = false;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            String expr = null;
           
            if (hasData) {
                this.add(',');
            }
            hasData = true;
			if(!warnedNonString && ! (key instanceof String)) {
				log.warn("JavaScript doesn't support non-String keys, using toString() on "
						+ key.getClass().getName());
				warnedNonString = true;
			}
            this.value(key.toString(), method);
            this.add(":");
            this.value(entry.getValue(), method);
            
        }

        this.add("}");
    }

    /**
     * Add date to buffer
     */
    private void date(Date date, Method method) {
        this.string(formatter.format(date));
    }

    /**
     * Add array to buffer
     */
    private void array(Iterator it, Method method) throws JSONException {
        this.add("[");

        boolean hasData = false;
        for (int i = 0; it.hasNext(); i++) {
            String expr = null;
            
            if (hasData) {
                this.add(',');
            }
            hasData = true;
            this.value(it.next(), method);
           
        }

        this.add("]");
    }

    /**
     * Add array to buffer
     */
    private void array(Object object, Method method) throws JSONException {
        this.add("[");

        int length = Array.getLength(object);

        boolean hasData = false;
        for (int i = 0; i < length; ++i) {
            String expr = null;
            
            if (hasData) {
                this.add(',');
            }
            hasData = true;
            this.value(Array.get(object, i), method);
            
        }

        this.add("]");
    }

    /**
     * Add boolean to buffer
     */
    private void bool(boolean b) {
        this.add(b ? "true" : "false");
    }

    /**
     * escape characters
     */
    private void string(Object obj) {
        this.add('"');

        CharacterIterator it = new StringCharacterIterator(obj.toString());

        for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
            if (c == '"') {
                this.add("\\\"");
            } else if (c == '\\') {
                this.add("\\\\");
            } else if (c == '/') {
                this.add("\\/");
            } else if (c == '\b') {
                this.add("\\b");
            } else if (c == '\f') {
                this.add("\\f");
            } else if (c == '\n') {
                this.add("\\n");
            } else if (c == '\r') {
                this.add("\\r");
            } else if (c == '\t') {
                this.add("\\t");
            } else if (Character.isISOControl(c)) {
                this.unicode(c);
            } else {
                this.add(c);
            }
        }

        this.add('"');
    }

    /**
     * Add object to buffer
     */
    private void add(Object obj) {
        this.buf.append(obj);
    }

    /**
     * Add char to buffer
     */
    private void add(char c) {
        this.buf.append(c);
    }

    /**
     * Represent as unicode
     *
     * @param c character to be encoded
     */
    private void unicode(char c) {
        this.add("\\u");

        int n = c;

        for (int i = 0; i < 4; ++i) {
            int digit = (n & 0xf000) >> 12;

            this.add(hex[digit]);
            n <<= 4;
        }
    }

}
