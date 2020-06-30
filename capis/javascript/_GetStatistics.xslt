<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output encoding="UTF-8"
	            method="html"
	            media-type="text/html" />

	<xsl:template match="/">
		<xsl:apply-templates select="/result/data/function" />
	</xsl:template>

	<xsl:template match="function">
		<font color="#aa55aa" size="4">
			<xsl:value-of select="@name" />
		</font><p/>
		<table border="1">
			<tr>
				<th>Success</th>
				<th>Call #</th>
				<th>Average (ms)</th>
				<th>Min (ms)</th>
				<th>Max (ms)</th>
				<th>Last (ms)</th>
			</tr>
			<xsl:apply-templates select="successful|unsuccessful" />
		</table><p/>
	</xsl:template>

	<xsl:template match="successful|unsuccessful">
		<tr>
			<td>
				<xsl:choose>
					<xsl:when test="@errorcode">
						<xsl:text>Unsuccessful </xsl:text>
						<xsl:value-of select="@errorcode" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="local-name()" />
					</xsl:otherwise>
				</xsl:choose>
			</td>
			<td>
				<xsl:value-of select="@count" />
			</td>
			<td>
				<xsl:value-of select="@average" />
			</td>
			<td>
				<xsl:value-of select="min/@duration" />
			</td>
			<td>
				<xsl:value-of select="max/@duration" />
			</td>
			<td>
				<xsl:value-of select="last/@duration" />
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
