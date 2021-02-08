package com.github.skjolber.jackson.jsh.highlighter;

public final class AnsiSyntaxHighlight {

    public final static String	ESC_START 			= "\u001B[";
    public final static String	ESC_END 				= "m";

	public static final String	CLEAR				= "0";

	public static final String	HIGH_INTENSITY		= "1";
	public static final String	LOW_INTENSITY		= "2";

	public static final String	ITALIC				= "3";
	public static final String	UNDERLINE			= "4";
	public static final String	BLINK				= "5";
	public static final String	RAPID_BLINK			= "6";
	public static final String	REVERSE_VIDEO		= "7";
	public static final String	INVISIBLE_TEXT		= "8";

	public static final String	BLACK				= "30";
	public static final String	RED 				= "31";
	public static final String	GREEN				= "32";
	public static final String	YELLOW				= "33";
	public static final String	BLUE				= "34";
	public static final String	MAGENTA				= "35";
	public static final String	CYAN				= "36";
	public static final String	WHITE				= "37";
	public static final String	DEFAULT				= "38";

	public static final String	BACKGROUND_BLACK	= "40";
	public static final String	BACKGROUND_RED		= "41";
	public static final String	BACKGROUND_GREEN	= "42";
	public static final String	BACKGROUND_YELLOW	= "43";
	public static final String	BACKGROUND_BLUE		= "44";
	public static final String	BACKGROUND_MAGENTA	= "45";
	public static final String	BACKGROUND_CYAN		= "46";
	public static final String	BACKGROUND_WHITE	= "47";
	public static final String	BACKGROUND_DEFAULT 	= "49";

	public static final String	SEPERATOR 			= ";";

	public final static String	RESET = ESC_START + CLEAR + ESC_END;

	public static String build(String arg) {
        StringBuilder sb = new StringBuilder();
        sb.append(ESC_START);
        sb.append(CLEAR);
        sb.append(';');
        sb.append(arg);
        sb.append(ESC_END);
        return sb.toString();
	}

	public static String build(String ... args) {
		if(args == null || args.length == 0) {
			return RESET;
		}
        StringBuilder sb = new StringBuilder();
        sb.append(ESC_START);
        sb.append(CLEAR);
        sb.append(';');
        for(String arg : args) {
        	if(!arg.equals(CLEAR)) {
	            sb.append(arg);
	            sb.append(';');
        	}
        }
        sb.setLength(sb.length() - 1);
        sb.append(ESC_END);
        return sb.toString();
	}
	
	public static boolean isBackground(String code) {
		switch(code) {
			case BACKGROUND_BLACK:
			case BACKGROUND_RED:
			case BACKGROUND_GREEN:
			case BACKGROUND_YELLOW:
			case BACKGROUND_BLUE:
			case BACKGROUND_MAGENTA:
			case BACKGROUND_CYAN:
			case BACKGROUND_WHITE:
			case BACKGROUND_DEFAULT: {
				return true;
			}
		}
		return false;
	}
	
}
