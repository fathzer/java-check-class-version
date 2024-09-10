package com.fathzer.java.release;

public class Arguments {
	public static final String IS_JAVA_ARG = "-j";
	public static final String IGNORE_MODULE_INFO = "-i";
	
	private String path;
	private int release;
	private boolean isJava;
	private boolean ignoreModuleInfo;
	
	public Arguments(String[] args) {
		for (String arg : args) {
			if (arg.startsWith("-")) {
				if (IS_JAVA_ARG.equals(arg)) {
					isJava = true;
				} else if (IGNORE_MODULE_INFO.equals(arg)) {
					ignoreModuleInfo = true;
				} else {
					throw new IllegalArgumentException("Unknown option "+arg);
				}
			} else if (path==null) {
				path = arg;
			} else if (release==0) {
				release = Integer.parseInt(arg);
				if (release==0) {
					illegalRelease();;
				}
			}
		}
		if (release==0) {
			throw new IllegalArgumentException("Missing argument, expected at least a file name and a release number");
		}
		if (getRealease()<45) {
			illegalRelease();
		}
	}
	
	private void illegalRelease() {
		throw new IllegalArgumentException("Invalid release "+getRealease());
	}
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @return the realease
	 */
	public int getRealease() {
		return isJava ? release+44 : release;
	}
	/**
	 * @return the isJava
	 */
	public boolean isJava() {
		return isJava;
	}
	/**
	 * @return the ignoreModuleInfo
	 */
	public boolean isIgnoreModuleInfo() {
		return ignoreModuleInfo;
	}
}
