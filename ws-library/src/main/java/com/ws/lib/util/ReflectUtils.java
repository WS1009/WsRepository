package com.ws.lib.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {

    //无参
    public static Object createObject(String className) {
        Class[] paraTypes = new Class[]{};
        Object[] paraValues = new Object[]{};

        try {
            Class r = Class.forName(className);
            return createObject(r, paraTypes, paraValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    //无参
    public static Object createObject(Class clazz) {
        Class[] pareTyple = new Class[]{};
        Object[] paraValues = new Object[]{};

        return createObject(clazz, pareTyple, paraValues);
    }

    //一个参数
    public static Object createObject(String className, Class pareTyple, Object pareVaule) {
        Class[] paraTypes = new Class[]{pareTyple};
        Object[] paraValues = new Object[]{pareVaule};

        try {
            Class r = Class.forName(className);
            return createObject(r, paraTypes, paraValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    //一个参数
    public static Object createObject(Class clazz, Class pareTyple, Object pareVaule) {
        Class[] paraTypes = new Class[]{pareTyple};
        Object[] paraValues = new Object[]{pareVaule};

        return createObject(clazz, paraTypes, paraValues);
    }

    //多个参数
    public static Object createObject(String className, Class[] paraTypes, Object[] paraValues) {
        try {
            Class r = Class.forName(className);
            return createObject(r, paraTypes, paraValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    //多个参数
    public static Object createObject(Class clazz, Class[] paraTypes, Object[] paraValues) {
        Constructor ctor = null;
        try {
            ctor = clazz.getDeclaredConstructor(paraTypes);
        } catch (Exception e) {
            try {
                ctor = clazz.getConstructor(paraTypes);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (ctor != null) {
                    ctor.setAccessible(true);
                    return ctor.newInstance(paraValues);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    //多个参数
    public static Object invokeInstanceMethod(Object obj, String methodName, Class[] pareTypes, Object[] paraValues) {
        if (obj == null) {
            return null;
        }
        Method method = null;
        try {
            method = obj.getClass().getDeclaredMethod(methodName, pareTypes); //在指定类中获取指定的方法（public、protected、 private方法）
        } catch (NoSuchMethodException e) {
            try {
                method = obj.getClass().getMethod(methodName, pareTypes);//在指定类及其父类中获取指定方法（public方法）
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (method != null) {
                    method.setAccessible(true);
                    return method.invoke(obj, paraValues);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //一个参数
    public static Object invokeInstanceMethod(Object obj, String methodName, Class pareType, Object paraValue) {
        Class[] paraTypes = {pareType};
        Object[] paraValues = {paraValue};

        return invokeInstanceMethod(obj, methodName, paraTypes, paraValues);
    }

    //无参
    public static Object invokeInstanceMethod(Object obj, String methodName) {
        Class[] paraTypes = new Class[]{};
        Object[] paraValues = new Object[]{};

        return invokeInstanceMethod(obj, methodName, paraTypes, paraValues);
    }

    //无参
    public static Object invokeStaticMethod(String className, String method_name) {
        Class[] paraTypes = new Class[]{};
        Object[] paraValues = new Object[]{};

        return invokeStaticMethod(className, method_name, paraTypes, paraValues);
    }

    //一个参数
    public static Object invokeStaticMethod(String className, String method_name, Class pareTyple, Object pareVaule) {
        Class[] paraTypes = new Class[]{pareTyple};
        Object[] paraValues = new Object[]{pareVaule};

        return invokeStaticMethod(className, method_name, paraTypes, paraValues);
    }

    //多个参数
    public static Object invokeStaticMethod(String className, String method_name, Class[] paraTypes, Object[] paraValues) {
        try {
            Class obj_class = Class.forName(className);
            return invokeStaticMethod(obj_class, method_name, paraTypes, paraValues);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //无参
    public static Object invokeStaticMethod(Class clazz, String method_name) {
        Class[] paraTypes = new Class[]{};
        Object[] paraValues = new Object[]{};

        return invokeStaticMethod(clazz, method_name, paraTypes, paraValues);
    }

    //一个参数
    public static Object invokeStaticMethod(Class clazz, String method_name, Class classType, Object pareVaule) {
        Class[] classTypes = new Class[]{classType};
        Object[] paraValues = new Object[]{pareVaule};

        return invokeStaticMethod(clazz, method_name, classTypes, paraValues);
    }

    //多个参数
    public static Object invokeStaticMethod(Class clazz, String method_name, Class[] paraTypes, Object[] paraValues) {
        try {
            Method method = clazz.getDeclaredMethod(method_name, paraTypes);
            method.setAccessible(true);
            return method.invoke(null, paraValues);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    //简写版本
    public static Object getFieldObject(Object obj, String filedName) {
        return getFieldObject(obj.getClass(), obj, filedName);
    }

    public static Object getFieldObject(String className, Object obj, String filedName) {
        try {
            Class obj_class = Class.forName(className);
            return getFieldObject(obj_class, obj, filedName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getFieldObject(Class clazz, Object obj, String filedName) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //简写版本
    public static void setFieldObject(Object obj, String filedName, Object filedVaule) {
        setFieldObject(obj.getClass(), obj, filedName, filedVaule);
    }

    public static void setFieldObject(Class clazz, Object obj, String filedName, Object filedVaule) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(obj, filedVaule);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFieldObject(String className, Object obj, String filedName, Object filedVaule) {
        try {
            Class obj_class = Class.forName(className);
            setFieldObject(obj_class, obj, filedName, filedVaule);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Object getStaticFieldObject(String className, String filedName) {
        return getFieldObject(className, null, filedName);
    }

    public static Object getStaticFieldObject(Class clazz, String filedName) {
        return getFieldObject(clazz, null, filedName);
    }

    public static void setStaticFieldObject(String classname, String filedName, Object filedVaule) {
        setFieldObject(classname, null, filedName, filedVaule);
    }

    public static void setStaticFieldObject(Class clazz, String filedName, Object filedVaule) {
        setFieldObject(clazz, null, filedName, filedVaule);
    }
}