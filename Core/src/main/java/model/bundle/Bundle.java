package model.bundle;

import java.util.ResourceBundle;

public class Bundle {
	private ResourceBundle resourceBundle;
	
	
	public Bundle(String fileName) {
		this.setResourceBundle(ResourceBundle.getBundle(String.format("property.%1s", fileName)));
	}
	public Bundle(String source, String fileName) {
		this.setResourceBundle(ResourceBundle.getBundle(String.format("%1s.%2s", source, fileName)));
	}
	
	
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
	public String getStringValue(String key) {
		return resourceBundle.getString(key);
	}
	public Object getObjectValue(String key) {
		return resourceBundle.getObject(key);
	}
}