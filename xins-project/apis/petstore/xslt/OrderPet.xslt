<?xml version="1.0" encoding="UTF-8"?>
<!--
 $Id: OrderPet.xslt,v 1.5 2006/04/06 10:56:31 agoubard Exp $
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="Commons.xslt" />

	<xsl:template match="commandresult">
		<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
			<xsl:call-template name="header">
				<xsl:with-param name="title" select="'Order pet'" />
			</xsl:call-template>
			<body>
				<xsl:call-template name="page-header">
					<xsl:with-param name="title" select="'Order pet'" />
				</xsl:call-template>
				<div id="content">
					<xsl:call-template name="error" />
					<table style="border:0px">
						<tr>
							<td>
								Pet 
								<xsl:if test="parameter[@name='error.code']">
									not
								</xsl:if>
								ordered.<br /><br />
								<a href="?command=SearchPet">Back to the search page</a><br/>
								<a href="?command=Logout">Logout</a><br/>
							</td>
						</tr>
					</table>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>