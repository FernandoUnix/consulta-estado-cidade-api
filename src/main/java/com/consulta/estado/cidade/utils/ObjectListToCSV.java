package com.consulta.estado.cidade.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ObjectListToCSV {

	private static final String CSV_SEPARATOR = ",";

	public static String convertListToCSV(List<?> objectList) {
		if (objectList.size() < 1) {
			Log.getLog().info("Não encontrado itens para converter para csv");
			return "";
		}
		String csv = "";
		Object t = objectList.get(0);
		Field[] declaredFields = t.getClass().getDeclaredFields();
		ArrayList<Field> useableFields = getUseableFields(declaredFields);
		csv = getCSVHeader(csv, useableFields, null);
		csv = csv.concat("\n");
		for (Object object : objectList) {
			csv = addObjectValue(csv, useableFields, object);
			csv = csv.concat("\n");
		}
		return csv;
	}

	private static String addObjectValue(String csv, ArrayList<Field> useableFields, Object object) {

		for (Field field : useableFields) {
			try {
				if (canFieldUsedDirectly(field)) {
					if (object != null) {
						field.setAccessible(true);
						Object value = field.get(object);
						csv = csv.concat(value + "");
						field.setAccessible(false);
					}
				} else {
					if (object != null) {
						field.setAccessible(true);
						Object value = field.get(object);
						field.setAccessible(false);
						csv = addObjectValue(csv, getUseableFields(field.getType().getDeclaredFields()), value);
					}
				}
			} catch (IllegalArgumentException e) {
				Log.getLog().fine(e.getMessage());
			} catch (SecurityException e) {
				Log.getLog().fine(e.getMessage());
			} catch (IllegalAccessException e) {
				Log.getLog().fine(e.getMessage());
			}
			csv = csv.concat(CSV_SEPARATOR);
		}
		return csv;
	}

	private static boolean canFieldUsedDirectly(Field field) {
		return !field.getType().toString().contains("biplav");
	}

	private static ArrayList<Field> getUseableFields(Field[] declaredFields) {
		ArrayList<Field> useableFields = new ArrayList<Field>();
		for (Field field : declaredFields) {
			if (!Modifier.isStatic(field.getModifiers())) {
				useableFields.add(field);
			}
		}
		return useableFields;
	}

	private static String getCSVHeader(String csv, ArrayList<Field> useableFields, String prefix) {
		prefix = (prefix == null) ? "" : prefix;
		for (Field field : useableFields) {
			if (canFieldUsedDirectly(field)) {
				csv = csv.concat(prefix + field.getName());
				csv = csv.concat(CSV_SEPARATOR);
			} else {
				csv = getCSVHeader(csv, getUseableFields(field.getType().getDeclaredFields()), field.getName() + "_");
			}
		}
		return csv;
	}
}