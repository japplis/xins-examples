<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output encoding="UTF-8"
	            method="html"
	            media-type="text/html" />

	<xsl:template match="/">
		<table border="1">
			<tr>
				<td>XINS version</td>
				<td>
					<xsl:value-of select="/result/param[@name='xins.version']/text()" />
				</td>
			</tr>
			<tr>
				<td>Java version</td>
				<td>
					<xsl:value-of select="/result/param[@name='java.version']/text()" />
				</td>
			</tr>
			<xsl:if test="/result/param[@name='api.version']">
				<xsl:variable name="api.version" select="/result/param[@name='api.version']/text()" />
				<tr>
					<td>API version</td>
					<td>
						<xsl:if test="contains($api.version, '-dev')">
							<xsl:attribute name="bgcolor">red</xsl:attribute>
						</xsl:if>
						<xsl:value-of select="$api.version" />
					</td>
				</tr>
			</xsl:if>
		</table>
	</xsl:template>

</xsl:stylesheet>
