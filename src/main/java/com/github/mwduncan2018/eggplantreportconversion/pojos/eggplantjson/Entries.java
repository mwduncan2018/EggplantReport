package com.github.mwduncan2018.eggplantreportconversion.pojos.eggplantjson;

public class Entries {
	private String handlerName;
	private String timestamp;
	private String message;
	private String scriptName;
	private String handlerLine;
	private String identifier;
	private String image;
	private String contextInfo;
	private OtherProperties otherProperties;

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public String getHandlerLine() {
		return handlerLine;
	}

	public void setHandlerLine(String handlerLine) {
		this.handlerLine = handlerLine;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContextInfo() {
		return contextInfo;
	}

	public void setContextInfo(String contextInfo) {
		this.contextInfo = contextInfo;
	}

	public OtherProperties getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(OtherProperties otherProperties) {
		this.otherProperties = otherProperties;
	}
	
	public Entries() {
	}
}
