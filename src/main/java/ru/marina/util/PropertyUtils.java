package ru.marina.util;

import java.lang.reflect.Field;

public class PropertyUtils extends org.apache.commons.beanutils.PropertyUtils {
    private PropertyUtils() {
    }

    /**
     * Получает значение приватного поля объекта.
     *
     * @param bean Объект
     * @param name Имя приватного поля
     * @return Значение приватного поля
     */
    public static Object getPrivateProperty(Object bean, String name) throws IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = bean.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                return field.get(bean);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException(name);
    }

    /**
     * Устанавливает значение в приватное поле объекта.
     *
     * @param bean  Объект
     * @param name  Имя приватного поля
     * @param value Новое значение приватного поля
     */
    public static void setPrivateProperty(Object bean, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = bean.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                field.set(bean, value);
                return;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException(name);
    }
}
