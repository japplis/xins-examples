<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output encoding="UTF-8"
	            method="html"
	            media-type="text/html" />

	<xsl:template match="/">
		<h3>Build properties</h3>
		<xsl:apply-templates select="/result/data/build" />
		<h3>Runtime properties</h3>
		<xsl:apply-templates select="/result/data/runtime" />
		<h3>System properties</h3>
		<xsl:apply-templates select="/result/data/system" />
	</xsl:template>

	<xsl:template match="build|system">
		<table border="1">
			<xsl:for-each select="property">
				<xsl:sort select="@name" />
				<tr>
					<td>
						<xsl:value-of select="@name" />
					</td>
					<td>
						<xsl:value-of select="text()" />
					</td>
				</tr>
			</xsl:for-each>
		</table>
	</xsl:template>

	<xsl:template match="runtime">
		<table id="t1" class="sortable" border="1">
			<tr>
				<th>Name</th>
				<th>Value</th>
			</tr>
			<xsl:for-each select="property[not(starts-with(@name, 'log4j.'))]">
				<tr>
					<td>
						<xsl:value-of select="@name" />
					</td>
					<td>
						<xsl:value-of select="text()" />
					</td>
				</tr>
			</xsl:for-each>
		</table>
	</xsl:template>
</xsl:stylesheet>
