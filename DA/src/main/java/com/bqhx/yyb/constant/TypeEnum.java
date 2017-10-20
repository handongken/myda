package com.bqhx.yyb.constant;


public final class TypeEnum {
	enum tEnum {

		TYPE_0("0","聚惠丰"), TYPE_1("1","聚惠盈"), TYPE_2("2","聚惠享"), TYPE_3("3","聚惠丰(50)"), TYPE_4("4","聚惠盈(50)"), TYPE_5("5","聚惠享(50)"), TYPE_6("6","聚惠丰(100)"), TYPE_7("7","聚惠盈(100)"), TYPE_8(
				"8","聚惠享(100)"), TYPE_9("9","聚惠福"), TYPE_10("10","聚惠鑫"), TYPE_11("11","聚惠福(50)"), TYPE_12("12","聚惠福(100)"), TYPE_13("13","聚惠鑫(50)"), TYPE_14(
						"14","聚惠鑫(100)"), TYPE_15("15","惠添利"), TYPE_16("16","惠添金"), TYPE_17("17","惠添利(50)"), TYPE_18("18","惠添利(100)"), TYPE_19("19","惠添金(50)"), TYPE_20("20","惠添金(100)");
		
		private String enumKey;
		private String enumValue;
		
		private tEnum(String enumKey,String enumValue) {
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

	public static String getValue(String enumKey){
		String enumValue = null;
		String value = null;
		if(enumKey != null){
			for(int i = 0;i < tEnum.values().length;i++){
				String key = tEnum.values()[i].getEnumKey();
				if(key.equals(enumKey)){
					enumValue = tEnum.values()[i].getEnumValue();
					if(enumValue.contains("聚惠丰")){
						value = "A";
					}else if(enumValue.contains("聚惠盈")){
						value = "B";
					}else if(enumValue.contains("聚惠享")){
						value = "C";
					}else if(enumValue.contains("聚惠福")){
						value = "D";
					}else if(enumValue.contains("聚惠鑫")){
						value = "E";
					}else if(enumValue.contains("惠添利")){
						value = "F";
					}else if(enumValue.contains("惠添金")){
						value = "G";
					}
					return value;
				}
			}
		}
		return value;
	}
}
