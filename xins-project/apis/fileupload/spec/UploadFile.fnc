<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 1.4//EN" "http://www.xins.org/dtd/function_1_4.dtd">

<function name="UploadFile"
rcsversion="$Revision: 1.1 $" rcsdate="$Date: 2006/06/08 15:51:37 $">

	<description>Upload a file.</description>

	<input>
		<param name="documentName" required="true" type="_text">
			<description>The name of the file which is uploaded.</description>
		</param>
		<param name="documentContent" required="true" type="_hex">
			<description>The content of the file which is uploaded.</description>
		</param>
		<param name="owner" required="true" type="_text">
			<description>The owner of the uploaded file.</description>
		</param>
		<param name="description" required="true" type="_text">
			<description>The description of the uploaded file.</description>
		</param>
		<param name="keywords" required="false" type="_text">
			<description>Keywords related to the file.</description>
		</param>
	</input>
	<output>
	</output>
</function>
