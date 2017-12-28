package com.bqhx.yyb.constant;


public final class StatusEnum {
	enum sEnum {

		STATUS_0("0", "正常"), STATUS_1("1", "已赎回"), STATUS_2("2", "申请提前赎回");
		
		private String enumKey;
		private String enumValue;
		
		private sEnum(String enumKey,String enumValue) {
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
			for(int i = 0;i < sEnum.values().length;i++){
				if(sEnum.values()[i].getEnumKey().equals(enumKey)){
					value = sEnum.values()[i].getEnumValue();
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
			for(int i = 0;i < sEnum.values().length;i++){
				if(sEnum.values()[i].getEnumValue().equals(enumValue)){
					key = sEnum.values()[i].getEnumKey();
					return key;
				}
			}
		}
		return key;
	}
}
