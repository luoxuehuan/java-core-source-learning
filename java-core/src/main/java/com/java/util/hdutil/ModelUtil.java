package com.java.util.hdutil;

/**
 * 实体模型操作工具类
 * 
 * @author sunfeng
 *
 */
public class ModelUtil {

	/**
	 * 得到注解类的实体名称，返回模式名.表名
	 * 
	 * @param clz
	 *            注解类的实体
	 * @return 模式名.表名
	 */
	public static String getModelName(Class<?> clz) {
		Model model = clz.getAnnotation(Model.class);

		if (model == null) {
			return null;
		}
		String schema = model.schema();
		String name = model.name();
		if (!StringUtil.isNull(schema)) {
			return schema + "." + name;
		} else {
			return name;
		}
	}

	/**
	 * 得到注解类的模式名，返回模式名
	 * 
	 * @param clz
	 *            注解类的实体
	 * @return 模式名
	 */
	public static String getModelSchemaName(Class<?> clz) {
		Model model = clz.getAnnotation(Model.class);

		if (model == null) {
			return null;
		}
		return model.schema();
	}

	/**
	 * 得到注解类的表名，返回表名
	 * 
	 * @param clz
	 *            注解类的实体
	 * @return 表名
	 */
	public static String getModelTableName(Class<?> clz) {
		Model model = clz.getAnnotation(Model.class);

		if (model == null) {
			return null;
		}
		return model.name();
	}
}
