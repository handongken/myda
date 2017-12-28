package com.bqhx.yyb.constant;


public final class ContinueFlgEnum {
	enum cEnum {

		CONTINUEFLG_0("0", "续投"), CONTINUEFLG_1("1", "非续投"), CONTINUEFLG_2("2", "首次投资");
		
		private String enumKey;
		private String enumValue;
		
		private cEnum(String enumKey,String enumValue) {
			this.enumKey = enumKey;
			this.enumValue = enumValue;
		}

		public String getEnumKey() {
			return enumKey;
		}

		public String getEnumValue() {
			return enumValue;
		}
	}

	/**
	 * 获取enumvalue
	 * @param enumKey
	 * @return
	 */
	public static String getValue(String enumKey){
		String value = "";
		if(enumKey != null){
			for(int i = 0;i < cEnum.values().length;i++){
				if(cEnum.values()[i].getEnumKey().equals(enumKey)){
					value = cEnum.values()[i].getEnumValue();
					return value;
				}
			}
		}
		return value;
	}
	
	/**
	 * 获取enumkey
	 * @param enumValue
	 * @return
	 */
	public static String getKey(String enumValue){
		String key = "";
		if(enumValue != null){
			for(int i = 0;i < cEnum.values().length;i++){
				if(cEnum.values()[i].getEnumValue().equals(enumValue)){
					key = cEnum.values()[i].getEnumKey();
					return key;
				}
			}
		}
		return key;
	}
}
