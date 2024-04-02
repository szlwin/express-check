package smarter.common.express.execute;

import java.util.Map;

public class SimpleExprParam {

	private Map<String,Object> paramMap;
	
	private Map<String,Object> externalMap;

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public Map<String, Object> getExternalMap() {
		return externalMap;
	}

	public void setExternalMap(Map<String, Object> externalMap) {
		this.externalMap = externalMap;
	}
	
	
}
