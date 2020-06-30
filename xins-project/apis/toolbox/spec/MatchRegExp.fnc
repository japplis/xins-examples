<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 1.5//EN" "http://www.xins.org/dtd/function_1_5.dtd">

<function name="MatchRegExp"
rcsversion="$Revision: 1.1 $" rcsdate="$Date: 2007/01/24 15:40:36 $">

	<description>Returns whether a text matches a regular expression.</description>

	<input>
		<param name="entry" required="true" type="_text">
			<description>The input text to text.</description>
		</param>
		<param name="regexp" required="true" type="_text">
			<description>The regular expression to test upon.</description>
		</param>
	</input>
	<output>
		<param name="match" required="true" type="_boolean">
			<description>Whether or not the text matches the regular expression.</description>
		</param>
	</output>
</function>
