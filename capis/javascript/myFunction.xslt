<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output encoding="UTF-8"
	            media-type="text/html" />

	<xsl:template match="/">
		<html>
			<head>
				<title>XINS result transformed</title>
			</head>
			<body>
				<xsl:choose>
					<xsl:when test="/result/@errorcode">
						<font color="red">
						<xsl:text>Error found: </xslt:text>
						<xsl:value-of select="/result/@errorcode" />
						</font>
					</xsl:when>
					<xsl:otherwise>
						<font color="red">
						<xsl:text>Result: </xsl:text>
						<xsl:value-of select="/result/param[@name='message']/text()" />
					</xsl:otherwise>
				</xsl:choose>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
