<?xml version="1.0" encoding="UTF-8"?>
<!--
 $Id: ThankYouRegistration.xslt,v 1.3 2006/04/06 10:56:31 agoubard Exp $
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="Commons.xslt" />

	<xsl:template match="commandresult">
		<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
			<xsl:call-template name="header">
				<xsl:with-param name="title" select="'Customer registered'" />
			</xsl:call-template>
			<body>
				<xsl:call-template name="page-header">
					<xsl:with-param name="title" select="'Customer registered'" />
				</xsl:call-template>
				<div id="content">
					<span>You have been registered as a new customer.</span>
					<br /><br />
					<span>Please go to the <a style="padding-left:auto" href="?command=SearchPet">login page</a> to enter the pet store.
					</span>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>